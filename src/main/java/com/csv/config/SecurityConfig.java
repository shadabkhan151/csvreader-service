package com.csv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //HTTP Basic authentication
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/readnstore/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/readnstore/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/readnstore/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/readnstore/**").hasRole("ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER", "ADMIN");
    }
}