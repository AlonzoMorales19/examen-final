package com.examen.examen.repository;

import com.examen.examen.entitis.Proveedor; // Asegúrate que este import sea correcto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    // Spring Data JPA crea los métodos findAll(), findById(), save(), delete(), etc.
}