package com.example.itransitioncourseproject.configs;

import com.example.itransitioncourseproject.utils.BaseUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST = {
            BaseUrl.API_BASE,
            BaseUrl.API_HOME,
            BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/auth/register",
            BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/auth/login",

            // COLLECTIONS
            BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/collections",

            // ITEMS
            BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/items",
            BaseUrl.API_PREFIX+BaseUrl.API_VERSION+"/items/collection/*",
            BaseUrl.API_PREFIX+BaseUrl.API_VERSION+"/items/tag/*",

            "/assets/css/**",
            "/assets/flags/**",
            "/assets/fonts/**",
            "/assets/images/**",
            "/assets/js/**",
            "/assets/plugins/**",
            "/webjars/**"
    };

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();

        http
                .authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .formLogin()
                .loginPage(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/auth/login")
                .defaultSuccessUrl(BaseUrl.API_HOME);

        http
                .logout()
                .logoutSuccessUrl(BaseUrl.API_HOME)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
