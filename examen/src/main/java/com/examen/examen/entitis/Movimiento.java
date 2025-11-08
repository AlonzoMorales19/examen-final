package com.examen.examen.entitis;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "tipo")
    private String tipo; // "ENTRADA" o "SALIDA"

    @Column(name = "cantidad")
    private Integer cantidad;

    // Relación: Muchos movimientos pertenecen a Un Producto
    @ManyToOne
    @JoinColumn(name = "id_producto") // Esta es la columna FK en la tabla 'movimiento'
    private Producto producto;

    // Relación: Muchos movimientos (de entrada) pertenecen a Un Proveedor
    @ManyToOne
    @JoinColumn(name = "id_proveedor") // Esta es la columna FK en la tabla 'movimiento'
    private Proveedor proveedor;

    // Getters y Setters
    // ... (Omitidos por brevedad, pero debes generarlos)
}