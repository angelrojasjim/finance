package com.banktcs.banksimulation;


import jakarta.persistence.*;

import java.util.List;

@Entity
@SequenceGenerator(name = "persona_seq", sequenceName = "persona_seq", allocationSize = 1)
public class Cliente extends Persona {


    @Column(nullable = false, unique = true)
    private String contrasena;

    @Column(nullable = false)
    private String estado;

    // Default constructor
    public Cliente() {
    }

    // Parameterized constructor
    public Cliente(String nombre, String genero, int edad, Long identificacion, String direccion, String telefono,
                   String contrasena, String estado) {
        super.setNombre(nombre);
        super.setGenero(genero);
        super.setEdad(edad);
        super.setIdentificacion(identificacion);
        super.setDireccion(direccion);
        super.setTelefono(telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    @OneToMany( mappedBy="cliente", cascade=CascadeType.ALL)
    private List<Cuenta> list;


    // Getters and setters

    public List<Cuenta> getList(){
        return list;
    }
    public Long getClienteid() {
        return getId();
    }

    public void setClienteid(Long clienteid) {
        this.setId(clienteid);
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
