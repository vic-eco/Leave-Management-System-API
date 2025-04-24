package cy.ac.ucy.cs.epl425.LMS.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    AuthenticationManager authenticationManager;

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsManager());
        authenticationManager = authenticationManagerBuilder.build();

        http
            .csrf(csrf->csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.POST, "/api/employees/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/leaves/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/leaves/employees/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/leaves/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/leaves/**").permitAll()
				.anyRequest().authenticated()
			)
            .authenticationManager(authenticationManager);

		return http.build();
	}

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User
            .withUsername("jsmith")
            .password(passwordEncoder().encode("epl425$"))
            .roles("EMPLOYEE")
            .build());
        
        userDetailsList.add(User
            .withUsername("atrevor")
            .password(passwordEncoder().encode("letmein"))
            .roles("EMPLOYEE", "MANAGER")
            .build());

        userDetailsList.add(User
            .withUsername("dalves")
            .password(passwordEncoder().encode("secure"))
            .roles("MANAGER")
            .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost"));
        configuration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL)); // e.g. GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD
        configuration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
        //configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
