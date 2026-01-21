package com.mypetsdiary.service;

import com.mypetsdiary.model.Usuario;
import com.mypetsdiary.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // LÓGICA DE REGISTRO (Aquí evitamos el correo duplicado)
    public void registrar(Usuario u) throws Exception {
        if(usuarioRepo.findByCorreo(u.getCorreo()).isPresent()) {
            throw new Exception("Lo sentimos, este correo ya está en uso.");
        }
        // Encriptamos la contraseña antes de guardar
        u.setContrasena(encoder.encode(u.getContrasena()));
        usuarioRepo.save(u);
    }

    // LÓGICA DE LOGIN (Requerida por Spring Security)
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(), 
                user.getContrasena(), 
                new ArrayList<>()
        );
    }
}