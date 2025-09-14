package com.ecommerce.auth.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthServerConfig {

    @Bean
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = 
            new OAuth2AuthorizationServerConfigurer();

        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth2/**"))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/oauth2/**").permitAll()  // Allow token endpoint access
                .anyRequest().authenticated()
            )
            .apply(authorizationServerConfigurer);

        // Enable form login for testing
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }
}