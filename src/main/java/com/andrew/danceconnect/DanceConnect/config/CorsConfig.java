package com.andrew.danceconnect.DanceConnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Конфиг для работы с frontend
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Разрешить запросы с фронтенда
                .allowedMethods("GET", "POST", "PATCH", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}