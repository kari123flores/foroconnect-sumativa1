
package cl.duoc.foroconnect_publicaciones.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class UsuarioClient {

    private final RestClient restClient;

    public UsuarioClient(
            @Value("${usuarios.service.url}") String usuariosUrl) {

        this.restClient = RestClient.builder()
                .baseUrl(usuariosUrl)
                .build();
    }

    public boolean verificarConexion() {

        try {
            restClient.get()
                    .uri("/api/usuarios")
                    .retrieve()
                    .toBodilessEntity();

            return true;

        } catch (RestClientException e) {
            return false;
        }
    }
}
