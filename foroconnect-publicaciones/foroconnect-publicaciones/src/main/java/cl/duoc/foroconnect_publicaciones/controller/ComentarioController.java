
package cl.duoc.foroconnect_publicaciones.controller;

import cl.duoc.foroconnect_publicaciones.model.Comentario;
import cl.duoc.foroconnect_publicaciones.service.ComentarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    // LISTAR TODOS LOS COMENTARIOS
    @GetMapping
    public List<Comentario> listar() {
        return service.listar();
    }

    // BUSCAR COMENTARIO POR ID
    @GetMapping("/{id}")
    public Comentario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // CREAR COMENTARIO
    @PostMapping
    public ResponseEntity<Comentario> guardar(
            @Valid @RequestBody Comentario comentario) {

        Comentario nuevo = service.guardar(comentario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    
    // ACTUALIZAR COMENTARIO
    @PutMapping("/{id}")
    public Comentario actualizar(
            @PathVariable Long id,
            @RequestBody Comentario datos) {

        return service.actualizar(id, datos);
    }


    // ELIMINAR COMENTARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR COMENTARIOS POR PUBLICACION
    @GetMapping("/publicacion/{publicacionId}")
    public List<Comentario> buscarPorPublicacion(
            @PathVariable Long publicacionId) {

        return service.buscarPorPublicacion(publicacionId);
    }

    // BUSCAR COMENTARIOS POR USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public List<Comentario> buscarPorUsuario(
            @PathVariable Long usuarioId) {

        return service.buscarPorUsuario(usuarioId);
    }
}
