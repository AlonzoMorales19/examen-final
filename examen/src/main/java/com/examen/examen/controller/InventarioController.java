package com.examen.examen.controller;

import com.examen.examen.entitis.Movimiento;
import com.examen.examen.entitis.Producto;
import com.examen.examen.entitis.Proveedor;
import com.examen.examen.repository.ProveedorRepository;
import com.examen.examen.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // La URL base para toda tu API (ej: http://localhost:8080/api)
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Inyectamos este repositorio directo porque solo es para un 'findAll()' simple
    @Autowired
    private ProveedorRepository proveedorRepository;

    // --- Endpoints para responder a las preguntas ---
    
    /**
     * Responde a: "Qué productos tienen y sus cantidades"
     * URL: GET http://localhost:8080/api/productos
     */
    @GetMapping("/productos")
    public List<Producto> getTodosLosProductos() {
        return inventarioService.getTodosLosProductos();
    }
    
    /**
     * Responde a: "Qué productos están en bodega"
     * URL: GET http://localhost:8080/api/productos/en-bodega
     */
    @GetMapping("/productos/en-bodega")
    public List<Producto> getProductosEnBodega() {
        return inventarioService.getProductosEnBodega();
    }
    
    /**
     * Responde a: "Historial de movimientos"
     * URL: GET http://localhost:8080/api/movimientos
     */
    @GetMapping("/movimientos")
    public List<Movimiento> getHistorialMovimientos() {
        return inventarioService.getHistorialMovimientos();
    }

    /**
     * Responde a: "De qué proveedores vienen" (mostrando todos los proveedores)
     * URL: GET http://localhost:8080/api/proveedores
     */
    @GetMapping("/proveedores")
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }
    
    // --- Endpoint para crear ---

    /**
     * Crea un nuevo movimiento (ENTRADA o SALIDA).
     * Esto actualiza el stock del producto automáticamente.
     * URL: POST http://localhost:8080/api/movimientos
     *
     * JSON de ejemplo para enviar en el Body de Postman:
     * {
     * "idMovimiento": 11,
     * "tipo": "SALIDA",
     * "cantidad": 5,
     * "producto": { "idProducto": 101 },
     * "proveedor": null
     * }
     */
    @PostMapping("/movimientos")
    public ResponseEntity<?> crearMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = inventarioService.crearMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (RuntimeException e) {
            // Manejo de errores (ej. "Stock insuficiente" o "Producto no encontrado")
            return ResponseEntity.badRequest().body(e.getMessage()); 
        }
    }
}