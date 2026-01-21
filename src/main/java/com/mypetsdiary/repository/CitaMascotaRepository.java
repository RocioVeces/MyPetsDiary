package com.mypetsdiary.repository;

import com.mypetsdiary.model.CitaMascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaMascotaRepository extends JpaRepository<CitaMascota, Long> {
}