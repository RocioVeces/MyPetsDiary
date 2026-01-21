package com.mypetsdiary.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    private String contrasena;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;

    public Usuario() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}