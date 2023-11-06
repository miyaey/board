package com.example.miyaeboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/post/create").authenticated() // /post/create에 대해서는 인증이 필요
                .antMatchers("/post/vote/**").authenticated() // /post/vote에 대해서는 인증이 필요
                .antMatchers("/comment/create/**").authenticated() // /comment/create/**에 대해서는 인증 필요
                .antMatchers("/member/signup").anonymous() // 회원 가입 페이지는 인증되지 않은 사용자만 액세스 가능
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
        return http.build();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.sendRedirect("/error/forbidden");
        };
    }
}
