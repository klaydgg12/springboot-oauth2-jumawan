package jumawan.com.springbootproject.oauth2_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public pages
                        .requestMatchers("/", "/error", "/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
                        // Protected page
                        .requestMatchers("/profile").authenticated()
                        // Any other request needs authentication
                        .anyRequest().authenticated()
                )
                // OAuth2 Login configuration
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/") // use your home page with "Login with Google/GitHub" buttons
                        .defaultSuccessUrl("/profile", true) // go to profile after successful login
                )
                // Logout configuration
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // redirect to home after logout
                        .permitAll()
                )
                // Disable CSRF for development (you can enable later for production)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
