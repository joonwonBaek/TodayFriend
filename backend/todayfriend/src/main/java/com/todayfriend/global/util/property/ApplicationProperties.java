package com.todayfriend.global.util.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    public static String HOST_IMAGE_URL;
    public static String PROFILE_PATH;

//    public ApplicationProperties(@Value("${host.profile.url}") String hostImgUrl,
//                                 @Value("${file.profile.path}") String profilePath) {
//        HOST_IMAGE_URL = hostImgUrl;
//        PROFILE_PATH = profilePath;
//    }
}
