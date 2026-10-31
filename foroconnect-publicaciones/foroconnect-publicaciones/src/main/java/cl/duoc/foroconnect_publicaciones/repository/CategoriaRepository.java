
package cl.duoc.foroconnect_publicaciones.repository;

import cl.duoc.foroconnect_publicaciones.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

}
