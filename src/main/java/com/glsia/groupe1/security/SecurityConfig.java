package com.glsia.groupe1.security;

import com.glsia.groupe1.filter.CustomAuthenticationFilter;
import com.glsia.groupe1.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  final UserDetailsService userDetailsService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/login/**","user/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/article/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/categorie/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/approvisionnement/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/LigneVente/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/Vente/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        //http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
