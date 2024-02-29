package com.todayfriend.member.repository;

import com.todayfriend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);
}
