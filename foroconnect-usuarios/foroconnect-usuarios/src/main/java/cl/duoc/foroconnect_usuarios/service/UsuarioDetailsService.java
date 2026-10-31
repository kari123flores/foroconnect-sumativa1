
package cl.duoc.foroconnect_usuarios.service;

import cl.duoc.foroconnect_usuarios.model.Usuario;
import cl.duoc.foroconnect_usuarios.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        Usuario usuario = repository.findByCorreo(correo)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        System.out.println("ROL DEL USUARIO: " + usuario.getRol());
        
        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles(usuario.getRol())
                .build();
    }
}
