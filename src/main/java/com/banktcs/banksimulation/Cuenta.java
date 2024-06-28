package com.banktcs.banksimulation;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUENTAS")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long numeroCuenta;

    @Column(nullable = false)
    private String tipoCuenta;

    @Column(nullable = false)
    private double saldoInicial;

    @Column(nullable = false)
    private String estado;

    // Default constructor
    public Cuenta() {
    }

    // Parameterized constructor
    public Cuenta(String tipoCuenta, double saldoInicial, String estado) {
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @OneToMany( mappedBy="cuenta", cascade=CascadeType.ALL)
    private List<Movimiento> list;

    @ManyToOne
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimiento> getList(){
        return list;
    }
}
