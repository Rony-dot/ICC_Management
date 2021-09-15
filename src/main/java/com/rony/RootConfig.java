package com.rony;

import com.rony.config.HibernateConfig;
import com.rony.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.rony.services", "com.rony.config", "com.rony.config.security"})
//@ComponentScan(basePackages = {"com.rony.services"})
public class RootConfig {

    @Bean
    PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

// its not required because, we have scanned the config package;
//    @Bean
//    public HibernateConfig hibernateConfig(){
//        return new HibernateConfig();
//    }
//
//    @Bean
//    public UserService userService(){
//        return new UserService(hibernateConfig());
//    }



}
