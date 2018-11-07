create database templateconnexion;

create table utilisateur(
						id INT(11) NOT NULL AUTO_INCREMENT,
            email VARCHAR(127),
						password VARCHAR(64),
						PRIMARY KEY(id),
						UNIQUE(email)
						);