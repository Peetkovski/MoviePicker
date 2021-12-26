package com.example.moviepicker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/search/**").permitAll()
                .antMatchers("/movie/**").permitAll()
                .antMatchers("/profile/**").hasAnyRole(ApplicationUserRole.USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    private final PasswordEncoder passwordEncoder;




    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails annasmith = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.GUEST.name())
                .authorities(ApplicationUserRole.GUEST.getGrantedAuthorities())
                .build();

        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.USER.name())
                .authorities(ApplicationUserRole.USER.getGrantedAuthorities())
                .build();


        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.USER.name())
                .authorities(ApplicationUserRole.USER.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(
                annasmith,
                linda,
                tom
        );
    }}
