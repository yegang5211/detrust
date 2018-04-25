package com.hankal.detrust;

import com.hankal.detrust.controller.LoginController;
import com.hankal.detrust.service.filter.TestFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ImportResource(locations = "classpath*:/applicationContext-service.xml")
@SpringBootApplication
@EnableSwagger2
//重点
@ServletComponentScan
// mapper.java扫描
//@MapperScan("com.hankal.detrust.service.security.token")  // 增加该注解会影响**Dao的加载
public class DetrustApplication extends WebMvcConfigurerAdapter { // SpringBootServletInitializer

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    public static void main(String[] args) {
        log.info("=====spring boot start success====");
        SpringApplication.run(DetrustApplication.class, args);

        System.out.println("http://localhost:8080/demo/hello");
        System.out.println("http://localhost:8080/swagger-ui.html");
    }

   /* @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TestFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }*/
}