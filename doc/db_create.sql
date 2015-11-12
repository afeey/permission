FLUSH TABLES;
DROP TABLE IF EXISTS `sys_role_resource`;
DROP TABLE IF EXISTS `sys_resource`;
DROP TABLE IF EXISTS `sys_user_role`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_login_log`;
DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE sys_user (
 id CHAR(36) NOT NULL,
 user_name VARCHAR(40) DEFAULT '' NOT NULL,
 password VARCHAR(50) DEFAULT '' NOT NULL,
 cellphone VARCHAR(20) DEFAULT '' NOT NULL,
 mail VARCHAR(60) DEFAULT '' NOT NULL,
 money DECIMAL(10,2) DEFAULT 0.00 NOT NULL,
 alias VARCHAR(40) DEFAULT '' NOT NULL,
 full_name VARCHAR(60) DEFAULT '' NOT NULL,
 sex INT DEFAULT 0 NOT NULL,
 birthday DATETIME DEFAULT '1900-01-01 00:00:00' NOT NULL,
 id_card CHAR(18) DEFAULT '' NOT NULL,
 reg_time DATETIME NOT NULL,
 validate INT DEFAULT 0 NOT NULL,
 login_time DATETIME DEFAULT '1900-01-01 00:00:00' NOT NULL,
 login_ip CHAR(15) DEFAULT '' NOT NULL,
 login_times INT DEFAULT 0 NOT NULL,
 status INT DEFAULT 0 NOT NULL
);

ALTER TABLE sys_user ADD CONSTRAINT PK_sys_user PRIMARY KEY (id);


CREATE TABLE sys_login_log (
 id CHAR(36) NOT NULL,
 user_id CHAR(36) NOT NULL,
 user_name VARCHAR(40),
 login_time DATETIME NOT NULL,
 login_ip CHAR(15) NOT NULL
);

ALTER TABLE sys_login_log ADD CONSTRAINT PK_sys_login_log PRIMARY KEY (id);


CREATE TABLE sys_role (
 id CHAR(36) NOT NULL,
 name VARCHAR(40) DEFAULT '' NOT NULL,
 code VARCHAR(40) DEFAULT '' NOT NULL,
 comment VARCHAR(200) DEFAULT '' NOT NULL,
 status TINYINT DEFAULT 0 NOT NULL,
 sort INT DEFAULT 0 NOT NULL,
 create_at DATETIME NOT NULL,
 update_at DATETIME NOT NULL,
 permissions TEXT
);

ALTER TABLE sys_role ADD CONSTRAINT PK_sys_role PRIMARY KEY (id);


CREATE TABLE sys_resource (
 id CHAR(36) NOT NULL,
 parent_id CHAR(36) DEFAULT '' NOT NULL,
 name VARCHAR(100) DEFAULT '' NOT NULL,
 type TINYINT DEFAULT 0 NOT NULL,
 url VARCHAR(500) DEFAULT '',
 permissions VARCHAR(500) DEFAULT '',
 status INT DEFAULT 0 NOT NULL,
 comment VARCHAR(200) DEFAULT '' NOT NULL,
 create_at DATETIME NOT NULL,
 update_at DATETIME NOT NULL
);

ALTER TABLE sys_resource ADD CONSTRAINT PK_sys_resource PRIMARY KEY (id);


CREATE TABLE sys_user_role (
 id CHAR(36) NOT NULL,
 user_id CHAR(36),
 role_id CHAR(36),
 create_at DATETIME NOT NULL
);

ALTER TABLE sys_user_role ADD CONSTRAINT PK_sys_user_role PRIMARY KEY (id);


CREATE TABLE sys_role_resource (
 id CHAR(36) NOT NULL,
 role_id CHAR(36),
 resource_id CHAR(36),
 create_at DATETIME NOT NULL
);

ALTER TABLE sys_role_resource ADD CONSTRAINT PK_sys_role_resource PRIMARY KEY (id);


ALTER TABLE sys_user_role ADD CONSTRAINT FK_sys_user_role_0 FOREIGN KEY (user_id) REFERENCES sys_user (id);
ALTER TABLE sys_user_role ADD CONSTRAINT FK_sys_user_role_1 FOREIGN KEY (role_id) REFERENCES sys_role (id);


ALTER TABLE sys_role_resource ADD CONSTRAINT FK_sys_role_resource_0 FOREIGN KEY (role_id) REFERENCES sys_role (id);
ALTER TABLE sys_role_resource ADD CONSTRAINT FK_sys_role_resource_1 FOREIGN KEY (resource_id) REFERENCES sys_resource (id);