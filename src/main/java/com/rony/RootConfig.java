package com.rony;

import com.rony.config.HibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.rony.services", "com.rony.config"})
//@ComponentScan(basePackages = {"com.rony.services"})
public class RootConfig {

// its not required because, we have scanned the config package;
//    @Bean
//    public HibernateConfig hibernateConfig(){
//        return new HibernateConfig();
//    }

}
