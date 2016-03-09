CREATE OR REPLACE FUNCTION INSERTAR_USUARIO(VARCHAR,VARCHAR,VARCHAR,VARCHAR,VARCHAR,INTEGER) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	CONTAR := COUNT(NOMBRE) FROM USUARIO WHERE USUARIO.CEDULA = $3;

	IF CONTAR = 0 THEN
		INSERT INTO USUARIO(NOMBRE,APELLIDO,CEDULA,USUARIO,CONTRASEÑA,TIPO) VALUES ($1,$2,$3,$4,$5,$6);
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION INSERTAR_MAQUINARIA(VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	CONTAR := COUNT(NOMBRE) FROM MAQUINARIA WHERE MAQUINARIA.CODIGO = $1;

	IF CONTAR = 0 THEN
		INSERT INTO MAQUINARIA(CODIGO,NOMBRE) VALUES ($1,$2);
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION INSERTAR_MOLDE(VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	CONTAR := COUNT(NOMBRE) FROM MOLDE WHERE MOLDE.CODIGO = $1;

	IF CONTAR = 0 THEN
		INSERT INTO MOLDE(CODIGO,NOMBRE) VALUES ($1,$2);
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION AUTENTICACION(VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN
	CONTAR := COUNT(*) FROM USUARIO WHERE USUARIO.USUARIO = $1 AND USUARIO.CONTRASEÑA = $2;
	IF CONTAR = 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION TODAS_MAQUINARIAS() RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM MAQUINARIA;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION TODOS_MOLDES() RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM MOLDE;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION TODOS_USUARIOS() RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM USUARIO;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION BUSCAR_USUARIO(VARCHAR) RETURNS INTEGER AS $$
DECLARE
	ID INTEGER;
BEGIN	
	ID := (ID_USUARIO) FROM USUARIO WHERE USUARIO.USUARIO = $1;
	IF ID!=0 THEN
		RETURN ID;
	ELSE
		ID := 0;
		RETURN ID;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION BUSCAR_MAQUINA(VARCHAR) RETURNS INTEGER AS $$
DECLARE
	ID INTEGER;
BEGIN	
	ID := (ID_MAQUINARIA) FROM MAQUINARIA WHERE MAQUINARIA.CODIGO = $1;
	IF ID!=0 THEN
		RETURN ID;
	ELSE
		ID := 0;
		RETURN ID;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION BUSCAR_MOLDE(VARCHAR) RETURNS INTEGER AS $$
DECLARE
	ID INTEGER;
BEGIN	
	ID := (ID_MOLDE) FROM MOLDE WHERE MOLDE.CODIGO = $1;
	IF ID!=0 THEN
		RETURN ID;
	ELSE
		ID := 0;
		RETURN ID;
	END IF;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION INSERTAR_REPORTE(DATE,INTEGER,INTEGER,INTEGER,VARCHAR,VARCHAR,VARCHAR,VARCHAR,VARCHAR,BYTEA,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	ID INTEGER;
BEGIN	
	INSERT INTO REPORTE(FECHA,ID_MAQUINARIA,ID_MOLDE,ID_USUARIO,DESCRIPCION_NOVEDAD,TIPO_NOVEDAD,DESCRIPCION_SOLUCION,TIPO_SOLUCION,NOVEDAD,FOTO,REVISADO) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11);
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION INSERTAR_REPORTE_MOLDE(DATE,INTEGER,INTEGER,INTEGER,VARCHAR,VARCHAR,VARCHAR,VARCHAR,VARCHAR,BYTEA,VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	ID INTEGER;
BEGIN	
	INSERT INTO REPORTE_MOLDE(FECHA,ID_MAQUINARIA,ID_MOLDE,ID_USUARIO,DESCRIPCION_NOVEDAD,TIPO_NOVEDAD,DESCRIPCION_SOLUCION,TIPO_SOLUCION,NOVEDAD,FOTO,ARTICULO,REVISOR) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12);
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION TODOS_REPORTES() RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM REPORTE;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION BUSCAR_USUARIO_ID(INTEGER) RETURNS VARCHAR AS $$
DECLARE
	ID VARCHAR;
BEGIN	
	ID := (NOMBRE || ' ' || APELLIDO) FROM USUARIO WHERE USUARIO.ID_USUARIO = $1;
	RETURN ID;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION BUSCAR_MAQUINA_ID(INTEGER) RETURNS VARCHAR AS $$
DECLARE
	ID VARCHAR;
BEGIN	
	ID := (CODIGO || ' ' || NOMBRE) FROM MAQUINARIA WHERE MAQUINARIA.ID_MAQUINARIA = $1;
	RETURN ID;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION BUSCAR_MOLDE_ID(INTEGER) RETURNS VARCHAR AS $$
DECLARE
	ID VARCHAR;
BEGIN	
	ID := (CODIGO || ' ' || NOMBRE) FROM MOLDE WHERE MOLDE.ID_MOLDE = $1;
	RETURN ID;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_SOLO_MAQUINARIA(varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where maquinaria.codigo like $1||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION CONSULTA_SOLO_MOLDE(varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where molde.codigo like $1||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_SOLO_USUARIO(varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION CONSULTA_SOLO_NOVEDAD(varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where reporte.tipo_novedad like $1||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_MAQUINARIA(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and maquinaria.codigo like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_MOLDE(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and molde.codigo like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_NOVEDAD(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and reporte.tipo_novedad like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_MAQUINARIA_MOLDE(varchar, varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and maquinaria.codigo like $2||'%' and molde.codigo like $3||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_MAQUINARIA_NOVEDAD(varchar, varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and maquinaria.codigo like $2||'%' and reporte.tipo_novedad like $3||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION CONSULTA_USUARIO_MOLDE_NOVEDAD(varchar, varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and molde.codigo like $2||'%' and reporte.tipo_novedad like $3||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION CONSULTA_MAQUINARIA_MOLDE(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where maquinaria.codigo like $1||'%' and molde.codigo like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION CONSULTA_MAQUINARIA_NOVEDAD(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where maquinaria.codigo like $1||'%' and reporte.tipo_novedad like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_MAQUINARIA_MOLDE_NOVEDAD(varchar, varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where maquinaria.codigo like $1||'%' and molde.codigo like $2||'%' and reporte.tipo_novedad like $3||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION CONSULTA_MOLDE_NOVEDAD(varchar, varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where molde.codigo like $1||'%' and reporte.tipo_novedad like $2||'%';
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION TODOS_REPORTES_FECHAS (varchar) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM reporte 
	WHERE reporte.fecha = cast($1 as DATE);
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION consulta_reporte_fecha_por_mes(character varying)
  RETURNS refcursor AS
$BODY$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR select * from
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde 
	where cast(reporte.fecha as VARCHAR) between $1 and $1;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION DATOS_MAQUINARIA(INTEGER) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM MAQUINARIA WHERE MAQUINARIA.ID_MAQUINARIA = $1;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION ACTUALIZAR_MAQUINARIA(INTEGER,VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	UPDATE MAQUINARIA SET CODIGO=$2, NOMBRE=$3 WHERE MAQUINARIA.ID_MAQUINARIA = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION ELIMINAR_MAQUINARIA(INTEGER) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	DELETE FROM MAQUINARIA WHERE MAQUINARIA.ID_MAQUINARIA = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION DATOS_MOLDE(INTEGER) RETURNS REFCURSOR AS $$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * FROM MOLDE WHERE MOLDE.ID_MOLDE = $1;
	RETURN MYCURS;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION ELIMINAR_MOLDE(INTEGER) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	DELETE FROM MOLDE WHERE MOLDE.ID_MOLDE = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION ACTUALIZAR_MOLDE(INTEGER,VARCHAR,VARCHAR) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	UPDATE MOLDE SET CODIGO=$2, NOMBRE=$3 WHERE MOLDE.ID_MOLDE = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;

-- Function: todos_usuario_nombre()

-- DROP FUNCTION todos_usuario_nombre();

CREATE OR REPLACE FUNCTION todos_usuario_nombre()
  RETURNS refcursor AS
$BODY$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT NOMBRE FROM USUARIO;
	RETURN MYCURS;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION todos_usuario_nombre()
  OWNER TO postgres;
  
  CREATE OR REPLACE FUNCTION consulta_usuario_maquinaria_molde_novedad(
    character varying,
    character varying,
    character varying,
    character varying)
  RETURNS refcursor AS
$BODY$
DECLARE
	MYCURS REFCURSOR;
BEGIN
	OPEN MYCURS FOR SELECT * from 
	usuario inner join reporte 
	on usuario.id_usuario = reporte.id_usuario 
	inner join maquinaria 
	on maquinaria.id_maquinaria = reporte.id_maquinaria
	inner join molde
	on molde.id_molde = reporte.id_molde
	where usuario.nombre like $1||'%' and maquinaria.codigo like $2||'%' and molde.codigo like $3||'%' and reporte.tipo_novedad like $4||'%';
	RETURN MYCURS;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  
  CREATE OR REPLACE FUNCTION ELIMINAR_USUARIO(INTEGER) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	DELETE FROM USUARIO WHERE USUARIO.ID_USUARIO = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;



CREATE OR REPLACE FUNCTION ACTUALIZAR_USUARIO(INTEGER,VARCHAR,VARCHAR,VARCHAR,VARCHAR,VARCHAR,INT) RETURNS BOOLEAN AS $$
DECLARE
	CONTAR INTEGER;
BEGIN	
	UPDATE USUARIO SET NOMBRE=$2, APELLIDO=$3, CEDULA=$4, USUARIO=$5, CONTRASEÑA=$6, TIPO=$7 WHERE USUARIO.ID_USUARIO = $1;
	RETURN TRUE;
END;
$$ LANGUAGE PLPGSQL;