package com.mypetsdiary.repository;

import com.mypetsdiary.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByUsuarioId(Long usuarioId);
}