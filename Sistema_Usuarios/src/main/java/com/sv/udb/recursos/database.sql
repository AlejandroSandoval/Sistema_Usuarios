CREATE DATABASE IF NOT EXISTS `sistemas_pilet`

DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE sistemas_pilet; 

CREATE TABLE IF NOT EXISTS `ROL`
(
	`codi_role` INT NOT NULL AUTO_INCREMENT,
	`nomb_role` VARCHAR(25) NOT NULL,
	`desc_role` VARCHAR(250) NOT NULL,
	`esta_role` INT NOT NULL,
	PRIMARY KEY(codi_role)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `PAGINA`
(
	`codi_pagi` INT NOT NULL AUTO_INCREMENT,
	`nomb_pagi` VARCHAR(25) NOT NULL,
	`desc_pagi` VARCHAR(250) NOT NULL,
	`esta_pagi` INT NOT NULL,
	PRIMARY KEY(codi_pagi)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `PERMISO`
(
	`codi_perm` INT NOT NULL AUTO_INCREMENT,
	`codi_role` INT NOT NULL,
	`codi_pagi` INT NOT NULL,
	`valo_perm` INT NOT NULL,
	`esta_perm` INT NOT NULL,
	PRIMARY KEY(codi_perm)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `USUARIO_ROL`
(
	`codi_usua_role` INT NOT NULL AUTO_INCREMENT,
	`codi_role` INT NOT NULL,
	`acce_usua` VARCHAR(150) NOT NULL,
	`cont_usua` VARCHAR(150) NOT NULL,
	`esta_usua_role` INT NOT NULL,
	PRIMARY KEY(codi_usua_role)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `BITACORA`
(
	`codi_bita` INT NOT NULL AUTO_INCREMENT,
	`codi_usua` INT NOT NULL,
	`nomb_bita` VARCHAR(100) NOT NULL,
	`acci_bita` VARCHAR(100) NOT NULL,
	`fech_bita` datetime NOT NULL,
	`regi_bita` INT NOT NULL,
	PRIMARY KEY(codi_bita)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `PERMISO` ADD CONSTRAINT `fk_codi_role_perm` FOREIGN KEY (`codi_role`) REFERENCES `ROL` (`codi_role`);
ALTER TABLE `PERMISO` ADD CONSTRAINT `fk_codi_pagi_perm` FOREIGN KEY (`codi_pagi`) REFERENCES `PAGINA` (`codi_pagi`);
ALTER TABLE `USUARIO_ROL` ADD CONSTRAINT `fk_codi_role_usua` FOREIGN KEY (`codi_role`) REFERENCES `ROL` (`codi_role`);

INSERT INTO rol values(1,'Super Administrador','el mero mero',1);
INSERT INTO rol values(2,'Administrador','el mero sub',1);
INSERT INTO rol values(3,'Docente','el algo',1);
INSERT INTO rol values(4,'Secretaria','el ayno',1);

INSERT INTO usuario_rol values(1,1,'alejose@gmail.com','alejo123',1);