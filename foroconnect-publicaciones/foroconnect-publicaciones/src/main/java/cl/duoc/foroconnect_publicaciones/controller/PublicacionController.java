
package cl.duoc.foroconnect_publicaciones.controller;

import cl.duoc.foroconnect_publicaciones.model.Publicacion;
import cl.duoc.foroconnect_publicaciones.service.PublicacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService service;

    public PublicacionController(PublicacionService service) {
        this.service = service;
    }

    // LISTAR TODAS LAS PUBLICACIONES
    @GetMapping
    public List<Publicacion> listar() {
        return service.listar();
    }

    // BUSCAR PUBLICACION POR ID
    @GetMapping("/{id}")
    public Publicacion buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // CREAR PUBLICACION
    @PostMapping
    public ResponseEntity<Publicacion> guardar(
            @Valid @RequestBody Publicacion publicacion) {

        Publicacion nueva = service.guardar(publicacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    // ACTUALIZAR PUBLICACION
    @PutMapping("/{id}")
    public Publicacion actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Publicacion datos) {

        return service.actualizar(id, datos);
    }

    // ELIMINAR PUBLICACION
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // BUSCAR PUBLICACIONES POR CATEGORIA
    @GetMapping("/categoria/{categoriaId}")
    public List<Publicacion> buscarPorCategoria(
            @PathVariable Long categoriaId) {

        return service.buscarPorCategoria(categoriaId);
    }

    // BUSCAR PUBLICACIONES POR USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public List<Publicacion> buscarPorUsuario(
            @PathVariable Long usuarioId) {

        return service.buscarPorUsuario(usuarioId);
    }

    // BUSCAR PUBLICACIONES POR TITULO
    @GetMapping("/buscar")
    public List<Publicacion> buscarPorTitulo(
            @RequestParam String titulo) {

        return service.buscarPorTitulo(titulo);
    }
}
