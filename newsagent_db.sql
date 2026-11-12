CREATE DATABASE IF NOT EXISTS newsagents_db;
USE newsagents_db;

-- Drop tables in dependency order
DROP TABLE IF EXISTS delivery_docket;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS publication;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS news_agent;
DROP TABLE IF EXISTS delivery_area;
DROP TABLE IF EXISTS delivery_person;


-- Delivery Person
CREATE TABLE delivery_person(
	deliveryPersonId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    address VARCHAR(150) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL
);

-- Delivery areas
CREATE TABLE delivery_area (
    deliveryAreaId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    region VARCHAR(100) NOT NULL,
    deliveryPersonId INT NOT NULL,
    
    FOREIGN KEY (deliveryPersonId)
        REFERENCES delivery_person(deliveryPersonId)
);



-- Customers
CREATE TABLE customer (
    customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    address VARCHAR(150) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL
    -- deliveryAreaId INT NOT NULL,

    -- FOREIGN KEY (deliveryAreaId)
--         REFERENCES delivery_area(deliveryAreaId)
);

-- News Agents
CREATE TABLE news_agent (
    newsAgentId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(150) NOT NULL,
    phoneNumber VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL
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

-- Products belonging to each order
CREATE TABLE order_item (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    orderDate VARCHAR(20) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price VARCHAR(20) NOT NULL,
    customerId INT NOT NULL,
    
    CHECK (quantity > 0),
    CHECK (price >= 0),
    FOREIGN KEY (customerId)
		REFERENCES customer(customerId)
);

-- Delivery Docket
CREATE TABLE delivery_docket (
    docketId INT AUTO_INCREMENT PRIMARY KEY,
    deliveryDate DATE NOT NULL,
    deliveryStatus VARCHAR(20) NOT NULL,
);
 -- Bill
CREATE TABLE bill (
    billId INT AUTO_INCREMENT PRIMARY KEY,
    paymentStatus VARCHAR(20) NOT NULL,
    paymentDate VARCHAR(10) NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL,
);


select * from order_item;

INSERT INTO customer
    (firstName, surname,address,phoneNumber)
VALUES
    ('John', 'Smith', 'Main Street', '0871234567');
    
INSERT INTO delivery_person
	(firstName, surname, address, phoneNumber)
VALUES
	('Jack', 'Casey', 'Mount Street', '0897645312');

    
select * from customer;
