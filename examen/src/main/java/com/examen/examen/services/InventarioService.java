package com.examen.examen.services;

import com.examen.examen.entitis.Movimiento;
import com.examen.examen.entitis.Producto;
import com.examen.examen.repository.MovimientoRepository;
import com.examen.examen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    // Método para "Qué productos están en bodega"
    public List<Producto> getProductosEnBodega() {
        // Llama al método personalizado que creamos en el repository
        return productoRepository.findByStockActualGreaterThan(0);
    }
    
    // Método para "Historial de movimientos"
    public List<Movimiento> getHistorialMovimientos() {
        return movimientoRepository.findAll();
    }
    
    // Método para "Qué productos tienen y sus cantidades"
    public List<Producto> getTodosLosProductos() {
        return productoRepository.findAll();
    }
    
    /**
     * Lógica de negocio principal: 
     * Crea un movimiento y actualiza el stock del producto correspondiente.
     * @Transactional asegura que si falla la actualización de stock,
     * tampoco se guarde el movimiento (todo o nada).
     */
    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento) {
        
        // 1. Encontrar el producto afectado.
        // Usamos .orElseThrow() para lanzar un error si el producto no existe.
        Producto producto = productoRepository.findById(movimiento.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Error: Producto no encontrado con id: " + movimiento.getProducto().getIdProducto()));

        // 2. Actualizar el stock según el tipo de movimiento
        if ("ENTRADA".equals(movimiento.getTipo())) {
            int nuevoStock = producto.getStockActual() + movimiento.getCantidad();
            producto.setStockActual(nuevoStock);
        } else if ("SALIDA".equals(movimiento.getTipo())) {
            int nuevoStock = producto.getStockActual() - movimiento.getCantidad();
            // Validación para no tener stock negativo
            if (nuevoStock < 0) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
            producto.setStockActual(nuevoStock);
        } else {
            throw new RuntimeException("Tipo de movimiento no válido: " + movimiento.getTipo());
        }

        // 3. Guardar el producto actualizado (con el nuevo stock) en la BD
        productoRepository.save(producto);
        
        // 4. Asignar fecha actual y guardar el nuevo movimiento en la BD
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }
}