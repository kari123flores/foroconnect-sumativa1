
package cl.duoc.foroconnect_publicaciones.service;

import cl.duoc.foroconnect_publicaciones.model.Comentario;
import cl.duoc.foroconnect_publicaciones.model.Publicacion;
import cl.duoc.foroconnect_publicaciones.repository.ComentarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;
    private final PublicacionService publicacionService;

    public ComentarioService(
            ComentarioRepository repository,
            PublicacionService publicacionService) {
        this.repository = repository;
        this.publicacionService = publicacionService;
    }

    // LISTAR TODOS LOS COMENTARIOS
    public List<Comentario> listar() {
        return repository.findAll();
    }

    // BUSCAR COMENTARIO POR ID
    public Comentario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Comentario no encontrado"
                    )
                );
    }

    // CREAR COMENTARIO
    public Comentario guardar(Comentario comentario) {

        validar(comentario);

        Publicacion publicacion = publicacionService.buscarPorId(
                comentario.getPublicacion().getId()
        );

        comentario.setId(null);
        comentario.setFechaCreacion(null);
        comentario.setPublicacion(publicacion);

        return repository.save(comentario);
    }

    // ACTUALIZAR COMENTARIO
    public Comentario actualizar(Long id, Comentario datos) {

        Comentario comentario = buscarPorId(id);

        if (datos.getContenido() == null ||
                datos.getContenido().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El contenido es obligatorio"
            );
        }

        comentario.setContenido(datos.getContenido());

        return repository.save(comentario);
    }

    // ELIMINAR COMENTARIO
    public void eliminar(Long id) {
        Comentario comentario = buscarPorId(id);
        repository.delete(comentario);
    }

    // BUSCAR COMENTARIOS POR PUBLICACION
    public List<Comentario> buscarPorPublicacion(Long publicacionId) {
        publicacionService.buscarPorId(publicacionId);
        return repository.findByPublicacionId(publicacionId);
    }

    // BUSCAR COMENTARIOS POR USUARIO
    public List<Comentario> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    // VALIDAR DATOS
    private void validar(Comentario comentario) {

        if (comentario.getContenido() == null ||
                comentario.getContenido().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El contenido es obligatorio"
            );
        }

        if (comentario.getUsuarioId() == null ||
                comentario.getUsuarioId() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El usuarioId es obligatorio"
            );
        }

        if (comentario.getPublicacion() == null ||
                comentario.getPublicacion().getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La publicacion es obligatoria"
            );
        }
    }
}
