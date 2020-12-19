package com.javainuse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Web security config.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final String INITIAL_ADMIN_PASSWORD = "ass";//ConfigProperties.INITIAL_ADMIN_PASSWORD;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;


    public WebSecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        try {
            userDetailsService.loadUserByUsername("admin");
        } catch (UsernameNotFoundException e) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder().encode(INITIAL_ADMIN_PASSWORD));
            admin.setEnabled(true);
            Roles adminRole = new Roles();
            Roles userRole = new Roles();
            adminRole.setUser(admin);
            userRole.setUser(admin);
            adminRole.setRole("ADMIN");
            userRole.setRole("USER");
            admin.getRoles().add(adminRole);
            admin.getRoles().add(userRole);
            userRepository.save(admin);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .and().authorizeRequests().antMatchers("/swagger**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable();
    }
}