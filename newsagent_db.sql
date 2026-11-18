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
    publicationName VARCHAR(100) NOT NULL UNIQUE,
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
		REFERENCES customer(customerId),
	FOREIGN KEY (productName)
		REFERENCES publication(publicationName)
);

-- Delivery Docket
CREATE TABLE delivery_docket (
    docketId INT AUTO_INCREMENT PRIMARY KEY,
    deliveryDate DATE NOT NULL,
    deliveryStatus VARCHAR(20) NOT NULL
);
 -- Bill
CREATE TABLE bill (
    billId INT AUTO_INCREMENT PRIMARY KEY,
    paymentStatus VARCHAR(20) NOT NULL,
    paymentDate VARCHAR(10) NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL
);


select * from order_item;

INSERT INTO customer
    (firstName, surname,address,phoneNumber)
VALUES
    ('John', 'Smith', 'Main Street', '0871234567'),
    ('Jane', 'Doe', 'Mount Street', '0854327864'),
    ('Jill', 'Scott', 'Patrick Street', '0891231232'),
    ('Paul', 'Connors', 'Highfield Park', '0891212425'),
    ('Pat', 'O\'Rourke', 'Willow Fields', '0839032145'),
    ('Mary', 'Kavanagh', 'Golden Island', '0869472761'),
    ('Bill', 'Billison', 'Bill Street', '0856782413');
    
INSERT INTO delivery_person
	(firstName, surname, address, phoneNumber)
VALUES
	('Jack', 'Casey', 'Mount Street', '0897645312');

    
select * from customer;

INSERT INTO publication
    (publicationName, price, frequency, type, stockQuantity)
VALUES
    ('Irish Daily Star', 2.00, 7, 'Tabloid', 300),
    ('Irish Daily Mirror', 2.50, 7, 'Tabloid', 200),
    ('Irish Daily Mail', 3.00, 7, 'Tabloid', 250),
    ('The Irish Sun', 3.25, 7, 'Tabloid', 275),
    ('Irish Examiner', 2.20, 6, 'Broadsheet', 325),
    ('Irish Independent', 4.00, 7, 'Compact', 220),
    ('The Herald', 2.95, 6, 'Tabloid', 150),
    ('The Irish Times', 3.75, 6, 'Broadsheet', 400);
