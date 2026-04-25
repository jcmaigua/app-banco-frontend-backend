/**
 *
 * DDL para creación de tablas - Microservicio Clientes
 *
 * @author jmaigua
 * @version 1.0 12-04-2026
 */

-- DDL para crear el esquema DB_CLIENTES_CUENTAS
CREATE SCHEMA IF NOT EXISTS DB_CLIENTES_CUENTAS;

-- Establecer esquema
SET schema 'DB_CLIENTES_CUENTAS';


-- Tabla INFO_PERSONA

CREATE TABLE DB_CLIENTES_CUENTAS.INFO_PERSONA (
    id_persona                          INT4 NOT NULL,
    nombre                              VARCHAR(100) NOT NULL,
    apellido                            VARCHAR(100) NOT NULL,
    genero                              VARCHAR(20) NOT NULL,
    edad                                INT4 NOT NULL,
    identificacion                              VARCHAR(10) NOT NULL,
    direccion                           VARCHAR(300) NOT NULL,
    telefono                            VARCHAR(20) NOT NULL,
    estado                              VARCHAR(10) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    fecha_modificacion                  TIMESTAMP,
    persona_modificacion                VARCHAR(50),
    PRIMARY KEY (id_persona)
);

-- Secuencia para INFO_PERSONA
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_persona START 1 OWNED BY DB_CLIENTES_CUENTAS.INFO_PERSONA.id_persona;


-- Tabla INFO_CLIENTE

CREATE TABLE DB_CLIENTES_CUENTAS.INFO_CLIENTE (
    id_cliente                          INT4 NOT NULL,
    persona_id                          INT4 NOT NULL,
    contrasena                          VARCHAR(200) NOT NULL,
    estado                              VARCHAR(10) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    fecha_modificacion                  TIMESTAMP,
    persona_modificacion                VARCHAR(50),
    PRIMARY KEY (id_cliente),
    CONSTRAINT fk_cliente_persona FOREIGN KEY (persona_id) REFERENCES DB_CLIENTES_CUENTAS.INFO_PERSONA(id_persona)
);

-- Secuencia para INFO_CLIENTE
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_cliente START 1 OWNED BY DB_CLIENTES_CUENTAS.INFO_CLIENTE.id_cliente;

-- Un persona puede tener muchos registros INFO_CLIENTE (credenciales / perfiles); sin índice único en persona_id
CREATE INDEX idx_cliente_persona ON DB_CLIENTES_CUENTAS.INFO_CLIENTE(persona_id);


-- Tabla ADMI_TIPO_CUENTA
CREATE TABLE DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA (
    id_tipo_cuenta                      INT4 NOT NULL,
    nombre_cuenta                       VARCHAR(100) UNIQUE NOT NULL,
    descripcion                         VARCHAR(300),
    estado                              VARCHAR(10) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    fecha_modificacion                  TIMESTAMP,
    persona_modificacion                VARCHAR(50),
    PRIMARY KEY (id_tipo_cuenta)
);

-- Secuencia para ADMI_TIPO_CUENTA
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_tipo_cuenta START 1 OWNED BY DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.id_tipo_cuenta;


-- Tabla ADMI_TIPO_MOVIMIENTO

CREATE TABLE DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO (
    id_tipo_movimiento                  INT4 NOT NULL,
    nombre_movimiento                   VARCHAR(100) UNIQUE NOT NULL,
    descripcion                         VARCHAR(300),
    estado                              VARCHAR(10) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    fecha_modificacion                  TIMESTAMP,
    persona_modificacion                VARCHAR(50),
    PRIMARY KEY (id_tipo_movimiento)
);

-- Secuencia para ADMI_TIPO_MOVIMIENTO
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_tipo_movimiento START 1 OWNED BY DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.id_tipo_movimiento;


-- Tabla INFO_CUENTA

CREATE TABLE DB_CLIENTES_CUENTAS.INFO_CUENTA (
    id_cuenta                           INT4 NOT NULL,
    numero_cuenta                       VARCHAR(20) NOT NULL,
    tipo_cuenta_id                      INT4 NOT NULL,
    persona_id                          INT4 NOT NULL,
    saldo_inicial                       NUMERIC(15,2) NOT NULL DEFAULT 0,
    saldo_actual                        NUMERIC(15,2) NOT NULL DEFAULT 0,
    estado                              VARCHAR(10) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    fecha_modificacion                  TIMESTAMP,
    persona_modificacion                VARCHAR(50),
    PRIMARY KEY (id_cuenta),
    CONSTRAINT fk_cuenta_tipo_cuenta FOREIGN KEY (tipo_cuenta_id) REFERENCES DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA(id_tipo_cuenta),
    CONSTRAINT fk_cuenta_persona FOREIGN KEY (persona_id) REFERENCES DB_CLIENTES_CUENTAS.INFO_PERSONA(id_persona)
);

-- Secuencia para INFO_CUENTA
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_cuenta START 1 OWNED BY DB_CLIENTES_CUENTAS.INFO_CUENTA.id_cuenta;

-- Índice para búsqueda por persona titular
CREATE INDEX idx_cuenta_persona ON DB_CLIENTES_CUENTAS.INFO_CUENTA(persona_id);


-- Tabla INFO_MOVIMIENTO

CREATE TABLE DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO (
    id_movimiento                       INT4 NOT NULL,
    tipo_movimiento_id                  INT4 NOT NULL,
    cuenta_id                           INT4 NOT NULL,
    estado                              VARCHAR(10) NOT NULL,
    valor                               NUMERIC(15,2) NOT NULL,
    saldo                               NUMERIC(15,2) NOT NULL,
    fecha_creacion                      TIMESTAMP NOT NULL,
    persona_creacion                    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_movimiento),
    CONSTRAINT fk_movimiento_tipo FOREIGN KEY (tipo_movimiento_id) REFERENCES DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO(id_tipo_movimiento),
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES DB_CLIENTES_CUENTAS.INFO_CUENTA(id_cuenta)
);

-- Secuencia para INFO_MOVIMIENTO
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_movimiento START 1 OWNED BY DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.id_movimiento;

-- Índice para búsqueda por cuenta
CREATE INDEX idx_movimiento_cuenta ON DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO(cuenta_id);

-- Índice para búsqueda por fecha
CREATE INDEX idx_movimiento_fecha ON DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO(fecha_creacion);


-- Tabla ADMI_CONFIGURATION_PARAMETER_CAB
CREATE TABLE DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB (
    id_configuration_parameter_cab      INT4 NOT NULL,
    name                                VARCHAR(255) UNIQUE NOT NULL,
    description                         VARCHAR(300) NOT NULL,
    status                              VARCHAR(10) NOT NULL,
    creation_user                       VARCHAR(50) NOT NULL,
    creation_date                       TIMESTAMP NOT NULL,
    PRIMARY KEY (id_configuration_parameter_cab)
);

-- Secuencia para ADMI_CONFIGURATION_PARAMETER_CAB
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_configuration_parameter_cab START 1 OWNED BY DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.id_configuration_parameter_cab;

-- Tabla ADMI_CONFIGURATION_PARAMETER_DET
CREATE TABLE DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET (
    id_configuration_parameter_det      INT4 NOT NULL,
    configuration_parameter_cab_id      INT4 NOT NULL,
    value                               VARCHAR(255) NOT NULL,
    status                              VARCHAR(10) NOT NULL,
    creation_user                       VARCHAR(50) NOT NULL,
    creation_date                       TIMESTAMP NOT NULL,
    PRIMARY KEY (id_configuration_parameter_det),
    CONSTRAINT fk_cab FOREIGN KEY (configuration_parameter_cab_id) REFERENCES DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB(id_configuration_parameter_cab)
);

-- Secuencia para ADMI_CONFIGURATION_PARAMETER_DET
CREATE SEQUENCE DB_CLIENTES_CUENTAS.seq_configuration_parameter_det START 1 OWNED BY DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.id_configuration_parameter_det;


-- COMENTARIOS DE LOS CAMPOS DE LAS TABLAS

-- Tabla: INFO_PERSONA
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.id_persona IS 'Identificación numérica del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.nombre IS 'Nombre del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.apellido IS 'Apellido del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.genero IS 'Género del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.edad IS 'Edad del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.identificacion IS 'Identificación del persona (10 caracteres)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.direccion IS 'Dirección domiciliaria del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.telefono IS 'Número de teléfono del persona';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.estado IS 'Estado del registro (True/False)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.fecha_creacion IS 'Fecha de creación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.persona_creacion IS 'Persona que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.fecha_modificacion IS 'Fecha de última modificación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_PERSONA.persona_modificacion IS 'Persona que realizó la última modificación';

-- Tabla: INFO_CLIENTE
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.id_cliente IS 'Identificación numérica del cliente';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.persona_id IS 'FK persona (relación 1:N: un persona, muchos clientes)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.contrasena IS 'Contraseña del cliente';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.estado IS 'Estado del registro (True/False)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.fecha_creacion IS 'Fecha de creación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.persona_creacion IS 'Persona que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.fecha_modificacion IS 'Fecha de última modificación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CLIENTE.persona_modificacion IS 'Persona que realizó la última modificación';

-- Tabla: ADMI_TIPO_CUENTA
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.id_tipo_cuenta IS 'Identificación numérica del tipo de cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.nombre_cuenta IS 'Nombre del tipo de cuenta (Ahorros, Corriente)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.descripcion IS 'Descripción del tipo de cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.estado IS 'Estado del registro (True/False)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.fecha_creacion IS 'Fecha de creación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.persona_creacion IS 'Persona que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.fecha_modificacion IS 'Fecha de última modificación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA.persona_modificacion IS 'Persona que realizó la última modificación';

-- Tabla: ADMI_TIPO_MOVIMIENTO
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.id_tipo_movimiento IS 'Identificación numérica del tipo de movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.nombre_movimiento IS 'Nombre del tipo de movimiento (Depósito, Retiro)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.descripcion IS 'Descripción del tipo de movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.estado IS 'Estado del registro (True/False)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.fecha_creacion IS 'Fecha de creación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.persona_creacion IS 'Persona que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.fecha_modificacion IS 'Fecha de última modificación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO.persona_modificacion IS 'Persona que realizó la última modificación';

-- Tabla: INFO_CUENTA
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.id_cuenta IS 'Identificación numérica de la cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.numero_cuenta IS 'Número de cuenta visible para el cliente';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.tipo_cuenta_id IS 'FK de la identificación numérica del tipo de cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.persona_id IS 'FK persona titular de la cuenta (1:N persona → cuentas)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.saldo_inicial IS 'Saldo inicial al momento de crear la cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.saldo_actual IS 'Saldo disponible actual de la cuenta';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.estado IS 'Estado del registro (True/False)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.fecha_creacion IS 'Fecha de creación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.persona_creacion IS 'Persona que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.fecha_modificacion IS 'Fecha de última modificación del registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_CUENTA.persona_modificacion IS 'Persona que realizó la última modificación';

-- Tabla: INFO_MOVIMIENTO
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.id_movimiento IS 'Identificación numérica del movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.tipo_movimiento_id IS 'FK de la identificación numérica del tipo de movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.cuenta_id IS 'FK de la identificación numérica de la cuenta asociada';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.valor IS 'Valor del movimiento (positivo para depósito, negativo para retiro)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.saldo IS 'Saldo resultante después del movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.fecha_creacion IS 'Fecha de creación del movimiento';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.INFO_MOVIMIENTO.persona_creacion IS 'Persona que creó el registro';

-- Tabla: ADMI_CONFIGURATION_PARAMETER_CAB
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.id_configuration_parameter_cab IS 'Identificación numérica del parámetro de configuración (cabecera)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.name IS 'Nombre del parámetro de configuración';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.description IS 'Descripción del parámetro de configuración';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.status IS 'Estado del parámetro de configuración';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.creation_user IS 'Usuario que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB.creation_date IS 'Fecha de creación del registro';

-- Tabla: ADMI_CONFIGURATION_PARAMETER_DET
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.id_configuration_parameter_det IS 'Identificación numérica del parámetro de configuración (detalle)';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.configuration_parameter_cab_id IS 'Identificación numérica del parámetro de configuración (cabecera) asociado';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.value IS 'Valor del parámetro de configuración';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.status IS 'Estado del parámetro de configuración';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.creation_user IS 'Usuario que creó el registro';
COMMENT ON COLUMN DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET.creation_date IS 'Fecha de creación del registro';


-- Datos mínimos para pruebas (tipos de cuenta y movimiento)
INSERT INTO DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA (id_tipo_cuenta, nombre_cuenta, descripcion, estado, fecha_creacion, persona_creacion)
VALUES (1, 'Ahorros', 'Cuenta de ahorros', 'ACTIVO', NOW(), 'Juan Maigua')
ON CONFLICT (id_tipo_cuenta) DO NOTHING;
INSERT INTO DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA (id_tipo_cuenta, nombre_cuenta, descripcion, estado, fecha_creacion, persona_creacion)
VALUES (2, 'Corriente', 'Cuenta corriente', 'ACTIVO', NOW(), 'Juan Maigua')
ON CONFLICT (id_tipo_cuenta) DO NOTHING;
SELECT setval('DB_CLIENTES_CUENTAS.seq_tipo_cuenta', (SELECT COALESCE(MAX(id_tipo_cuenta),1) FROM DB_CLIENTES_CUENTAS.ADMI_TIPO_CUENTA));

INSERT INTO DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO (id_tipo_movimiento, nombre_movimiento, descripcion, estado, fecha_creacion, persona_creacion)
VALUES (1, 'Deposito', 'Depósito', 'ACTIVO', NOW(), 'Juan Maigua')
ON CONFLICT (id_tipo_movimiento) DO NOTHING;
INSERT INTO DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO (id_tipo_movimiento, nombre_movimiento, descripcion, estado, fecha_creacion, persona_creacion)
VALUES (2, 'Retiro', 'Retiro', 'ACTIVO', NOW(), 'Juan Maigua')
ON CONFLICT (id_tipo_movimiento) DO NOTHING;
SELECT setval('DB_CLIENTES_CUENTAS.seq_tipo_movimiento', (SELECT COALESCE(MAX(id_tipo_movimiento),1) FROM DB_CLIENTES_CUENTAS.ADMI_TIPO_MOVIMIENTO));


INSERT INTO DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB (
    id_configuration_parameter_cab, 
    name, 
    description,
    status, 
    creation_user, 
    creation_date)
    VALUES (
        nextval('DB_CLIENTES_CUENTAS.seq_configuration_parameter_cab'), 
        'MAXIMO_TRANSACCION_DIARIA', 
        'MAXIMO_TRANSACCION_DIARIA', 
        'ACTIVO', 
        'jmaigua', 
        current_timestamp
    );

INSERT INTO DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_DET (
    id_configuration_parameter_det, 
    configuration_parameter_cab_id,
    value, 
    status, 
    creation_user, 
    creation_date)
    VALUES (
        nextval('DB_CLIENTES_CUENTAS.seq_configuration_parameter_det'), 
        (
            SELECT
                id_configuration_parameter_cab
            FROM
                DB_CLIENTES_CUENTAS.ADMI_CONFIGURATION_PARAMETER_CAB
            WHERE
                name = 'MAXIMO_TRANSACCION_DIARIA'
        ),
        '1000', 
        'ACTIVO', 
        'jmaigua', 
        current_timestamp
    );
