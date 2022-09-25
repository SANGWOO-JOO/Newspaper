package Newspaper.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 사전에 prePost로 권한체크를 하겠다는 설정!!
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdapter`는 Filter chian을 구성하는 Configuration클래스

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 인가 정책 설정
//        http
//                .authorizeRequests() // 요청에 대한 보안 검사 실행
//                .anyRequest().authenticated(); // 어떠한 요청에도 인증을 받도록 설정
//
//        // 인증 정책 설정
//        http
//                .formLogin(); // formLogin인증 방식을 사용하도록 설정
//    }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // HttpSecurity를 설정하는 것이 Filter들을 Setting하는 것이다.
            http.authorizeRequests((requests) ->
                    requests.antMatchers("/").permitAll()
                            .anyRequest().authenticated()
            );
            http.formLogin(login->
                    login.defaultSuccessUrl("/", false));
            http.httpBasic();
        }
}
