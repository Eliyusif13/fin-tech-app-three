package com.sadiqov.tech_app_three.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationConfig {

    public static String urlMB;

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(getClass().getName());
    }
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    public static String getUrlMB() {
        return urlMB;
    }
@Value("${URL_MB}")
    public  void setUrlMB(String urlMB) {
        ApplicationConfig.urlMB =
                String.format(urlMB, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyy")));
    }
}
