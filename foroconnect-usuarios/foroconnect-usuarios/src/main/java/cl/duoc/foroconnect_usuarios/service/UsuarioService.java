
package cl.duoc.foroconnect_usuarios.service;

import cl.duoc.foroconnect_usuarios.model.Usuario;
import cl.duoc.foroconnect_usuarios.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

        
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                    )
                 );
}

    public Usuario guardar(Usuario usuario) {

        if (repository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new IllegalArgumentException(
                "El correo ya está registrado");
        }

        usuario.setId(null);
        usuario.setRol("USUARIO");

        usuario.setContrasena(
            passwordEncoder.encode(usuario.getContrasena())
        );

        return repository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        Usuario usuario = buscarPorId(id);

        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());

        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }

    // VERIFICAR INICIO DE SESION
    public Usuario iniciarSesion(String correo, String contrasena) {

        if (correo == null || contrasena == null) {
            throw new IllegalArgumentException(
                "Credenciales incorrectas"
            );
        }

        Usuario usuario = repository.findByCorreo(correo)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Credenciales incorrectas"
                    )
                );

        if (!passwordEncoder.matches(
                contrasena, usuario.getContrasena())) {

            throw new IllegalArgumentException(
                "Credenciales incorrectas"
            );
        }

        return usuario;
    }
}
