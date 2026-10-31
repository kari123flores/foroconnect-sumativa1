
package cl.duoc.foroconnect_publicaciones.service;

import cl.duoc.foroconnect_publicaciones.model.Categoria;
import cl.duoc.foroconnect_publicaciones.model.Publicacion;
import cl.duoc.foroconnect_publicaciones.repository.PublicacionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository repository;
    private final CategoriaService categoriaService;

    public PublicacionService(
            PublicacionRepository repository,
            CategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    // LISTAR TODAS LAS PUBLICACIONES
    public List<Publicacion> listar() {
        return repository.findAll();
    }

    // BUSCAR PUBLICACION POR ID
    public Publicacion buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Publicacion no encontrada"
                    )
                );
    }

    // CREAR PUBLICACION
    public Publicacion guardar(Publicacion publicacion) {

        validar(publicacion);

        Categoria categoria = categoriaService.buscarPorId(
                publicacion.getCategoria().getId()
        );

        publicacion.setId(null);
        publicacion.setFechaCreacion(null);
        publicacion.setCategoria(categoria);

        return repository.save(publicacion);
    }

    // ACTUALIZAR PUBLICACION
    public Publicacion actualizar(Long id, Publicacion datos) {

        Publicacion publicacion = buscarPorId(id);

        validar(datos);

        Categoria categoria = categoriaService.buscarPorId(
                datos.getCategoria().getId()
        );

        publicacion.setTitulo(datos.getTitulo());
        publicacion.setContenido(datos.getContenido());
        publicacion.setCategoria(categoria);

        return repository.save(publicacion);
    }

    // ELIMINAR PUBLICACION
    public void eliminar(Long id) {
        Publicacion publicacion = buscarPorId(id);
        repository.delete(publicacion);
    }

    // BUSCAR POR CATEGORIA
    public List<Publicacion> buscarPorCategoria(Long categoriaId) {
        categoriaService.buscarPorId(categoriaId);
        return repository.findByCategoriaId(categoriaId);
    }

    // BUSCAR POR USUARIO
    public List<Publicacion> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    // BUSCAR POR TITULO
    public List<Publicacion> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    // VALIDAR DATOS
    private void validar(Publicacion publicacion) {

        if (publicacion.getTitulo() == null ||
                publicacion.getTitulo().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El titulo es obligatorio"
            );
        }

        if (publicacion.getContenido() == null ||
                publicacion.getContenido().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El contenido es obligatorio"
            );
        }

        if (publicacion.getUsuarioId() == null ||
                publicacion.getUsuarioId() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El usuarioId es obligatorio"
            );
        }

        if (publicacion.getCategoria() == null ||
                publicacion.getCategoria().getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La categoria es obligatoria"
            );
        }
    }
}
