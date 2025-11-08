package com.examen.examen.repository;

import com.examen.examen.entitis.Producto; // Asegúrate que este import sea correcto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    /**
     * Método personalizado para responder a: "Qué productos están en bodega"
     * Busca todos los productos cuyo stock sea mayor a un número.
     */
    List<Producto> findByStockActualGreaterThan(int stock);
}