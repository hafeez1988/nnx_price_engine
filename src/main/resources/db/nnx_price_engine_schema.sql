CREATE DATABASE IF NOT EXISTS nnx_price_engine_schema;
USE nnx_price_engine_schema;

CREATE TABLE IF NOT EXISTS product (
	product_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE,
	category VARCHAR(100) NOT NULL,
	created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted BOOLEAN DEFAULT 0
);

CREATE TABLE IF NOT EXISTS product_config (
	config_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	product_id INT(6) NOT NULL,
	price_per_unit DOUBLE NOT NULL,
	price_per_carton DOUBLE NOT NULL,
	units_per_carton INT(6) NOT NULL,
	created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);