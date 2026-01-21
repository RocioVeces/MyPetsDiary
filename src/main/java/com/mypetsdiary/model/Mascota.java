package com.mypetsdiary.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaMascota> citas;

    public Mascota() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public List<CitaMascota> getCitas() { return citas; }
    public void setCitas(List<CitaMascota> citas) { this.citas = citas; }
}