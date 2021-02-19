package zhurenko.ua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import zhurenko.ua.security.AuthProvider;


@Configuration
@EnableWebSecurity
@ComponentScan("zhurenko.ua")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthProvider authProvider;

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                    //разрешаем доступ к страницам всем пользователям
                    .mvcMatchers("/login", "/registration").permitAll()

                    //запрещаем доступ к страницам не авторизированным пользователям
                    .mvcMatchers("/", "/showBook").authenticated()
                    .and().csrf().disable()

                //описываем работу с формой логирования
                .formLogin()
                    //указываем запрос на страничку с вводом логина и пароля
                    .loginPage("/login")
                    //указываем запрос, по которому в форме логирования будет
                    // отправляться данные пользователя логин и пародь
                    .loginProcessingUrl("/login/process")
                    //указываем name поля input , где будем вводить имя пользователя или логин
                    .usernameParameter("email")
                    //указываем name поля input , где будем вводить пароль пользователя
                    .passwordParameter("password")
                    //указіваем запрос, по которому будем переходить в случае не зарегестрированніх
                    // логина и пароля
                    .failureForwardUrl("/login?error=true")
                    .permitAll()
                    .and()
                    //указываем ,что разголиниться могут все
                .logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider( authProvider);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
