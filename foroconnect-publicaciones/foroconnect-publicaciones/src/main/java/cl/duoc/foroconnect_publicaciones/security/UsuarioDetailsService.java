
package cl.duoc.foroconnect_publicaciones.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        String sql = """
                SELECT CORREO, CONTRASENA, ROL
                FROM USUARIOS
                WHERE CORREO = ?
                """;

        return jdbcTemplate.query(
                sql,
                (ResultSet rs) -> {
                    if (!rs.next()) {
                        throw new UsernameNotFoundException(
                                "Usuario no encontrado: " + correo
                        );
                    }

                    try {
                        return User.withUsername(rs.getString("CORREO"))
                                .password(rs.getString("CONTRASENA"))
                                .roles(rs.getString("ROL"))
                                .build();
                    } catch (SQLException e) {
                        throw new IllegalStateException(
                                "Error al leer usuario desde Oracle", e
                        );
                    }
                },
                correo
        );
    }
}
