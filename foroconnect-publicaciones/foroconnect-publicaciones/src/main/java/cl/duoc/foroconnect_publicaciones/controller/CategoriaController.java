
package cl.duoc.foroconnect_publicaciones.controller;

import cl.duoc.foroconnect_publicaciones.model.Categoria;
import cl.duoc.foroconnect_publicaciones.service.CategoriaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // LISTAR CATEGORIAS
    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    // BUSCAR CATEGORIA POR ID
    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // CREAR CATEGORIA
    @PostMapping
    public ResponseEntity<Categoria> guardar(
            @Valid @RequestBody Categoria categoria) {

        Categoria nueva = service.guardar(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    // ACTUALIZAR CATEGORIA
    @PutMapping("/{id}")
    public Categoria actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria datos) {

        return service.actualizar(id, datos);
    }

    // ELIMINAR CATEGORIA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
