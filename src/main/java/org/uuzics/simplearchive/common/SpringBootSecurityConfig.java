/*
 * Copyright 2024 Uuzics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uuzics.simplearchive.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringBootSecurityConfig {
    @Value("${simple-archive.admin-account.username}")
    private String adminUsername;
    @Value("${simple-archive.admin-account.password}")
    private String adminPassword;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername(adminUsername)
                        .password(passwordEncoder().encode(adminPassword))
                        .roles("ADMIN")
                        .build()
        );
        return manager;
    }

    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/admin/login")
                                .defaultSuccessUrl("/admin/dash")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler((request, response, authentication) -> response.sendRedirect("/admin/dash"))
                                .failureHandler((request, response, exception) -> response.sendRedirect("/admin/login?error=true"))
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/admin/logout")
                                .logoutSuccessUrl("/")
                );
        return httpSecurity.build();
    }
}
