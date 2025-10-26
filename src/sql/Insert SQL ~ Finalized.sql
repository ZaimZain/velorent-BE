USE velorentdb;

-- USERS
INSERT INTO users (username, password, role, full_name, email, phone, address)
VALUES
('Admin', 'admin123', 'admin', 'System Admin', 'admin@velorent.com', '0123456789', 'Kuala Lumpur'),
('Renter', 'renter123', 'renter', 'Ahmad Car Rental', 'ahmad@rent.com', '0111111111', 'Selangor'),
('Customer', 'customer123', 'customer', 'John Tan', 'john@user.com', '0199999999', 'Penang');

-- RENTERS
INSERT INTO renters (renter_id, company_name, ic_number)
VALUES 
(2, 'Ahmad Car Rental', '900101-14-5677');

-- CUSTOMERS
INSERT INTO customers (identification_number, identification_type, license_number)
VALUES 
('A12345678', 'passport', 'A1234567MY'),
('930811035807', 'ic', 'A7654321MY');

-- CARS
INSERT INTO cars (renter_id, brand, model, body_type, fuel_type, transmission, seat, year, plate_number, price_per_day, status, description)
VALUES
(2, 'Perodua', 'Myvi', 'hatchback', 'petrol', 'manual', 4, 2022, 'ABC1234', 120.00, 'rented', 'Compact car, fuel efficient'),
(2, 'Toyota', 'Vios', 'sedan', 'diesel', 'automatic', 4, 2021, 'XYZ5678', 180.00, 'maintenance', 'Comfortable sedan'),
(2, 'Honda', 'Civic', 'sedan', 'electric', 'automatic', 4, 2021, 'XYZ1234', 180.00, 'available', 'Comfortable sedan');

-- CAR IMAGES
INSERT INTO car_images (car_id, image_url)
VALUES
(1, 'https://velorent.com/images/myvi1.jpg'),
(2, 'https://velorent.com/images/civic1.jpg');

-- RENTALS
INSERT INTO rentals (customer_id, car_id, start_date, end_date, total_price, status)
VALUES
(2, 1, '2025-10-01', '2025-10-03', 360.00, 'completed'),
(1, 3, '2025-10-10', '2025-10-12', 360.00, 'pending');

-- PAYMENTS
INSERT INTO payments (rental_id, amount, payment_method, status)
VALUES
(1, 360.00, 'bank_transfer', 'paid'),
(2, 360.00, 'ewallet', 'pending');

-- REVIEWS
INSERT INTO reviews (rental_id, rating, comment)
VALUES
(1, 5, 'Car was clean and smooth to drive!');

-- NOTIFICATIONS
INSERT INTO notifications (user_id, message)
VALUES
(2, 'Your car Myvi has been rented by John Tan.'),
(3, 'Your rental for Honda Civic is pending approval.');

-- SYSTEM LOGS
INSERT INTO system_logs (user_id, action, description)
VALUES
(2, 'Add Car', 'Added Honda Civic to fleet'),
(3, 'Book Car', 'Booked Perodua Myvi for 3 days');
