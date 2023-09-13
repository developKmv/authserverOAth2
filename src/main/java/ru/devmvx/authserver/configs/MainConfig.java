package ru.devmvx.authserver.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.devmvx.authserver.dao.UserRepo;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class MainConfig {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/KebabCloud?useSSL=false&serverTimezone=UTC");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("Dune1488");
        return dataSourceBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepo repo) {
        return username -> repo.getUserByUsername(username);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> {
            authz.anyRequest().authenticated();
        });
        http.formLogin((confForm)->confForm.permitAll());
        return http.build();
    }

}
