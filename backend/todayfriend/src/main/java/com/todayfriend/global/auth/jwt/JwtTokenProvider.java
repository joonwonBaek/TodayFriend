package com.todayfriend.global.auth.jwt;

import com.todayfriend.member.domain.Member;
import com.todayfriend.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider  {
    private final UserDetailsService userDetailsService;
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.access.expiration}")
    private long accessTokenValidTime;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenValidTime;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public String createAccessToken(Integer id, String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣음
        claims.put("id", id);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256,secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }
    public String createRefreshToken(Integer id) {

        Date now = new Date();
        return Jwts.builder()
                .setId(Integer.toString(id)) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256,secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    public void storeRefreshToken(int id, String refreshToken) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null) {
            redisTemplate.opsForValue().set(
                    Integer.toString(id),
                    refreshToken,
                    refreshTokenValidTime,
                    TimeUnit.MILLISECONDS

            );
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    //==Jwt 토큰의 유효성 체크 메소드==//
    public Claims parseJwtToken(String token) {
        token = BearerRemove(token); // Bearer 제거
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    //==토큰 앞 부분('Bearer') 제거 메소드==//
    public String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (SignatureException e) {
            log.warn("JWT 서명이 유효하지 않습니다.");
            throw new SignatureException("잘못된 JWT 시그니쳐");
        } catch (MalformedJwtException e) {
            log.warn("유효하지 않은 JWT 토큰입니다.");
            throw new MalformedJwtException("유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException e) {
            log.warn("만료된 JWT 토큰입니다.");
            throw new ExpiredJwtException(null,null,"토큰 기간 만료");
        } catch (UnsupportedJwtException e) {
            log.warn("지원되지 않는 JWT 토큰입니다.");
            throw new UnsupportedJwtException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty.");
        } catch (NullPointerException e){
            log.warn("JWT RefreshToken is empty");
        } catch (Exception e) {
            log.warn("잘못된 토큰입니다.");
        }
        return false;

    }
}
