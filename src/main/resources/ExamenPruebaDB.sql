CREATE SCHEMA examenPrueba;
use examenPrueba;

CREATE TABLE usuario (
id_usuario BIGINT PRIMARY KEY AUTO_INCREMENT,
nombre_usuario VARCHAR(60) UNIQUE,
contrasena VARCHAR(60) NOT NULL);

CREATE TABLE rol (
id_rol BIGINT PRIMARY KEY AUTO_INCREMENT,
nombre_rol VARCHAR(60) UNIQUE );

CREATE TABLE comida(
id_comida bigint not null auto_increment primary key,
nombre varchar(40) not null,
pais varchar(40) not null,
hiperenlace varchar(100) not null,
descripcion varchar(100) not null,
fecha date null
);

CREATE TABLE usuario_rol (
id_usuario BIGINT NOT NULL,
id_rol BIGINT NOT NULL,
PRIMARY KEY(id_usuario,id_rol),
CONSTRAINT FK_USUARIO_ROL_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE,
CONSTRAINT FK_USUARIO_ROL_IDROL FOREIGN KEY (id_rol) REFERENCES rol (id_rol));

CREATE TABLE usuario_comida (
id_usuario BIGINT NOT NULL,
id_comida BIGINT NOT NULL,
PRIMARY KEY(id_usuario,id_comida),
CONSTRAINT FK_USUARIO_COMIDA_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE,
CONSTRAINT FK_USUARIO_COMIDA_IDCOMIDA FOREIGN KEY (id_comida) REFERENCES comida (id_comida));

CREATE TABLE comentario(
id_comentario bigint not null auto_increment primary key,
id_comida bigint not null, 
id_usuario bigint not null,
comentario varchar(200) not null,
fecha date not null,
CONSTRAINT FK_COMENTARIO_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE,
CONSTRAINT FK_COMENTARIO_COMIDA FOREIGN KEY (id_comida) REFERENCES comida (id_comida) ON DELETE CASCADE
);
INSERT INTO rol(nombre_rol) VALUES ("ADMIN");
INSERT INTO rol(nombre_rol) VALUES ("USER");

/*INSERT INTO comentario(id_comida,id_usuario,comentario,fecha) VALUES(1,1,"este es un comentario",curdate());*/