package jumawan.com.springbootproject.oauth2_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                        .loginPage("/") // Home page with login buttons
                        .defaultSuccessUrl("/profile", true) // Redirect to profile after login
                )
                // Logout configuration
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/") // Redirect to home page
                        .invalidateHttpSession(true) // Invalidate session
                        .clearAuthentication(true)   // Clear authentication
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                )
                // Disable CSRF only for development
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
