package BunnyCafeIsland.Config;


import BunnyCafeIsland.Filter.JwtAuthFilter;
import BunnyCafeIsland.Filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static BunnyCafeIsland.Enums.Permission.STAFF_READ;
import static BunnyCafeIsland.Enums.Permission.*;
import static BunnyCafeIsland.Enums.Role.Manager;
import static BunnyCafeIsland.Enums.Role.Staff;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //Manager = Admin, therefore we have Staff and Manager role

    private final JwtAuthFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        //Disable CSRF for now, not required for stateless API that use POST, PUT, PATCH, DELETE
        http.csrf(AbstractHttpConfigurer::disable);

        //Role permission issue
        http
                .authorizeHttpRequests(req->
                             req.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/management/menuItems/images/**").permitAll()
                                     .requestMatchers("/api/management/menuItems/**").hasAnyRole(Staff.name(),Manager.name())
                                .requestMatchers(HttpMethod.GET, "/api/management/menuItems").hasAnyAuthority(STAFF_READ.name(),MANAGER_READ.name())
                                .requestMatchers(HttpMethod.GET, "/api/management/menuItems/**").hasAnyAuthority(STAFF_READ.name(),MANAGER_READ.name())
                                .requestMatchers(HttpMethod.POST, "/api/management/menuItems").hasAnyAuthority(MANAGER_CREATE.name())
                                .requestMatchers(HttpMethod.PUT, "/api/management/menuItems").hasAnyAuthority(MANAGER_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/api/management/menuItems/**").hasAnyAuthority(MANAGER_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(new LoggingFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        //TODO: Logout


        return http.build();

    }

    //TODO: Paste to the SecurityFilterChain
     /*http.authorizeHttpRequests(configurer ->
            configurer.anyRequest().permitAll()

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


                    .requestMatchers(HttpMethod.GET, "/api/menuItems").hasRole("Staff")
                    .requestMatchers(HttpMethod.GET, "/api/menuItems/**").hasRole("Staff")
                    .requestMatchers(HttpMethod.POST, "/api/menuItems").hasRole("Manager")
                    .requestMatchers(HttpMethod.PATCH, "/api/menuItems/**").hasRole("Staff")
                    .requestMatchers(HttpMethod.PUT, "/api/menuItems").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/api/menuItems/**").hasRole("Manager")

                    .requestMatchers(HttpMethod.POST, "/api/login").permitAll()




        );

        //Use HTTP Basic authentication
        //http.httpBasic(Customizer.withDefaults());
        */

     /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
            JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

                // Query to retrieve a user's credentials (username and password)
                userDetailsManager.setUsersByUsernameQuery(
                            "SELECT email , password, " +
                                    " CASE WHEN status = 'Active' THEN 1 ELSE 0 END AS status  " +
                            "FROM staff " +
                            "WHERE email=?"
                );

                // Query to retrieve the authorities/roles of a user
                userDetailsManager.setAuthoritiesByUsernameQuery(
                            "SELECT staff.email, roles.authority " +
                            "FROM staff " +
                            "INNER JOIN roles ON staff.id = roles.staff_id " +
                            "WHERE staff.email=?"
                );

            return userDetailsManager;
    }*/

}
