package com.threehk.map.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider authProvider;
    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;
    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(loginAuthenticationFilter());
        //start server security step 2.
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers( "/admin/login").permitAll()
                .antMatchers( "/scout/login").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/data/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/scout/**").hasRole("SCOUT")
//                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/admin/login")
                .loginPage("/scout/login")
                .successHandler(customLoginSuccessHandler)
                .failureHandler(customLoginFailureHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();

        loginAuthenticationFilter.setAuthenticationManager(authenticationManager());
        loginAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        loginAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler);

        return loginAuthenticationFilter;
    }
}
