package com.group.rh.config;

import com.group.rh.service.JWTUtils;
import com.group.rh.service.OurUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Récupération de l'en-tête d'autorisation
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // Vérification si l'en-tête est absent ou vide
        if(authHeader == null || authHeader.isBlank()){
            filterChain.doFilter(request, response);
            return;
        }

        // Extraction du jeton JWT en supprimant le préfixe "Bearer "
        jwtToken = authHeader.substring(7);
        // Extraction de l'email de l'utilisateur à partir du jeton
        userEmail = jwtUtils.extractUsername(jwtToken);

        // Si l'email de l'utilisateur est non nul et non authentifié
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Chargement des détails de l'utilisateur
            UserDetails userDetails = ourUserDetailsService.loadUserByUsername(userEmail);

            // Vérification de la validité du jeton JWT
            if (jwtUtils.isTokenValid(jwtToken, userDetails)){
                // Vérification de la validité du jeton JWT
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        // Passer la requête au filtre suivant dans la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}
