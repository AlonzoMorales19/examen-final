package com.examen.examen.repository;

import com.examen.examen.entitis.Movimiento; // Asegúrate que este import sea correcto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    // Métodos estándar
}