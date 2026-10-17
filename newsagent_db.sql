CREATE DATABASE IF NOT EXISTS newsagents_db;
USE newsagents_db;

-- Drop tables in dependency order
DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS delivery_area;

-- Delivery areas
CREATE TABLE delivery_area (
    deliveryAreaId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    region VARCHAR(100) NOT NULL,
    driverName VARCHAR(100) NOT NULL
);

-- Customers
CREATE TABLE customer (
    customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    address VARCHAR(150) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    deliveryAreaId INT NOT NULL,

    FOREIGN KEY (deliveryAreaId)
        REFERENCES delivery_area(deliveryAreaId)
);

-- Products belonging to each order
CREATE TABLE order_item (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    orderDate VARCHAR(20) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price VARCHAR(20),
    customerId INT NOT NULL,
    
    CHECK (quantity > 0),
    CHECK (price >= 0),
    FOREIGN KEY (customerId)
		REFERENCES customer(customerId)
);


-- Publications
CREATE TABLE publication (
    publicationId INT AUTO_INCREMENT PRIMARY KEY,
    publicationName VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    frequency VARCHAR(30) NOT NULL,
    type VARCHAR(30) NOT NULL,
    stockQuantity INT NOT NULL DEFAULT 0,

    CHECK (price >= 0),
    CHECK (stockQuantity >= 0)
);

