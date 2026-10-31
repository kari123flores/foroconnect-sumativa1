
package cl.duoc.foroconnect_usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http
            .csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth

                // REGISTRO PUBLICO
                .requestMatchers(
                    HttpMethod.POST, "/api/usuarios"
                ).permitAll()

                // LOGIN PUBLICO
                .requestMatchers(
                    HttpMethod.POST, "/api/usuarios/login"
                ).permitAll()

                // SOLO ADMIN PUEDE ELIMINAR USUARIOS
                .requestMatchers(
                    HttpMethod.DELETE, "/api/usuarios/**"
                ).hasRole("ADMIN")

                // SOLO ADMIN PUEDE MODIFICAR USUARIOS
                .requestMatchers(
                    HttpMethod.PUT, "/api/usuarios/**"
                ).hasRole("ADMIN")

                // USUARIOS AUTENTICADOS PUEDEN CONSULTAR
                .requestMatchers(
                    HttpMethod.GET, "/api/usuarios/**"
                ).hasAnyRole("USUARIO", "ADMIN")

                // PERMITIR QUE SPRING MUESTRE LOS ERRORES
                .requestMatchers("/error").permitAll()

                // BLOQUEAR OTRAS SOLICITUDES
                .anyRequest().denyAll()
            )

            .httpBasic(basic -> {});

        return http.build();
    }
}
