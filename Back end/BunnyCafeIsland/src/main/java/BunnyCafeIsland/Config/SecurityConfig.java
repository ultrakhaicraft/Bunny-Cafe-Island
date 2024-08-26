package BunnyCafeIsland.Config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //Manager = Admin, therefore we have Staff and Manager role

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } */

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
            JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

                // Query to retrieve a user's credentials (username and password)
                userDetailsManager.setUsersByUsernameQuery(
                    "SELECT email AS username, password, CASE status WHEN 'Active' THEN 1 ELSE 0 END AS enabled FROM staff WHERE email=?"
                );

                // Query to retrieve the authorities/roles of a user
                userDetailsManager.setAuthoritiesByUsernameQuery(
                    "SELECT staff.email AS username, authorities.authority FROM authorities INNER JOIN staff ON authorities.staff_id = staff.id WHERE staff.email=?"
                );

            return userDetailsManager;
    }




    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/staffs").hasRole("Manager")
                    .requestMatchers(HttpMethod.GET, "/api/staffs/**").hasRole("Manager")
                    .requestMatchers(HttpMethod.POST, "/api/staffs").hasRole("Manager")
                    .requestMatchers(HttpMethod.PUT, "/api/staffs").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/api/staffs/**").hasRole("Manager")

                    .requestMatchers(HttpMethod.GET, "/api/bunnies").hasRole("Staff")
                    .requestMatchers(HttpMethod.GET, "/api/bunnies/**").hasRole("Staff")
                    .requestMatchers(HttpMethod.POST, "/api/bunnies").hasRole("Manager")
                    .requestMatchers(HttpMethod.PATCH, "/api/bunnies/**").hasRole("Staff")
                    .requestMatchers(HttpMethod.PUT, "/api/bunnies").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/api/bunnies/**").hasRole("Manager")
        );

        //Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());
        
        //Disable CSRF for now, not required for stateless API that use POST, PUT, PATCH, DELETE
        http.csrf(csrf -> csrf.disable());

        return http.build();

    }


     /*@Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails Hifami = User.builder()
            .username("Hifami")
            .password("{noop}1234")
            .roles("Staff","Manager")
            .build();

        UserDetails Alice = User.builder()
            .username("AliceJohnson")
            .password("{noop}alice")
            .roles("Staff")
            .build();

        return new InMemoryUserDetailsManager(Hifami, Alice);
    }*/
}
