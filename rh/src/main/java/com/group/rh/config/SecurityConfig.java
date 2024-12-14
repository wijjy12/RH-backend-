package com.group.rh.config;

import com.group.rh.service.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;


    // Configuration de la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)  // Désactivation de la protection CSRF
                .cors(Customizer.withDefaults()) // Configuration des CORS
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/public/**", "/user/employee", "/candidats","/candidats/cv/**").permitAll()
                        .requestMatchers("/adminuser/get-profile").hasAnyAuthority("ADMIN", "EMPLOYERH", "EMPLOYE", "CANDIDAT") // Ensure all relevant roles have access
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/employeRH/**").hasAnyAuthority("EMPLOYERH")
                        .requestMatchers("/employe/**").hasAnyAuthority("EMPLOYE")
                        .requestMatchers("/candidat/**").hasAnyAuthority("CANDIDAT")
                        .anyRequest().authenticated())   // Toute autre requête nécessite une authentification
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Gestion de session sans état
                .authenticationProvider(authenticationProvider()) // Configuration du fournisseur d'authentification
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Ajout du filtre JWT avant le filtre d'authentification
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);  // Définir le service des détails utilisateur
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());  // Définir l'encodeur de mot de passe
        return daoAuthenticationProvider;
    }

    // Configuration de l'encodeur de mot de passe
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // Configuration du gestionnaire d'authentification
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}














