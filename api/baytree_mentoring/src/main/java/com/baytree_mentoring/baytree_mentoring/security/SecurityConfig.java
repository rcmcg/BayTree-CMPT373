package com.baytree_mentoring.baytree_mentoring.security;


import com.baytree_mentoring.baytree_mentoring.filter.CustomAuthenticationFilter;
import com.baytree_mentoring.baytree_mentoring.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();  // TODO: Remove this line without breaking everything as it's not secure.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        // Leaving this code here commented out because I think the actual fix should look something like this.
        // We need to specify the authentication level for specific endpoints.
        http
                .authorizeRequests()
                        .antMatchers(
                                "/",
                                "/user/add/", "/user/get/all/",
                                "/get/views/mentees/", "/get/mentees/all/",
                                "/questionnaire/add/", "/monthlyquestionnaire/**",
                                "/notifications/send/", "/notifications/get/{username}/", "/notifications/get/all/",
                                "/resource/add/", "/resource/get/all/", "/resource/delete/{resourceId}/",
                                "/session/add/",
                                "/user/add/mentor/", "/user/get/views/mentors/", "/user/get/mentors/all/")
                        .permitAll()
                        .anyRequest().authenticated();
//         This basically says all URLs are permitted without authentication. Really, should be authenticated to view
//         the above URLs but to get the project working leaving it for now .
//        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
         http.addFilter(customAuthenticationFilter);
         http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
