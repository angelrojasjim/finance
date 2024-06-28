package com.banktcs.banksimulation.controllers;

import com.banktcs.banksimulation.Movimiento;
import com.banktcs.banksimulation.Cuenta;
import com.banktcs.banksimulation.exception.SaldoInsuficienteException;
import com.banktcs.banksimulation.repository.CuentaRepository;
import com.banktcs.banksimulation.repository.MovimientoRepository;
import com.banktcs.banksimulation.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento not found"));
        return ResponseEntity.ok(movimiento);
    }


    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) throws Exception {
        double nuevoSaldo = 0;
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + movimiento.getCuenta().getId()));
        if(cuenta.getList().isEmpty()) {
            nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
            if (nuevoSaldo < 0) {
                throw new SaldoInsuficienteException("Tienes saldo insuficiente para este movimiento"); // or throw an exception if preferred
            }
        } else {
            nuevoSaldo = cuenta.getList().get(cuenta.getList().size() - 1).getSaldo() + movimiento.getValor();
            if (nuevoSaldo < 0) {
                throw new SaldoInsuficienteException("Tienes saldo insuficiente para este movimiento"); // or throw an exception if preferred
            }
        }
        cuenta.getList().add(movimiento);

        movimiento.setSaldo(nuevoSaldo);

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        Movimiento newMovimiento = movimientoRepository.save(movimiento);

        return ResponseEntity.ok(newMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento not found"));

        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());

        Movimiento updatedMovimiento = movimientoRepository.save(movimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento not found"));
        return ResponseEntity.noContent().build();
    }
}
