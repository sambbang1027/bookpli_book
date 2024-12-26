package com.example.bookpli_book.common.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("com.example.bookpli_book")
@Configuration
public class OpenFeignConfig {

    /* FULL : Request, Response의 Header, Body, 메타데이터를 로깅 */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
