package com.mypetsdiary.controller;

import com.mypetsdiary.model.CitaMascota;
import com.mypetsdiary.model.Mascota;
import com.mypetsdiary.model.Usuario;
import com.mypetsdiary.repository.UsuarioRepository;
import com.mypetsdiary.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        // Obtenemos el correo del usuario logueado desde el objeto Principal de Spring Security
        String correo = principal.getName();
        Usuario usuario = usuarioRepo.findByCorreo(correo).orElseThrow();
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("mascotas", mascotaService.listarPorUsuario(usuario.getId()));
        model.addAttribute("nuevaMascota", new Mascota());
        return "index";
    }

    @PostMapping("/mascotas/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota, Principal principal) {
        Usuario usuario = usuarioRepo.findByCorreo(principal.getName()).orElseThrow();
        mascota.setUsuario(usuario);
        mascotaService.guardarMascota(mascota);
        return "redirect:/index";
    }

    @PostMapping("/citas/guardar")
    public String guardarCita(@RequestParam Long mascotaId, 
                             @RequestParam String asunto, 
                             @RequestParam String fechaCita) {
        
        Mascota m = new Mascota();
        m.setId(mascotaId);
        
        CitaMascota cita = new CitaMascota();
        cita.setAsunto(asunto);
        cita.setFechaCita(LocalDate.parse(fechaCita));
        cita.setMascota(m);
        
        mascotaService.guardarCita(cita);
        return "redirect:/index";
    }

    @GetMapping("/mascotas/borrar/{id}")
    public String borrarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "redirect:/index";
    }

    @GetMapping("/citas/borrar/{id}")
    public String borrarCita(@PathVariable Long id) {
        mascotaService.eliminarCita(id);
        return "redirect:/index";
    }
}