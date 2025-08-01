package td.msk.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    @Order(2147483642)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.requestMatchers("/", "/home").permitAll()
                     .requestMatchers("/holidays/**").permitAll()
                     .requestMatchers("/contact").permitAll()
                     .requestMatchers("/saveMsg").permitAll()
                     .requestMatchers("/courses").permitAll()
                     .requestMatchers("/about").permitAll()
                     .requestMatchers("/assets/**").permitAll())
                     .formLogin(Customizer.withDefaults())
                     .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
