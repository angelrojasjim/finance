package com.banktcs.banksimulation.controllers;

import com.banktcs.banksimulation.Cliente;
import com.banktcs.banksimulation.repository.ClienteRepository;
import com.banktcs.banksimulation.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setNombre(clienteDetails.getNombre());
        cliente.setGenero(clienteDetails.getGenero());
        cliente.setEdad(clienteDetails.getEdad());
        cliente.setIdentificacion(clienteDetails.getIdentificacion());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setContrasena(clienteDetails.getContrasena());
        cliente.setEstado(clienteDetails.getEstado());

        Cliente updatedCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente not found"));

        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }
}
