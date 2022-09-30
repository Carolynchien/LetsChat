package com.carolyn.springboot.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.carolyn.springboot.services.AppuserService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class SecurityConfiguration  {

    @Autowired
    private AppuserService appuserService;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests().anyRequest().permitAll();
        // http.authorizeRequests().anyRequest().authenticated();
        
      
        // http.formLogin();

        // http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
         .authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests((request) -> request.antMatchers( "/api/v1/auth/login").permitAll()
         .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
         .antMatchers("/api/like/**").permitAll()
         .antMatchers("/api/user/**").permitAll()
         .antMatchers("/ws/**").permitAll()
         .antMatchers("/api/message/**").permitAll()
         .antMatchers("/api/user-sign-up/**").permitAll().anyRequest().authenticated())
         .addFilterBefore(new JWTAuthenticationFilter (appuserService, jwtTokenHelper),
         UsernamePasswordAuthenticationFilter.class);

         http.csrf().disable().cors().and().headers().frameOptions().disable();
         return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appuserService);
        //Database
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
 

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }
}

