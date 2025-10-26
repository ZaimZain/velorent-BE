USE velorentdb;

-- DROP TABLES
DROP TABLE IF EXISTS car_images;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS renters;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS system_logs;
DROP TABLE IF EXISTS users;

-- USERS (for admin, renter, customer)
CREATE TABLE IF NOT EXISTS velorentdb.users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) DEFAULT NULL,
  password VARCHAR(255),
  role ENUM('ADMIN', 'RENTER', 'CUSTOMER') NOT NULL,
  full_name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  address VARCHAR(255),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- RENTERS (car owners)
CREATE TABLE IF NOT EXISTS velorentdb.renters (
  renter_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  company_name VARCHAR(100),
  ic_number VARCHAR(20),
  FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- CUSTOMERS
CREATE TABLE IF NOT EXISTS velorentdb.customers (
  customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  identification_number VARCHAR(50),
  identification_type ENUM('IC', 'PASSPORT') DEFAULT NULL,
  license_number VARCHAR(50),
  FOREIGN KEY (customer_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- CARS
CREATE TABLE IF NOT EXISTS velorentdb.cars (
  car_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  renter_id BIGINT,
  brand VARCHAR(50),
  model VARCHAR(50),
  body_type VARCHAR(50),
  fuel_type ENUM('PETROL','DIESEL','ELECTRIC','HYBRID') DEFAULT NULL,
  transmission ENUM('AUTOMATIC','MANUAL') DEFAULT NULL,
  seat INT,
  year YEAR,
  plate_number VARCHAR(20) UNIQUE,
  price_per_day DECIMAL(10,2),
  status ENUM('AVAILABLE','RENTED','MAINTENANCE') NOT NULL,
  description TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (renter_id) REFERENCES renters(renter_id)
);

-- CAR IMAGES
CREATE TABLE IF NOT EXISTS velorentdb.car_images (
  image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  car_id BIGINT,
  image_url VARCHAR(255),
  FOREIGN KEY (car_id) REFERENCES cars(car_id) ON DELETE CASCADE
);

-- RENTALS
CREATE TABLE IF NOT EXISTS velorentdb.rentals (
  rental_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT,
  car_id BIGINT,
  start_date DATE,
  end_date DATE,
  total_price DECIMAL(10,2),
  status ENUM('PENDING','APPROVED','REJECTED','COMPLETED','CANCELLED') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
  FOREIGN KEY (car_id) REFERENCES cars(car_id)
);

-- PAYMENTS
CREATE TABLE IF NOT EXISTS velorentdb.payments (
  payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  rental_id BIGINT,
  amount DECIMAL(10,2),
  payment_method ENUM('CREDIT_CARD','BANK_TRANSFER','CASH','EWALLET') NOT NULL,
  status ENUM('PAID','PENDING','FAILED') NOT NULL,
  payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

-- REVIEWS
CREATE TABLE IF NOT EXISTS velorentdb.reviews (
  review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  rental_id BIGINT,
  rating INT CHECK (rating BETWEEN 1 AND 5),
  comment TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

-- NOTIFICATIONS
CREATE TABLE IF NOT EXISTS velorentdb.notifications (
  notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  message VARCHAR(255),
  is_read BOOLEAN DEFAULT FALSE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- SYSTEM LOGS
CREATE TABLE IF NOT EXISTS velorentdb.system_logs (
  log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  action VARCHAR(100),
  description TEXT,
  log_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);
