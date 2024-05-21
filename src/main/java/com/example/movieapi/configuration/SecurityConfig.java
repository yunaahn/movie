package com.example.movieapi.configuration;

import com.example.movieapi.jwt.JWTFilter;
import com.example.movieapi.jwt.JWTUtil;
import com.example.movieapi.jwt.LoginFilter;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 변수 생성
    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
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


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //csrf disable
        http
                .csrf((auth) -> auth.disable()); //세션방식은 세션이 고정되어 csrf 방어해줘야하지만 jwt는 csrf를 stateless로 사용해서 괜찮음

        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/movie/**", "/rating/**", "/join/**",
                                "/login/**", "swagger-ui/index.html").permitAll() //인가 필요 없음
                        .requestMatchers("/admin").hasRole("ADMIN") //해당페이지는 어떤 롤 필요한지
                        .anyRequest().authenticated());// 그 외는 로그인해야함
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        //필터 체인 추가 -> 로그인 필터에 authenticationManager 로 자격증명 확인, 토큰 생성 프로세스를 탐
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}
