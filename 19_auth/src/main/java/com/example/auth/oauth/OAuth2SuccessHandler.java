package com.example.auth.oauth;

import com.example.auth.entity.CustomUserDetails;
import com.example.auth.jwt.JwtTokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
// OAuth2 통신이 성공적으로 끝났을 때 사용하는 클래스
// JWT 를 활용한 인증 구현하고 있기 때문에 ID Provider 에게 받은 정보를 바탕으로 JWT 를 발급하는 역할을 하는 용도
// JWT 를 발급하고 클라이언트가 저장할 수 있도록 특정 URL 로 리다이렉트 시키자.
public class OAuth2SuccessHandler
        // 인증 성공 후 특정 URL 로 리다이렉트 시키고 싶을 때 활용할 수 있는 successHandler
        extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils tokenUtils;
    private final UserDetailsManager userDetailsManager;

    public OAuth2SuccessHandler(
            JwtTokenUtils tokenUtils,
            UserDetailsManager userDetailsManager
    ) {
        this.tokenUtils = tokenUtils;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    // 인증 성공 시 호출되는 메서드
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // Oauth2UserServiceImpl 에서 반환한 DefaultOAuth2User 가 저장
        OAuth2User oAuth2User
                = (OAuth2User) authentication.getPrincipal();
        // 소셜 로그인을 한 새로운 사용자를 우리의 UserEntity 로 전환하기 위한 작업
        // username: 이메일을 @ 기준으로 나누고, 뒤쪽에 ID Provider(Naver) 같은 값으로 조치
        String email = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");
        String username
                = String.format("{%s}%s", provider, email.split("@")[0]);
        String providerId = oAuth2User.getAttribute("id").toString();

        // 처음으로 소셜 로그인한 사용자를 데이터베이스에 등록
        if(!userDetailsManager.userExists(username)) {
            userDetailsManager.createUser(CustomUserDetails.builder()
                    .username(username)
                    .password(providerId)
                    .email(username)
                    .provider(provider)
                    .providerId(providerId)
                    .build());
        }

        // 데이터베이스에서 사용자 회수
        UserDetails details
                = userDetailsManager.loadUserByUsername(username);
        String jwt = tokenUtils.generateToken(details);

        // JWT 생성
//        String jwt = tokenUtils
//                .generateToken(User
//                        .withUsername(oAuth2User.getName())
//                        .password(oAuth2User.getAttribute("id").toString())
//                        .build());

        // 목적지 URL 설정
        // 우리 서비스가 프론트엔드 구성에 따라 유연하게 대처해야 한다.
        String targetUrl = String.format(
                "http://localhost:8080/token/val?token=%s", jwt
        );
        // 실제 리다이렉트 응답 생성
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
