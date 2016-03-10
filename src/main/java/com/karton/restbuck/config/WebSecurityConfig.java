package com.karton.restbuck.config;

import com.karton.restbuck.user.Role;
import com.karton.restbuck.user.UserAccountDetailsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@NoArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USERS = "/users";
    public static final String LOGIN = "/login";
    public static final String ACCESS_DENIED_URL = "/403";
    public static final String HTTP401_HEADER_VALUE = "401";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, USERS)
                .hasAuthority(Role.ROLE_USER.toString())
                .and()
                .formLogin().loginProcessingUrl(LOGIN)
                .successHandler(successHandler())
                .permitAll()
                .defaultSuccessUrl(USERS)
                .and()
                .exceptionHandling().accessDeniedPage(ACCESS_DENIED_URL)
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint(HTTP401_HEADER_VALUE));
    }

    private AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler(){

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
                clearAuthenticationAttributes(request);
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserAccountDetailsService userAccountDetailsService) throws Exception {
        auth.userDetailsService(userAccountDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
