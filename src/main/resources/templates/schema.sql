mbre_rol VARCHAR(60) UNIQUE );

INSERT INTO rol(nombre_rol) VALUES ('ADMIN');
INSERT INTO rol(nombre_rol) VALUES ('USER');

CREATE TABLE usuario_rol (
id_usuario BIGINT NOT NULL,
id_rol BIGINT NOT NULL,
PRIMARY KEY( id_usuario,id_rol),
CONSTRAINT FK_USUARIO_ROL_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON
DELETE CASCADE,
CONSTRAINT FK_USUARIO_ROL_IDROL FOREIGN KEY (id_rol) REFERENCES rol (id_rol));

CREATE TABLE comida(
id_comida BIGINT NOT NULL auto_increment PRIMARY KEY, 
nombre VARCHAR(100) NOT NULL,
pais VARCHAR(100) NOT NULL,
hiperenlace VARCHAR(100) NOT NULL,
descripcion VARCHAR(300) NOT NULL,
fecha DATE,
id_usuario BIGINT NOT NULL,
CONSTRAINT FK_COMIDA_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON
DELETE CASCADE
);

CREATE TABLE comentario(
id_comentario BIGINT NOT NULL auto_increment PRIMARY KEY,
comentario VARCHAR(300),
fecha DATE,
id_comida BIGINT NOT NULL,
id_usuario BIGINT NOT NULL,
CONSTRAINT FK_COMENTARIO_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON
DELETE CASCADE,
CONSTRAINT FK_COMENTARIO_COMIDA FOREIGN KEY (id_comida) REFERENCES comida (id_comida) ON
DELETE CASCADE
); 

CREATE TABLE usuario_comida (
id_usuario BIGINT NOT NULL,
id_comida BIGINT NOT NULL,
PRIMARY KEY( id_usuario,id_comida),
FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE,
FOREIGN KEY (id_comida) REFERENCES comida (id_comida)
);
