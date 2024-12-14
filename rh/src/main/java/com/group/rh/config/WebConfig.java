
package com.group.rh.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.CorsRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permettre les requêtes CORS pour tous les chemins
                .allowedOrigins("http://127.0.0.1:5173/")  // Autoriser les requêtes provenant de cette origine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")   // Autoriser ces méthodes HTTP
                .allowedHeaders("*")  // Autoriser tous les en-têtes
                .allowCredentials(true);  // Autoriser l'envoi des informations d'identification (cookies, en-têtes d'autorisation, etc.)
    }
}



