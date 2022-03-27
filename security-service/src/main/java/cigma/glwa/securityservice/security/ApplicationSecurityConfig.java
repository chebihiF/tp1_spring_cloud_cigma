package cigma.glwa.securityservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index.html","/css/**").permitAll()
                .antMatchers(HttpMethod.GET,"/customers/**").hasAuthority("customer:read")
                .antMatchers(HttpMethod.POST,"/customers/**").hasAuthority("customer:write")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder.encode("Admin"))
                .authorities("customer:write","customer:read","customer:delete","ROLE_ADMIN")
                .build();
        UserDetails manager = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("1234"))
                .authorities("customer:read","ROLE_MANAGER")
                .build();
        return new InMemoryUserDetailsManager(admin,manager);
    }
}
