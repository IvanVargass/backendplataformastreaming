package plataformas.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .csrf()
        .disable()
        .cors().disable()
        //.and() 
        //.httpBasic()
        
        .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/registro").permitAll()
        .antMatchers("/alquilada").hasAuthority("USER")
        .antMatchers("/pelicula").permitAll()
        //.antMatchers(HttpMethod.GET, "/pelicula").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/pelicula").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/pelicula").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .
        .and()
        .headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
