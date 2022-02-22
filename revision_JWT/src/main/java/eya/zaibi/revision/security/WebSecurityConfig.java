package eya.zaibi.revision.security;


import eya.zaibi.revision.security.jwt.AuthEntryPointJwt;
import eya.zaibi.revision.security.jwt.AuthTokenFilter;
import eya.zaibi.revision.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl beanauth;

    @Autowired
    private AuthEntryPointJwt bean_auth_exception;

    @Bean
    public AuthTokenFilter create_filter_jwt() {

        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // je peux utiliser mon propore algorithme pour crypter/décypter les mots de passe
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws
            Exception {
        authenticationManagerBuilder.userDetailsService(beanauth).
                passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(bean_auth_exception).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
               // .antMatchers("/api/v1/clients/**").hasRole("ADMIN")
               // .antMatchers("/api/v1/**").permitAll()
                
                .anyRequest().authenticated();

        http.addFilterBefore(create_filter_jwt(),
                UsernamePasswordAuthenticationFilter.class);
    }

}
