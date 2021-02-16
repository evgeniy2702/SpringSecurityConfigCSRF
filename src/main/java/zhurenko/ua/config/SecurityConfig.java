package zhurenko.ua.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import zhurenko.ua.security.AuthProvider;


@Configuration
@EnableWebSecurity
@ComponentScan("zhurenko.ua")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    public SecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers("/login", "/registration").permitAll()
                    .mvcMatchers("/").authenticated()
                    .and().csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login/form")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .failureForwardUrl("/login?error=true")
                    .permitAll()
                    .and()
                .logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider( authProvider);
    }
}
