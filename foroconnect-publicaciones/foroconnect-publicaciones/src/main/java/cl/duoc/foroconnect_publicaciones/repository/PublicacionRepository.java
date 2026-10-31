
package cl.duoc.foroconnect_publicaciones.repository;

import cl.duoc.foroconnect_publicaciones.model.Publicacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository
        extends JpaRepository<Publicacion, Long> {

    // BUSCAR PUBLICACIONES POR CATEGORIA
    List<Publicacion> findByCategoriaId(Long categoriaId);

    // BUSCAR PUBLICACIONES POR USUARIO
    List<Publicacion> findByUsuarioId(Long usuarioId);

    // BUSCAR PUBLICACIONES POR TITULO
    List<Publicacion> findByTituloContainingIgnoreCase(String titulo);
}
