package com.example.movieapi.configuration;

import com.example.movieapi.jwt.JWTFilter;
import com.example.movieapi.jwt.JWTUtil;
import com.example.movieapi.jwt.LoginFilter;
import com.example.movieapi.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    //AuthenticationManager가 인자로 받을 변수 생성
    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTUtil jwtUtil;

    private final CustomUserDetailService customUserDetailService;
    private final CorsFilter corsFilter;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil,
                          CustomUserDetailService customUserDetailsService, CustomUserDetailService customUserDetailService
            , CorsFilter corsFilter) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
        this.corsFilter = corsFilter;
    }

//Loginfilter 인자로 넘기기 위한 AuthenticationManager를 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8083/", "http://localhost:5173/"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        // you can configure many allowed CORS headers
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //csrf disable
        http
                .cors((auth)->auth.disable());

        http
                .csrf((auth) -> auth.disable()); //세션방식은 세션이 고정되어 csrf 방어해줘야하지만 jwt는 csrf를 stateless로 사용해서 괜찮음

        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/**","/movie/**", "/join/**","/spring/upload",
                                "/loginpage/**", "/login/**", "/rating/**", "/swagger-ui/**").permitAll() //인가 필요 없음
                        .requestMatchers("/admin").hasRole("ADMIN") //해당페이지는 어떤 롤 필요한지
                        .anyRequest().authenticated());// 그 외는 로그인해야함
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(new JWTFilter(jwtUtil, customUserDetailService), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));




        return http.build();
    }
}
