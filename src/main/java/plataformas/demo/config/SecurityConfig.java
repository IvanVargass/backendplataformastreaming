package plataformas.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .csrf()
        .disable()
        .cors()
        .and() 
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/pelicula").permitAll()
        .antMatchers(HttpMethod.GET, "/").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
