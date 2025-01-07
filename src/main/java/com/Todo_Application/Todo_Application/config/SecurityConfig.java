package com.Todo_Application.Todo_Application.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


// * Security Configuration class for the application
// *
// * @Configuration marks this as a configuration class
// * @EnableWebSecurity enables Spring Security's web security support
// * @EnableMethodSecurity enables method-level security using annotations like @PreAuthorize
// */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${app.security.user.name}")
    private String userName;
    @Value("${app.security.user.password}")
    private String userPassword;
    @Value("${app.security.admin.name}")
    private String adminName;
    @Value("${app.security.admin.password}")
    private String adminPassword;
    @Value("${app.security.user.roles:USER}")
    private String userRoles;
    @Value("${app.security.admin.roles:ADMIN}")
    private String adminRoles;

//     * Configures user details service with in-memory authentication
//     *
//     * @Bean marks this method as a source of bean definitions
//     * @return UserDetailsService configured with user and admin credentials
//     *
//     * Creates two users:
//     * 1. Regular user with USER role
//     * 2. Admin user with ADMIN role
//     * Both passwords are encoded using BCrypt
//     */
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername(this.userName).password(this.passwordEncoder().encode(this.userPassword)).roles(new String[]{this.userRoles}).build();
        UserDetails admin = User.withUsername(this.adminName).password(this.passwordEncoder().encode(this.adminPassword)).roles(new String[]{this.adminRoles}).build();
        userDetailsService.createUser(user);
        userDetailsService.createUser(admin);
        return userDetailsService;
    }

//    /**
//     * Creates password encoder bean for secure password hashing
//     *
//     * @Bean marks this method as a source of bean definitions
//     * @return BCryptPasswordEncoder instance for password encoding
//     *
//     * BCrypt is a strong hashing function designed for passwords
//     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(csrf -> csrf.disable())

                // Configure authorization rules for HTTP requests
                .authorizeHttpRequests(auth -> auth
                        // Allow everyone to access Swagger UI endpoints
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Only ADMIN can create tasks
                        .requestMatchers("/tasks/create**").hasRole("ADMIN")

                        // Only ADMIN can update tasks
                        .requestMatchers("/tasks/update/**").hasRole("ADMIN")

                        // Only ADMIN can delete tasks
                        .requestMatchers("/tasks/delete/**").hasRole("ADMIN")

                        // USER role can view all tasks
                        .requestMatchers("/tasks/getAll").hasRole("USER")

                        // All other requests need authentication
                        .anyRequest().authenticated()
                )

                // Enable HTTP Basic authentication with default settings
                .httpBasic(Customizer.withDefaults());

        // Build and return the security filter chain
        return http.build();
    }

}
