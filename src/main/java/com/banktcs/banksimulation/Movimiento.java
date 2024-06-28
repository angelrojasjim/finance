package com.banktcs.banksimulation;

import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String tipoMovimiento;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private double saldo;

    @ManyToOne
    private Cuenta cuenta;

    // Default constructor
    public Movimiento() {
    }

    // Parameterized constructor
    public Movimiento(Date fecha, String tipoMovimiento, double valor) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = 0;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
