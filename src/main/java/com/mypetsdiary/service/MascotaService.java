package com.mypetsdiary.service;

import com.mypetsdiary.model.Mascota;
import com.mypetsdiary.model.CitaMascota;
import com.mypetsdiary.repository.MascotaRepository;
import com.mypetsdiary.repository.CitaMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepo;

    @Autowired
    private CitaMascotaRepository citaRepo;

    // LÓGICA DE FICHAS
    public List<Mascota> listarPorUsuario(Long usuarioId) {
        return mascotaRepo.findByUsuarioId(usuarioId);
    }

    public void guardarMascota(Mascota m) {
        mascotaRepo.save(m);
    }

    public void eliminarMascota(Long id) {
        mascotaRepo.deleteById(id);
    }

    // LÓGICA DE CITAS/VACUNAS DENTRO DE LA FICHA
    public void guardarCita(CitaMascota c) {
        citaRepo.save(c);
    }

    public void eliminarCita(Long id) {
        citaRepo.deleteById(id);
    }
}