package br.com.sghss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
    throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                               .username("user")
                               .password(passwordEncoder().encode("12345"))
                               .roles("USUARIO")
                               .build();
        
        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder().encode("12345"))
                                .roles("USUARIO", "ADMINISTRADOR")
                                .build();
        
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                                         .requestMatchers("/api/pacientes", "/api/pacientes/*", "/api/suprimentos", "/api/suprimentos/*").permitAll()
                                                                         .requestMatchers("/receitas", "/suprimentos", "/logout")
                                                                         .hasRole("ADMINISTRADOR")
                                                                         .anyRequest()
                                                                         .authenticated())
                                                                                          .formLogin(formLogin -> formLogin
                                                                                                                           .loginPage("/login")
                                                                                                                           .permitAll())
                                                                                          .logout(logout -> logout
                                                                                                                  .logoutUrl("/logout")
                                                                                                                  .logoutSuccessUrl("/login")
                                                                                                                  .permitAll());

        return http.build();
    }
}