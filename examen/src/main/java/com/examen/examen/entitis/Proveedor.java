package com.examen.examen.entitis;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    // Relación: Un proveedor puede tener muchos movimientos (el 'mappedBy' apunta al campo 'proveedor' en la entidad Movimiento)
    @OneToMany(mappedBy = "proveedor")
    private Set<Movimiento> movimientos;

    // Getters y Setters
    // ... (Omitidos por brevedad, pero debes generarlos)
}