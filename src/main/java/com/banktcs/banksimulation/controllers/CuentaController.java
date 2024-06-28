package com.banktcs.banksimulation.controllers;

import com.banktcs.banksimulation.Cliente;
import com.banktcs.banksimulation.Cuenta;
import com.banktcs.banksimulation.Movimiento;
import com.banktcs.banksimulation.exception.ReachedLimitInNumberOfAccount;
import com.banktcs.banksimulation.exception.SaldoInsuficienteException;
import com.banktcs.banksimulation.repository.ClienteRepository;
import com.banktcs.banksimulation.repository.CuentaRepository;
import com.banktcs.banksimulation.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta not found"));
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + cuenta.getCliente().getId()));
        if(cliente.getList().isEmpty() || cliente.getList().size()<2) {
            cliente.getList().add(cuenta);
        } else {
            throw new ReachedLimitInNumberOfAccount("Alcanzaste el máximo número de cuentas a crear"); // or throw an exception if preferred
        }
        Cuenta newCuenta = cuentaRepository.save(cuenta);
        return ResponseEntity.ok(newCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta not found"));

        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.getEstado());

        Cuenta updatedCuenta = cuentaRepository.save(cuenta);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta not found"));
        cuentaRepository.delete(cuenta);
        return ResponseEntity.noContent().build();
    }
}
