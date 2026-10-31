
package cl.duoc.foroconnect_usuarios.controller;

import cl.duoc.foroconnect_usuarios.model.Usuario;
import cl.duoc.foroconnect_usuarios.service.UsuarioService;
import cl.duoc.foroconnect_usuarios.dto.LoginRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // LISTAR TODOS LOS USUARIOS
    @GetMapping
    public List<Map<String, Object>> listar() {
        return service.listar().stream()
                .map(this::respuestaUsuario)
                .toList();
    }

    // BUSCAR USUARIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                respuestaUsuario(service.buscarPorId(id))
        );
    }

    // REGISTRAR NUEVO USUARIO
    @PostMapping
    public ResponseEntity<?> registrar(
            @RequestBody Usuario usuario) {

        try {
            Usuario nuevo = service.guardar(usuario);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(respuestaUsuario(nuevo));

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(
            @PathVariable Long id,
            @RequestBody Usuario datos) {

        Usuario actualizado = service.actualizar(id, datos);

        return ResponseEntity.ok(
                respuestaUsuario(actualizado)
        );
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // INICIO DE SESION
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest datos) {

        try {
            Usuario usuario = service.iniciarSesion(
                    datos.correo(),
                    datos.contrasena()
            );

            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Inicio de sesion exitoso",
                            "usuario", respuestaUsuario(usuario)
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "Credenciales incorrectas"
                    ));
        }
    }

    // RESPUESTA SIN MOSTRAR LA CONTRASENA
    private Map<String, Object> respuestaUsuario(Usuario usuario) {
        return Map.of(
                "id", usuario.getId(),
                "nombre", usuario.getNombre(),
                "correo", usuario.getCorreo(),
                "rol", usuario.getRol()
        );
    }
}
