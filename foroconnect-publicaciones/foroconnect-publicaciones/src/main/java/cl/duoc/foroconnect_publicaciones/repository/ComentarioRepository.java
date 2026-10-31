
package cl.duoc.foroconnect_publicaciones.repository;

import cl.duoc.foroconnect_publicaciones.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository
        extends JpaRepository<Comentario, Long> {

    // BUSCAR COMENTARIOS POR PUBLICACION
    List<Comentario> findByPublicacionId(Long publicacionId);

    // BUSCAR COMENTARIOS POR USUARIO
    List<Comentario> findByUsuarioId(Long usuarioId);

}
