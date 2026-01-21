package com.mypetsdiary.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "citas_mascota")
public class CitaMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asunto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    public CitaMascota() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public LocalDate getFechaCita() { return fechaCita; }
    public void setFechaCita(LocalDate fechaCita) { this.fechaCita = fechaCita; }
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}