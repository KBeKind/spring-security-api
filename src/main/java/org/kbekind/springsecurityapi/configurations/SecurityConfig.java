package org.kbekind.springsecurityapi.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService detailsService) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(detailsService);
//        return new ProviderManager(daoAuthenticationProvider);
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Never disable csrf without having stateless session.  sessionManagement cannot be left enabled if using csrf.disable .  WOULD OPEN TO CSRF ATTACK
        return http.
                csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/auth/**").permitAll();
                        auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }



}
