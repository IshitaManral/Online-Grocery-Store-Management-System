create database project;
use project;
CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL DEFAULT 'Guest',
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE Categories (
    id INT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE SubCategories (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Products (
    id INT PRIMARY KEY,
    title VARCHAR(500) NOT NULL,
    sub_category_id INT,
    price DECIMAL(10,2),
    currency VARCHAR(10),
    discount VARCHAR(100),
    rating FLOAT,
    feature TEXT,
    product_description TEXT,
    stock_quantity INT NOT NULL
);

CREATE TABLE Cart (
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    added_at DATETIME NOT NULL
);

CREATE TABLE Orders (
    id INT PRIMARY KEY,
    user_id INT,
    order_date DATETIME NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status ENUM('Pending', 'Completed', 'Cancelled')
);

CREATE TABLE OrderDetails (
    id INT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE Transactions (
    id INT PRIMARY KEY,
    order_id INT,
    payment_method ENUM('Cash', 'Credit Card', 'Debit Card', 'UPI', 'Net Banking') NOT NULL,
    transaction_date DATETIME NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    status ENUM('Success', 'Failed', 'Pending') NOT NULL
);

-- Sample Insertions

-- Users
INSERT INTO Users (id, username, email, password, phone, address) VALUES
(1, 'Advika Wankhede', 'advika@example.com', 'hashed_pass1', '9876543210', 'Mumbai, India'),
(2, 'Rahul Sharma', 'rahul@example.com', 'hashed_pass2', '9876543211', 'Delhi, India'),
(3, 'Priya Mehta', 'priya@example.com', 'hashed_pass3', '9876543212', 'Pune, India'),
(4, 'Amit Verma', 'amit@example.com', 'hashed_pass4', '9876543213', 'Bangalore, India'),
(5, 'Neha Kapoor', 'neha@example.com', 'hashed_pass5', '9876543214', 'Hyderabad, India'),
(6, 'Karan Malhotra', 'karan@example.com', 'hashed_pass6', '9876543215', 'Chennai, India'),
(7, 'Simran Kaur', 'simran@example.com', 'hashed_pass7', '9876543216', 'Kolkata, India'),
(8, 'Rohan Das', 'rohan@example.com', 'hashed_pass8', '9876543217', 'Lucknow, India'),
(9, 'Ananya Singh', 'ananya@example.com', 'hashed_pass9', '9876543218', 'Jaipur, India'),
(10, 'Vikram Batra', 'vikram@example.com', 'hashed_pass10', '9876543219', 'Ahmedabad, India');

-- Categories
INSERT INTO Categories (id, name) VALUES
(1, 'Fruits & Vegetables'),
(2, 'Dairy Products'),
(3, 'Bakery Items'),
(4, 'Beverages'),
(5, 'Snacks'),
(6, 'Frozen Foods'),
(7, 'Meat & Seafood'),
(8, 'Pantry Staples'),
(9, 'Household Items'),
(10, 'Personal Care');

-- Products
INSERT INTO Products (id, title, sub_category_id, price, currency, discount, rating, feature, product_description, stock_quantity) VALUES
(1, 'Apple', 1, 50.00, 'INR', '10%', 4.5, 'Organic', 'Fresh organic apples', 100),
(2, 'Spinach', 2, 30.00, 'INR', '5%', 4.0, 'Rich in Iron', 'Fresh farm spinach', 200),
(3, 'Cheddar Cheese', 3, 250.00, 'INR', '15%', 4.7, 'Aged Cheese', 'Premium aged cheddar', 50),
(4, 'Banana', 1, 40.00, 'INR', '8%', 4.3, 'Rich in Potassium', 'Fresh bananas', 120),
(5, 'Milk', 3, 60.00, 'INR', '5%', 4.6, 'Full Cream', 'Fresh dairy milk', 300),
(6, 'Brown Bread', 3, 35.00, 'INR', '5%', 4.2, 'Whole Wheat', 'Healthy whole wheat bread', 80),
(7, 'Eggs', 3, 90.00, 'INR', '12%', 4.8, 'Organic', 'Fresh organic eggs', 150),
(8, 'Orange Juice', 4, 110.00, 'INR', '10%', 4.5, 'No Added Sugar', '100% pure orange juice', 70),
(9, 'Potato Chips', 5, 40.00, 'INR', '7%', 4.0, 'Low Fat', 'Crispy potato chips', 90),
(10, 'Chicken Breast', 7, 300.00, 'INR', '10%', 4.7, 'Boneless', 'Fresh chicken breast', 50);

-- Orders
INSERT INTO Orders (id, user_id, order_date, total_amount, status) VALUES
(1, 1, '2025-03-21 09:00:00', 90.00, 'Pending'),
(2, 2, '2025-03-21 10:30:00', 250.00, 'Completed'),
(3, 3, '2025-03-21 11:15:00', 60.00, 'Pending'),
(4, 4, '2025-03-21 12:00:00', 300.00, 'Completed'),
(5, 5, '2025-03-21 13:45:00', 110.00, 'Pending'),
(6, 6, '2025-03-21 14:30:00', 35.00, 'Completed'),
(7, 7, '2025-03-21 15:20:00', 90.00, 'Pending'),
(8, 8, '2025-03-21 16:05:00', 40.00, 'Completed'),
(9, 9, '2025-03-21 17:10:00', 300.00, 'Pending'),
(10, 10, '2025-03-21 18:00:00', 250.00, 'Completed')

ALTER USER 'root'@'localhost' IDENTIFIED BY 'Pass*Word231';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'root'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

describe users;

select * from users;
ALTER TABLE users MODIFY COLUMN username VARCHAR(255) NOT NULL DEFAULT 'Guest';
ALTER TABLE users MODIFY COLUMN phone VARCHAR(20) UNIQUE NOT NULL DEFAULT '0000000000';
ALTER TABLE users MODIFY COLUMN address VARCHAR(255) NOT NULL DEFAULT 'Unknown';

ALTER TABLE Orders ADD COLUMN total_amount DECIMAL(10,2) NOT NULL;
ALTER TABLE Categories DROP PRIMARY KEY;
ALTER TABLE Categories MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE SubCategories DROP PRIMARY KEY;
ALTER TABLE SubCategories MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE Products DROP PRIMARY KEY;
ALTER TABLE Products MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE Cart DROP PRIMARY KEY;
ALTER TABLE Cart MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE Orders DROP PRIMARY KEY;
ALTER TABLE Orders MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE OrderDetails DROP PRIMARY KEY;
ALTER TABLE OrderDetails MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE Transactions DROP PRIMARY KEY;
ALTER TABLE Transactions MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE SubCategories ADD FOREIGN KEY (category_id) REFERENCES Categories(id);
ALTER TABLE Products ADD FOREIGN KEY (sub_category_id) REFERENCES SubCategories(id);
ALTER TABLE Cart ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Cart ADD FOREIGN KEY (product_id) REFERENCES Products(id);
ALTER TABLE Orders ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE OrderDetails ADD FOREIGN KEY (order_id) REFERENCES Orders(id);
ALTER TABLE OrderDetails ADD FOREIGN KEY (product_id) REFERENCES Products(id);
ALTER TABLE Transactions ADD FOREIGN KEY (order_id) REFERENCES Orders(id);

ALTER TABLE Products 
ADD CONSTRAINT fk_sub_category 
FOREIGN KEY (sub_category_id) 
REFERENCES SubCategories(id) 
ON DELETE CASCADE;
