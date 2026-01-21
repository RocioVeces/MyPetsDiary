package com.mypetsdiary.controller;

import com.mypetsdiary.model.Usuario;
import com.mypetsdiary.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Muestra la página de login
    @GetMapping("/login")
    public String verLogin() {
        return "login";
    }

    // Muestra el formulario de registro con un objeto Usuario vacío
    @GetMapping("/registro")
    public String verRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesa los datos del registro
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.registrar(usuario);
            return "redirect:/login?success"; // Redirige al login con mensaje de éxito
        } catch (Exception e) {
            // Si el correo ya existe, el Service lanza una excepción que capturamos aquí
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}