USE social_media_database;

CREATE TABLE user_relation (

	name_user VARCHAR(255) NOT NULL,
    password_user VARCHAR(255) NOT NULL,
    email_user VARCHAR(255) NOT NULL UNIQUE,
    at_sign_user VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(at_sign_user)
);

CREATE TABLE mensage (

	value_mensage VARCHAR(255),
    id_respond INT NOT NULL UNIQUE AUTO_INCREMENT,
    id_respond_parent INT,
    at_sign_user VARCHAR(255) NOT NULL,

    PRIMARY KEY(id_respond),
    FOREIGN KEY (id_respond_parent) REFERENCES
		mensage(id_respond),
    FOREIGN KEY(at_sign_user) REFERENCES 
		user_relation(at_sign_user)
);
