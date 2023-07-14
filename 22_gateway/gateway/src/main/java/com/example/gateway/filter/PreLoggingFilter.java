package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class PreLoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(
            // HttpServletRequest & Response 대신
            ServerWebExchange exchange,
            // FilterChain
            GatewayFilterChain chain
    ) {
        // 사용자가 보낸 HTTP 요청을 받았다.
        ServerHttpRequest httpRequest = exchange.getRequest();
        // PreLoggingFilter 에서 요청을 식별할 수 있는 HTTP Header 를 작성
        // 나중에 PostLoggingFilter 에서 해당 Header 를 바탕으로 실행에 걸린 시간 측정
        String requestId = UUID.randomUUID().toString();
        // 사용자가 보낸 요청을 조작(변형)하겠다.
        httpRequest.mutate()
                // 헤더를 변형한다.
                .headers(httpHeaders -> {
                    httpHeaders.add(
                            "x-gateway-request-id",
                            requestId
                    );
                    httpHeaders.add(
                            "x-gateway-request-time",
                            String.valueOf(Instant.now().toEpochMilli())
                    );
                });
        log.info("start transaction: {}", requestId);

        // filterChain.doFilter() 대신
        return chain.filter(exchange);
    }
}
