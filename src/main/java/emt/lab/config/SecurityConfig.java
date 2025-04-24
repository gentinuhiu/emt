package emt.lab.config;

import emt.lab.config.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//package emt.lab.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
//
//
//    public SecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .headers((headers) -> headers
//                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
//                )
//                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers("/books/**", "/api/**").hasRole("LIBRARIAN") // Only librarians can manage books
////                        .requestMatchers("/api/user/**").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .formLogin((form) -> form
//                        .permitAll()
//                        .failureUrl("/login?error=BadCredentials")
//                        .defaultSuccessUrl("/swagger-ui/index.html", true)
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/")
//                );
//
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails librarian = User.builder()
////                .username("librarian")
////                .password(passwordEncoder.encode("librarian"))
////                .roles("LIBRARIAN")
////                .build();
////        UserDetails user = User.builder()
////                .username("customer")
////                .password(passwordEncoder.encode("customer"))
////                .roles("CUSTOMER")
////                .build();
////
////        return new InMemoryUserDetailsManager(librarian, user);
////    }
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
//                AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//        return authenticationManagerBuilder.build();
//    }
//
//}
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
