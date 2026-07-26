USE velorentdb;

-- DROP TABLES
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS payments;
-- DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS car_images;
DROP TABLE IF EXISTS cars;
-- DROP TABLE IF EXISTS notifications;
-- DROP TABLE IF EXISTS system_logs;
DROP TABLE IF EXISTS users;


-- ~PHASE 1~
-- USERS (for admin, agent, customer)
CREATE TABLE IF NOT EXISTS velorentdb.users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) DEFAULT NULL,
  password VARCHAR(255),
  full_name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  address VARCHAR(255),
  id_number VARCHAR(25) NOT NULL,
  id_type ENUM('SSM', 'IC', 'PASSPORT') NOT NULL,
  role ENUM('ADMIN', 'AGENT', 'CUSTOMER') NOT NULL,
  user_status ENUM('ACTIVE', 'DORMANT', 'INACTIVE') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP  
);

-- CARS
CREATE TABLE IF NOT EXISTS velorentdb.cars (
  car_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  agent_id BIGINT NOT NULL,
  brand VARCHAR(50),
  model VARCHAR(50),
  body_type VARCHAR(50),
  color VARCHAR(50),
  mileage INT,
  seat INT,
  year YEAR,
  plate_number VARCHAR(20) UNIQUE,
  daily_rate DECIMAL(10,2),
  description VARCHAR(255),
  fuel_type ENUM('PETROL','DIESEL','ELECTRIC','HYBRID') DEFAULT NULL,
  transmission ENUM('AUTOMATIC','MANUAL') DEFAULT NULL,
  car_status ENUM('AVAILABLE','RENTED','MAINTENANCE') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (agent_id) REFERENCES users(user_id)
);

-- CAR IMAGES
CREATE TABLE IF NOT EXISTS velorentdb.car_images (
  image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  car_id BIGINT NOT NULL,
  image_url VARCHAR(255),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (car_id) REFERENCES cars(car_id) ON DELETE CASCADE
);

-- CUSTOMERS
CREATE TABLE IF NOT EXISTS velorentdb.customers (
  customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  address VARCHAR(255),
  id_number VARCHAR(25),
  id_type ENUM('SSM', 'IC', 'PASSPORT'),
  driver_license VARCHAR(50),
  description VARCHAR(255),
  customer_status ENUM('ACTIVE','WARNING','INACTIVE') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- RENTALS
CREATE TABLE IF NOT EXISTS velorentdb.rentals (
  rental_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  car_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  total_amount DECIMAL(10,2),
  start_date DATE,
  end_date DATE,
  pickup_location VARCHAR(255),
  dropoff_location VARCHAR(255),
  rental_status ENUM('PENDING','UPCOMING','ACTIVE','COMPLETED','REJECTED','CANCELLED') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (car_id) REFERENCES cars(car_id),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- PAYMENTS
CREATE TABLE IF NOT EXISTS velorentdb.payments (
  payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  rental_id BIGINT NOT NULL,
  paid_amount DECIMAL(10,2),
  payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  payment_method ENUM('CREDITCARD','BANKTRANSFER','CASH','EWALLET') NOT NULL,
  payment_status ENUM('PAID','PARTIAL','FAILED') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

CREATE TABLE velorentdb.refresh_tokens (
    token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(500) NOT NULL,
    expiry_date DATETIME NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ~PHASE 2~
-- REVIEWS
-- CREATE TABLE IF NOT EXISTS velorentdb.reviews (
--   review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   rental_id BIGINT,
--   rating INT CHECK (rating BETWEEN 1 AND 5),
--   comment TEXT,
--  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--   FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
-- );

-- NOTIFICATIONS
-- CREATE TABLE IF NOT EXISTS velorentdb.notifications (
--   notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   user_id BIGINT,
--   message VARCHAR(255),
--   is_read BOOLEAN DEFAULT FALSE,
--   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--   FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );

-- SYSTEM LOGS
-- CREATE TABLE IF NOT EXISTS velorentdb.system_logs (
--   log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   user_id BIGINT,
--   action VARCHAR(100),
--   description TEXT,
--   log_time DATETIME DEFAULT CURRENT_TIMESTAMP,
--   FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );
