package com.academicevents.api.WebConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Setar as configurações para todas as rotas
                .allowedOrigins("*") // Adicione a origem permitida
                .allowedMethods("GET", "POST") // Métodos permitidos
                .allowedHeaders("*"); // Cabeçalhos permitidos
    }
}