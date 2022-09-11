package com.ecommerce.app.debodelivery.config;

import com.ecommerce.app.debodelivery.filter.JwtFilter;
import com.ecommerce.app.debodelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/v*/authenticate",
                        "/api/v*/emailVerify/**",
                        "/api/v*/registration/confirm",
                        "/api/v*/deleteUser/**/status/**",
                        "/api/v*/addProduct",
                        "/api/v*/getAllProduct",
                        "/api/v*/getProductByName/**",
                        "/api/v*/getProductByCategoryName/**",
                        "/api/v*/getProductByMaxPrice/**",
                        "/api/v*/getProductByGivenRange/startRange/**/maxRange/**",
                        "/api/v*/productImageFindById/**",
                        "/api/v*/getAllProductBySorting/**",
                        "/api/v*/getAllCategory/**",
                        "/api/v*/updateCategory/categoryOldName/**/categoryUpdatedName/**",
                        "/api/v*/reg/**",
                        "/api/v*/findByProductNameOrProductSellingPrice/productName/**",
                        "/api/v*/registration/confirm**",
                        "/v*/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/api/v*/addUser")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
