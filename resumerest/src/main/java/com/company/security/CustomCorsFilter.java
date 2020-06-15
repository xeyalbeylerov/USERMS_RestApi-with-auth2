package com.company.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CustomCorsFilter {
@Bean
    public FilterRegistrationBean corsFilter(){
    UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    CorsConfiguration config=new CorsConfiguration();
    config.setAllowCredentials(true);//yəni username ve parol kimi credentiallarin gonderilmesine icaze verir
    config.addAllowedOrigin("*");//hansi url-lerden muraciet gele biler
    config.addAllowedHeader("*");//hansi nov header ola biler, json ve s
    config.addAllowedMethod("*");//hansi method ola biler post get ve s
    source.registerCorsConfiguration("/**",config);
    FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
}
}
