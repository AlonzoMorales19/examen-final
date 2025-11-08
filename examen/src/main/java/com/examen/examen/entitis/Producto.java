package com.examen.examen.entitis;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "stock_actual")
    private Integer stockActual;

    // Relación: Un producto puede tener muchos movimientos
    @OneToMany(mappedBy = "producto")
    private Set<Movimiento> movimientos;

    // Getters y Setters
    // ... (Omitidos por brevedad, pero debes generarlos)
}