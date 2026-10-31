
package cl.duoc.foroconnect_publicaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth

                // CONSULTAS PUBLICAS
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/categorias/**",
                    "/api/publicaciones/**",
                    "/api/comentarios/**"
                ).permitAll()

                // ADMINISTRACION DE CATEGORIAS
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/categorias/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/categorias/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/categorias/**"
                ).hasRole("ADMIN")

                // PUBLICACIONES Y COMENTARIOS
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/publicaciones/**",
                    "/api/comentarios/**"
                ).hasAnyRole("USUARIO", "ADMIN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/publicaciones/**",
                    "/api/comentarios/**"
                ).hasAnyRole("USUARIO", "ADMIN")

                .requestMatchers(
                    HttpMethod.DELETE,
                    "/api/publicaciones/**",
                    "/api/comentarios/**"
                ).hasAnyRole("USUARIO", "ADMIN")

                .requestMatchers("/error").permitAll()

                .anyRequest().denyAll()
            )

            .httpBasic(basic -> {});

        return http.build();
    }
}
