package com.rental.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.rental.Services.UsersServices;
import com.rental.utils.PasswordEncoder;

@Configuration
// @EnableWebSecurity
public class WebScurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersServices usersServices;

    @Autowired
    PasswordEncoder passwordEncoder;

    
    // @Bean
    // public static PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable().authorizeHttpRequests((authorize) -> {
    //         authorize.anyRequest().authenticated();
    //     }).httpBasic(Customizer.withDefaults());
    //     return http.build();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
        
    //     UserDetails users = User.builder().username(usersServices)
    //         .password(passwordEncoder)
    //         .roles("USER")
    //         .build();
        
    //     UserDetails admins = User.builder()
    //         .username(usersServices)
    //         .password(passwordEncoder)
    //         .roles("ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(users,admins);
    // }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
        .antMatchers("**").permitAll()
            .anyRequest().fullyAuthenticated()
            .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(usersServices);
        return provider;
    }
    
}
