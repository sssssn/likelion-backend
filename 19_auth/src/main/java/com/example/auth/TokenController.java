package com.example.auth;

import com.example.auth.jwt.JwtRequestDto;
import com.example.auth.jwt.JwtTokenDto;
import com.example.auth.jwt.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("token") // http://localhost:8080/token/** 부터 시작하는 요청들
public class TokenController {
    // UserDetailsManager: 사용자 정보 회수
    // PasswordEncoder: 비밀번호 대조용 인코더
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    public TokenController(
            UserDetailsManager manager,
            PasswordEncoder passwordEncoder,
            JwtTokenUtils jwtTokenUtils
    ) {
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    // /token/issue: JWT 발급용 Endpoint
    @PostMapping("/issue")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        // 사용자 정보 회수
        UserDetails userDetails
                = manager.loadUserByUsername(dto.getUsername());
        log.info(userDetails.getAuthorities().toString());
        // 기록된 비밀번호와 실제 비밀번호가 다를 때, 평문 비밀번호와 암호화 비밀번호를 비교할 수 있다.
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }

//    // POST /token/secured
//    // 인증이 필요한 URL
//    @PostMapping("/secured")
//    public String checkSecure() {
//        log.info(SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName()
//        );
//        return "success";
//    }

    @GetMapping("/val")
    public Claims val(@RequestParam("token") String jwt) {
        return jwtTokenUtils.parseClaims(jwt);
    }
}
