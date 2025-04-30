//package mk.ukim.finki.emt.lab.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//
////@Configuration
////@EnableWebSecurity
////@EnableMethodSecurity
////public class SecurityConfig {
////
////    /**
////     * Remove the implementation of the following method and replace it with your own code to implement the security requests.
////     * If you do not wish to implement the security requests, leave this code as it is.
////     */
////
////    private final PasswordEncoder passwordEncoder;
//////    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
////
////
////    public SecurityConfig(PasswordEncoder passwordEncoder) {
////        this.passwordEncoder = passwordEncoder;
//////        this.authenticationProvider = authenticationProvider;
////        ;
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http
////                .csrf(AbstractHttpConfigurer::disable)
////                .headers((headers) -> headers
////                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
////                )
////                .authorizeHttpRequests((requests) -> requests
////                          .requestMatchers("/api/books", "/api/countries", "/api/authors").permitAll()
////                          .requestMatchers("/h2/**").hasAnyRole("ADMIN", "LIBRARIAN")
////                          .requestMatchers("/api/books/**", "/api/countries/**", "/api/authors/**").hasAnyRole("ADMIN", "LIBRARIAN")//.hasRole("LIBRARIAN")
////                          .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyRole("ADMIN", "LIBRARIAN")//.hasRole("LIBRARIAN")
////
////                )
////                .formLogin((form) -> form
////                        .permitAll()
////                        .failureUrl("/login?error=BadCredentials")
////                        .defaultSuccessUrl("/swagger-ui/index.html", true)
////                )
////                .logout((logout) -> logout
////                        .logoutUrl("/logout")
////                        .clearAuthentication(true)
////                        .invalidateHttpSession(true)
////                        .deleteCookies("JSESSIONID")
////                        .logoutSuccessUrl("/")
////                );
////
////
////        return http.build();
////    }
////
////    // In Memory Authentication
////        @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user1 = User.builder()
////                .username("user")
////                .password(passwordEncoder.encode("user"))
////                .roles("USER")
////                .build();
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password(passwordEncoder.encode("admin"))
////                .roles("ADMIN")
////                .build();
////            UserDetails librarian = User.builder()
////                    .username("librarian")
////                    .password(passwordEncoder.encode("librarian"))
////                    .roles("LIBRARIAN")
////                    .build();
////
////
////        return new InMemoryUserDetailsManager(user1, admin, librarian);
////    }}
//@Profile("test")
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
//                        corsConfigurationSource()));
////                .authorizeHttpRequests(requests -> requests.requestMatchers(
////                        "/api/**"
////                ).permitAll().anyRequest().hasAnyRole("ADMIN", "LIBRARIAN"))
////                .authorizeHttpRequests(requests -> requests
////                                        .requestMatchers("/api/author/**", "/api/book/**", "/api/categories/**", "/api/user/login", "/api/user/register", "/api/country/**")
////                                        .permitAll()
////                                        .requestMatchers("/api/wishlist/**")
////                                        .hasRole("USER")
////                                        .anyRequest().authenticated())
////                .formLogin((form) -> form.loginProcessingUrl(
////                                "/api/user/login")
////                        .permitAll()
////                        .failureUrl("/api/user/login?error=BadCredentials")
////                        .defaultSuccessUrl(
////                                "/swagger-ui/index.html",
////                                true
////                        ))
////                .logout((logout) -> logout.logoutUrl("/api/user/logout")
////                        .clearAuthentication(true)
////                        .invalidateHttpSession(
////                                true)
////                        .deleteCookies("JSESSIONID")
////                        .logoutSuccessUrl("/api/user/login"))
////                .exceptionHandling((ex) -> ex.accessDeniedPage(
////                        "/access_denied"));
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
//                AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//        return authenticationManagerBuilder.build();
//    }
//
//
//}
//
//
