﻿CREATE TABLE USUARIO(
ID_USUARIO	SERIAL PRIMARY KEY NOT NULL,
NOMBRE 		VARCHAR(20) NOT NULL,
APELLIDO 	VARCHAR(20) NOT NULL,
CEDULA 		VARCHAR(10) NOT NULL,
USUARIO		VARCHAR(10) NOT NULL,
CONTRASEÑA	VARCHAR(10) NOT NULL,
TIPO		INT NOT  NULL
);


CREATE TABLE MAQUINARIA(
CODIGO 		VARCHAR(20) NOT NULL PRIMARY KEY,
NOMBRE		VARCHAR(20) NOT NULL
);


CREATE TABLE MOLDE(
CODIGO 		VARCHAR(40) NOT NULL PRIMARY KEY,
NOMBRE		VARCHAR(20) NOT NULL 
);


CREATE TABLE TIPO_NOVEDAD(
ID_TIPONOVEDAD 	SERIAL PRIMARY KEY NOT NULL,
DESCRIPCION	VARCHAR(500) NOT NULL,
TIPO		VARCHAR(20) NOT NULL
);


CREATE TABLE TIPO_SOLUCION(
ID_TIPOSOLUCION SERIAL PRIMARY KEY NOT NULL,
DESCRIPCION 	VARCHAR(500) NOT NULL,
TIPO		VARCHAR(20) NOT NULL
);


CREATE TABLE REPORTE(
ID_REPORTE 		SERIAL PRIMARY KEY NOT NULL,
FECHA			DATE NOT NULL,
ID_MAQUINARIA		VARCHAR(20) NOT NULL,
ID_MOLDE		VARCHAR(20) NOT NULL,
ID_USUARIO		INT NOT NULL,
ID_TIPO_NOVEDAD		INT NOT NULL,
ID_TIPO_SOLUCION	INT NOT NULL,
NOVEDAD			VARCHAR(100) NOT NULL,
FOTO			BYTEA,
FOREIGN KEY (ID_MAQUINARIA) 	REFERENCES MAQUINARIA (CODIGO),
FOREIGN KEY (ID_MOLDE) 		REFERENCES MOLDE (CODIGO),
FOREIGN KEY (ID_USUARIO) 	REFERENCES USUARIO (ID_USUARIO),
FOREIGN KEY (ID_TIPO_NOVEDAD) 	REFERENCES TIPO_NOVEDAD (ID_TIPONOVEDAD),
FOREIGN KEY (ID_TIPO_SOLUCION) 	REFERENCES TIPO_SOLUCION (ID_TIPOSOLUCION)
}