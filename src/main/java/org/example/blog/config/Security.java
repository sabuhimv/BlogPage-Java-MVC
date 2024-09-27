package org.example.blog.config;

import org.example.blog.config.auth.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Autowired
    private CustomUserDetailService userDetailService;


//    ENCODE ELEMEK UCUN HAZIR METHODDU
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(x->x.disable())
                .authorizeHttpRequests(
                        request->request
                                .requestMatchers("/admin/category/**").hasAnyAuthority("ADMIN")
                                .requestMatchers("/admin/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/admin")
                        .loginPage("/login")
                        .failureUrl("/login")
                )
                .logout(logout->
                        logout.logoutSuccessUrl("/login")
                )
                .exceptionHandling(e->
                        e.accessDeniedPage("/login")
                );

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        // ensure the passwords are encoded properly
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("fuad@gmail.com").password("password").roles("USER","ADMIN").build());
//        manager.createUser(users.username("sebuhi").password("$2a$10$K5NxMktV5cx9V/mgT9555./43oZ4rDNvupqEsadjaxlczjEdb59na").roles("USER","ADMIN").build());
//        return manager;
//    }
}
