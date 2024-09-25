package com.ozius.poc_spring_security.conf;

import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadWebApplicationHttpSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Add configuration logic as needed.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.apply(AadWebApplicationHttpSecurityConfigurer.aadWebApplication())
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated();
        return http.build();
    }
}

//@Component
//public class SecurityConfig {
//
//    @Bean
//    public HttpSessionEventPublisher sessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/oauth2/**", "/login/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(Customizer.withDefaults())
//                .oidcLogout((logout) -> logout
//                        .backChannel(Customizer.withDefaults())
//                )
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/logout") // Disable CSRF protection for the logout endpoint
//                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Customize session policy
//                );
//
//        return http.build();
//    }
//
//    private LogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
//        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//        successHandler.setDefaultTargetUrl("https://crozy.b2clogin.com/crozy.onmicrosoft.com/oauth2/v2.0/logout?p=b2c_1_poc-spring-security");
//        successHandler.setPostLogoutRedirectUri("{baseUrl}/");
//        return successHandler;
//    }
//}