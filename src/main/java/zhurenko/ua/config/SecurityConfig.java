package zhurenko.ua.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity(debug = true)
@ComponentScan("zhurenko.ua")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private AuthProvider authProvider;
//
//    public void setAuthProvider(AuthProvider authProvider) {
//        this.authProvider = authProvider;
//    }

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    //Доступ только для не зарегистрированных пользователей
                    //.antMatchers("/registration").not().fullyAuthenticated()

                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/admin/**").hasRole("ADMIN")

                    //Доступ только для пользователей с ролью Пользователь
                    .antMatchers("/").hasRole("USER")

                    //запрещаем доступ к страницам неавторизированным пользователям
//                    .antMatchers("/", "/showBook").authenticated()

                    //разрешаем доступ к страницам всем пользователям
                    .antMatchers("/login", "/registration").permitAll()

                    //Все остальные страницы требуют аутентификации
                    //.anyRequest().authenticated()
                    .and()//.csrf().disable() //отмена защиты от аттаки csrf (подделки межсайтовых запросов)

                //описываем работу с формой логирования
                .formLogin()
                    //указываем запрос на страничку с вводом логина и пароля
                    .loginPage("/login")

                    //Перенарпавление на главную страницу после успешного входа
                    //.successForwardUrl("/")

                    //указываем запрос, по которому в форме логирования будет
                    // отправляться данные пользователя логин и пародь
                    .loginProcessingUrl("/login/process")

                    //указываем name поля input , где будем вводить имя пользователя или логин
                    //прим-ся эта настройка в сл, если поле в классе не совпадает сo словом username
                    .usernameParameter("email")

                    //указываем name поля input , где будем вводить пароль пользователя
                    //прим-ся эта настройка в сл, если поле в классе не совпадает сo словом password
                    //.passwordParameter("password")

                    //указываем запрос, по которому будем переходить в случае не зарегестрированных
                    // логина и пароля
                    .failureForwardUrl("/login?error=true")
                    .permitAll()
                    .and()
                    //указываем ,что разлогиниться могут все
                .logout();

    }

    //настройка кодировки пароля
    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider( authProvider);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
