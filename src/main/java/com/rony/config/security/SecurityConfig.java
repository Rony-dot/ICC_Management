
package com.rony.config.security;

import com.rony.config.HibernateConfig;
import com.rony.config.Initializer;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.authenticationProvider(authProvider());
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);

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

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/images/**", "/js/**", "/css/**", "/vendors/**", "/build/**" ).permitAll()
                .antMatchers("/","/login","/register", "/test/**", "/fileUpload").permitAll()
                .antMatchers("/users/all","/countries/all","/players/all","/events/all","/teams/all","/series/all").permitAll()
                .antMatchers("/countries/**","/events/**","/series/**").hasRole("ICC_AUTHORITY")
                .antMatchers("/players/**","/teams/**","/users/**").hasRole("TEAM_MANAGER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("email")
                .passwordParameter("password")
//                .defaultSuccessUrl("/success")
                .successHandler(authSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
//         */
    }

//    @Bean(name = "passwordEncoder")


//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return authProvider;
//    }
//
    @Bean
    public CustomAuthSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

//    protected UserDetailsService userDetailsService(){
//        UserDetails ronyUser = User.builder()
//                .username("rony")
//                .password(passwordEncoder.encode("1234"))
//                .authorities("ROLE_USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("1234"))
//                .authorities("ROLE_ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(ronyUser, admin);
//    }

}
