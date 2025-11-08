
-- 1. Tabla Proveedor
CREATE TABLE proveedor (
    id_proveedor INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

-- 2. Tabla Producto
CREATE TABLE producto (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2),
    unidad VARCHAR(20),
    categoria VARCHAR(50),
    stock_actual INT NOT NULL DEFAULT 0
);

-- 3. Tabla Movimiento 
CREATE TABLE movimiento (
    id_movimiento INT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(10) NOT NULL,
    cantidad INT NOT NULL,
    

    id_producto INT,
    id_proveedor INT,
    
   
    CONSTRAINT fk_producto
        FOREIGN KEY (id_producto) 
        REFERENCES producto(id_producto),
    
    CONSTRAINT fk_proveedor
        FOREIGN KEY (id_proveedor) 
        REFERENCES proveedor(id_proveedor),
        
    CONSTRAINT chk_tipo_movimiento
        CHECK (tipo IN ('ENTRADA', 'SALIDA'))
);

-- Insertar 3 proveedores
INSERT INTO proveedor (id_proveedor, nombre, direccion, telefono) VALUES 
(1, 'Importaciones Globales S.A.', 'Av. Las Américas 123', '555-0101'),
(2, 'Tecno Suministros GT', 'Zona 4, C. Real', '555-0102'),
(3, 'Distribuidora Oriental', 'Ruta al Atlántico km 8', '555-0103');

-- Insertar 5 productos
INSERT INTO producto (id_producto, nombre, descripcion, precio, unidad, categoria, stock_actual) VALUES 
(101, 'Teclado Mecánico RGB', 'Teclado con switches rojos', 850.00, 'unidad', 'Periféricos', 0),
(102, 'Mouse Óptico Gamer', 'Mouse de 16000 DPI', 450.00, 'unidad', 'Periféricos', 0),
(103, 'Monitor Curvo 27"', 'Monitor 144Hz 1ms', 2800.00, 'unidad', 'Monitores', 0),
(104, 'SSD 1TB NVMe', 'Disco de estado sólido M.2', 1200.00, 'unidad', 'Almacenamiento', 0),
(105, 'Memoria RAM 16GB DDR4', 'Kit 2x8GB a 3200MHz', 900.00, 'unidad', 'Componentes', 0);

-- Insertar 10 movimientos
INSERT INTO movimiento (id_movimiento, fecha, tipo, cantidad, id_producto, id_proveedor) VALUES 
(1, '2025-11-01', 'ENTRADA', 30, 101, 1),
(2, '2025-11-01', 'ENTRADA', 50, 102, 1),
(3, '2025-11-02', 'ENTRADA', 10, 103, 2),
(4, '2025-11-03', 'ENTRADA', 20, 104, 3),
(5, '2025-11-03', 'ENTRADA', 40, 105, 2);

-- Salidas (Ventas)
INSERT INTO movimiento (id_movimiento, fecha, tipo, cantidad, id_producto, id_proveedor) VALUES 
(6, '2025-11-05', 'SALIDA', 5, 101, NULL),
(7, '2025-11-05', 'SALIDA', 10, 102, NULL),
(8, '2025-11-06', 'SALIDA', 2, 103, NULL),
(9, '2025-11-07', 'SALIDA', 8, 101, NULL),
(10, '2025-11-08', 'SALIDA', 15, 105, NULL);

UPDATE producto SET stock_actual = (30 - 5 - 8) WHERE id_producto = 101; -- Stock: 17
UPDATE producto SET stock_actual = (50 - 10) WHERE id_producto = 102;   -- Stock: 40
UPDATE producto SET stock_actual = (10 - 2) WHERE id_producto = 103;    -- Stock: 8
UPDATE producto SET stock_actual = 20 WHERE id_producto = 104;        -- Stock: 20
UPDATE producto SET stock_actual = (40 - 15) WHERE id_producto = 105;   -- Stock: 25

-- "Qué productos tienen y sus cantidades" / "Qué productos están en bodega"
SELECT nombre, stock_actual 
FROM producto 
WHERE stock_actual > 0;

-- "Historial de movimientos" (mostrando nombres)
SELECT 
    m.fecha, 
    m.tipo, 
    p.nombre AS producto, 
    m.cantidad, 
    pr.nombre AS proveedor
FROM movimiento m
JOIN producto p ON m.id_producto = p.id_producto
LEFT JOIN proveedor pr ON m.id_proveedor = pr.id_proveedor
ORDER BY m.fecha;





