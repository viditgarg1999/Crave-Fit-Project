package com.leggo.fit.common.configuration;

import com.leggo.fit.common.configuration.JwtConfig.JwtAuthenticationFilter;
import com.leggo.fit.common.configuration.JwtConfig.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/login/via-password").permitAll()
                .antMatchers(HttpMethod.POST,"/api/signup/register").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login/via-otp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login/validate-otp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/signup/register/validate-otp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/signup/resend-otp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login/resend-otp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/forgot/via-phone").permitAll()
                .antMatchers(HttpMethod.POST,"/api/forgot/new-password").permitAll()
                .antMatchers(HttpMethod.POST,"/api/forgot/via-email").permitAll()
                .antMatchers(HttpMethod.GET,"/api/login/validate-refresh-token").permitAll()
                //.antMatchers("/v2/api-docs","/swagger-ui/","/swagger-ui.html","/swagger-ui/index.html/v3/api-docs/swagger-config","/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config","/swagger-ui/index.html").permitAll()
                .antMatchers("/swagger-ui.html",
                        "/swagger-ui/**" ,
                        "/v2/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManager) throws Exception {

    }

}
