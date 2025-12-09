CREATE DATABASE IF NOT EXISTS drivedeal CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE drivedeal;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(200) UNIQUE NOT NULL,
  password_hash VARCHAR(200) NOT NULL,
  role VARCHAR(20) NOT NULL DEFAULT 'ADMIN'
);

CREATE TABLE IF NOT EXISTS cars (
  id INT AUTO_INCREMENT PRIMARY KEY,
  make VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  year INT NOT NULL,
  price DECIMAL(12,2) NOT NULL,
  mileage INT NOT NULL,
  fuel_type VARCHAR(50) NOT NULL,
  description TEXT,
  image_url VARCHAR(500),
  created_at DATETIME NOT NULL
);

INSERT INTO cars(make, model, year, price, mileage, fuel_type, description, image_url, created_at) VALUES
('Maruti','Swift',2021,650000,22000,'Petrol','Well maintained, single owner','', NOW()),
('Hyundai','Creta',2020,1200000,35000,'Diesel','Top model with sunroof','', NOW()),
('Tata','Nexon EV',2022,1550000,12000,'Electric','Excellent condition, fast-charge','', NOW());