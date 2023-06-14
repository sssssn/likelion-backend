package com.example.jpa;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    // Spring 을 활용하는데 필요한 다양한 설정을 담고 있는 용도

    @Bean
    public AppConfigData connectionUrl() {
        // 이 메서드의 결과(반환값)을 Bean 객제로 등록
        if (true /* 현재 나의 상황에 따라서 다른 URL 을 지급하는 코드 */)
            return new AppConfigData("main-url");
            else return new AppConfigData("backup-url");
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
