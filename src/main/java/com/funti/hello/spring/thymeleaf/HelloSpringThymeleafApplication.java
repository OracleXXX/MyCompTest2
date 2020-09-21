package com.funti.hello.spring.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class HelloSpringThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringThymeleafApplication.class, args);
    }

    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();

    }

    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

}
