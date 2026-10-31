
package cl.duoc.foroconnect_publicaciones.service;

import cl.duoc.foroconnect_publicaciones.model.Categoria;
import cl.duoc.foroconnect_publicaciones.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    // LISTAR TODAS LAS CATEGORIAS
    public List<Categoria> listar() {
        return repository.findAll();
    }

    // BUSCAR CATEGORIA POR ID
    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Categoria no encontrada"
                        )
                );
    }

    // CREAR CATEGORIA
    public Categoria guardar(Categoria categoria) {

        if (categoria.getNombre() == null ||
                categoria.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre es obligatorio"
            );
        }

        if (repository.findByNombre(
                categoria.getNombre()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La categoria ya existe"
            );
        }

        categoria.setId(null);
        return repository.save(categoria);
    }

    // ACTUALIZAR CATEGORIA
    public Categoria actualizar(Long id, Categoria datos) {

        Categoria categoria = buscarPorId(id);

        if (datos.getNombre() == null ||
                datos.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre es obligatorio"
            );
        }

        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());

        return repository.save(categoria);
    }

    // ELIMINAR CATEGORIA
    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        repository.delete(categoria);
    }
}
