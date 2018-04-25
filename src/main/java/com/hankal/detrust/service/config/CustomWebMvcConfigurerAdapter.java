package com.hankal.detrust.service.config;

import com.hankal.detrust.service.security.LoginInterceptor;
import com.hankal.detrust.service.security.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 */
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration securityIntertor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        securityIntertor.excludePathPatterns("/login");
        securityIntertor.excludePathPatterns("/register");
        securityIntertor.excludePathPatterns("/logout");

        InterceptorRegistration loginIntertor = registry.addInterceptor(getLoginInterceptor());
        loginIntertor.addPathPatterns("/login");
        loginIntertor.addPathPatterns("/register");
        loginIntertor.addPathPatterns("/logout");
    }


}
