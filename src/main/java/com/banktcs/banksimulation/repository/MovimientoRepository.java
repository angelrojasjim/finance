package com.banktcs.banksimulation.repository;

import com.banktcs.banksimulation.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {}
