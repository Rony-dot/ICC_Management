
package com.rony.config.security;

import com.rony.config.HibernateConfig;
import com.rony.config.Initializer;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

        String p = passwordEncoder.encode("1234");
        passwordEncoder.matches("1234",p);
        System.out.println("password : ----------- : below ");
        System.out.println(p.toString());

      /*
        auth
                // inMemoryAuthentication() or jdbcAuthentication(), use only one
                .inMemoryAuthentication()
//                .jdbcAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN");
       */

        /*
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password(passwordEncoder.encode("123"))
                .roles("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("123"))
                .roles("ROLE_USER", "ROLE_ADMIN");

         */

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        custom login page
       /*
       http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/temp/**").permitAll()
                .antMatchers("/", "/register").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/")
//                .successHandler(authSuccessHandler())
                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        */


//        default login page of spring
                /*
        http.
                csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

         */
//        /*
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers( "/register").permitAll()
//                .antMatchers("/**")
//                .hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll();


//         */
    }

    @Bean(name = "passwordEncoder")
    PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }
    

}
