USE velorentdb;

SET FOREIGN_KEY_CHECKS = 0;

-- DELETE RECORDS
DELETE FROM payments;
DELETE FROM rentals;
DELETE FROM customers;
DELETE FROM car_images;
DELETE FROM cars;
DELETE FROM users;
-- DELETE FROM reviews;
-- DELETE FROM notifications;
-- DELETE FROM system_logs;

SET FOREIGN_KEY_CHECKS = 1;

-- ~PHASE 1~
-- USERS								
INSERT INTO `velorentdb`.`users` (`username`, `password`, `full_name`, `email`, `phone`, `address`, `id_number`, `id_type`, `role`, `user_status`)
VALUES
('Admin1', 'admin123', 'System Admin 1', 'admin1@velorent.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'ADMIN', 'ACTIVE'),
('Admin2', 'admin123', 'System Admin 2', 'admin2@velorent.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'ADMIN', 'DORMANT'),

('Renter1', 'renter123', 'Eddy Car Rental', 'suhardi@gmail.com', '+6013-3184673', 'Tasik Height, Bandar Tasik Selatan, KL', 'A1234567MY', 'SSM', 'RENTER', 'ACTIVE'),
('Renter2', 'renter123', 'Trevo', 'admin@trevo.com', '+6016-6002579', 'KL', 'A1234567MY', 'SSM', 'RENTER', 'ACTIVE'),
('Renter3', 'renter123', 'Socar', 'admin@socar.com', '+6016-6002579', 'KL', 'A1234567MY', 'SSM', 'RENTER', 'ACTIVE'),

('Customer1', 'customer123', 'Customer Name 1', 'cust1@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'ACTIVE'),
('Customer2', 'customer123', 'Customer Name 2', 'cust2@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'ACTIVE'),
('Customer3', 'customer123', 'Customer Name 3', 'cust3@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'ACTIVE'),
('Customer4', 'customer123', 'Customer Name 4', 'cust4@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'ACTIVE'),
('Customer5', 'customer123', 'Customer Name 5', 'cust5@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'ACTIVE'),
('Customer6', 'customer123', 'Customer Name 6', 'cust6@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'DORMANT'),
('Customer7', 'customer123', 'Customer Name 7', 'cust7@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'DORMANT'),
('Customer8', 'customer123', 'Customer Name 8', 'cust8@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'DORMANT'),
('Customer9', 'customer123', 'Customer Name 9', 'cust9@gmail.com', '+6016-6002579', 'Sungai Long, Kajang, Selangor', '930811035807', 'IC', 'CUSTOMER', 'DORMANT');


-- CARS
INSERT INTO `velorentdb`.`cars` (`renter_id`, `brand`, `model`, `body_type`, `color`, `mileage`, `seat`, `year`, `plate_number`, `daily_rate`, `description`, `fuel_type`, `transmission`, `car_status`)
VALUES
(3, 'Toyota', 'Vios', 'Sedan', 'Black', 33000, 4, '2020', 'DAK2122', 110.00, 'Description Description Description Description', 'PETROL', 'AUTOMATIC', 'AVAILABLE'),
(3, 'Mazda', 'CX-5', 'SUV', 'Black', 33000, 4, '2021', 'DBK2122', 120.00, 'Description Description Description Description', 'PETROL', 'MANUAL', 'AVAILABLE'),
(3, 'Perodua', 'Myvi', 'Hatchback', 'Black', 33000, 4, '2022', 'DCK2122', 130.00, 'Description Description Description Description', 'PETROL', 'AUTOMATIC', 'RENTED'),
(3, 'Toyota', 'Hilux', 'Pickup', 'Black', 33000, 6, '2023', 'DDK2122', 140.00, 'Description Description Description Description', 'DIESEL', 'MANUAL', 'RENTED'),
(3, 'Tesla', 'Model 3', 'Sedan', 'Black', 33000, 4, '2024', 'DEK2122', 150.00, 'Description Description Description Description', 'ELECTRIC', 'AUTOMATIC', 'MAINTENANCE'),
(3, 'Toyota', 'Camry', 'Sedan', 'Black', 33000, 4, '2025', 'DFK2122', 160.00, 'Description Description Description Description', 'HYBRID', 'MANUAL', 'MAINTENANCE'),
																																																  
(4, 'Perodua', 'Bezza', 'Sedan', 'Red', 25000, 4, '2015', 'VAX2122', 170.00, 'Description Description Description Description', 'PETROL', 'MANUAL', 'AVAILABLE'),
(4, 'Volkswagen', 'Golf', 'Hatchback', 'Red', 25000, 4, '2016', 'VBX2122', 180.00, 'Description Description Description Description', 'PETROL', 'AUTOMATIC', 'AVAILABLE'),
(4, 'Isuzu', 'D-Max', 'Pickup', 'Red', 25000, 6, '2017', 'VCX2122', 190.00, 'Description Description Description Description', 'DIESEL', 'MANUAL', 'RENTED'),
(4, 'Tesla', 'Model Y', 'Sedan', 'Red', 25000, 4, '2018', 'VDX2122', 200.00, 'Description Description Description Description', 'ELECTRIC', 'AUTOMATIC', 'RENTED'),
(4, 'BMW', 'X3', 'SUV', 'Red', 25000, 4, '2019', 'VEX2122', 210.00, 'Description Description Description Description', 'HYBRID', 'MANUAL', 'MAINTENANCE'),
																																																  
(5, 'Honda', 'City', 'Sedan', 'Silver', 100000, 4, '2020', 'BAA2122', 220.00, 'Description Description Description Description', 'PETROL', 'AUTOMATIC', 'AVAILABLE'),
(5, 'Proton', 'Saga', 'Sedan', 'Silver', 100000, 4, '2021', 'BBA2122', 230.00, 'Description Description Description Description', 'PETROL', 'AUTOMATIC', 'RENTED');


-- CAR IMAGES
INSERT INTO `velorentdb`.`car_images` (`car_id`, `image_url`)
VALUES
(1, 'https://images.unsplash.com/photo-1749058983469-11eaef8d7bc5?w=400'),
(1, 'https://images.unsplash.com/photo-1749058983952-c7ce95258535?w=400'),
(2, 'https://images.unsplash.com/photo-1743114713466-f12a85992a75?w=400'),
(2, 'https://images.unsplash.com/photo-1743114713492-ab7d94a7f082?w=400'),
(3, ''),
(4, 'https://images.unsplash.com/photo-1641431616381-3f0613d82ca7?w-400'),
(5, 'https://images.unsplash.com/photo-1560958089-b8a1929cea89?w=400'),
(6, 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=400'),

(7, 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=400'),
(8, 'https://images.unsplash.com/photo-1572811298797-9eecadf6cb24?w=400'),
(8, 'https://images.unsplash.com/photo-1655286684114-48fec0444451?w=400'),
(9, 'https://images.unsplash.com/photo-1515661601451-281ea60d99f0?w=400'),
(10, 'https://images.unsplash.com/photo-1560958089-b8a1929cea89?w=400'),
(11, 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=400'),

(12, 'https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=400'),
(13, 'https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=400');


-- CUSTOMERS
INSERT INTO `velorentdb`.`customers`
(`full_name`, `email`, `phone`, `address`, `id_number`, `id_type`, `driver_license`, `description`, `customer_status`)
VALUES
('Ahmad Rizal bin Abdullah', 'ahmad.rizal@email.com', '+60 12-345 6789', 'No. 15, Jalan Merdeka, 50100 Kuala Lumpur', '930111111111', 'IC', 'D1234567', 'Pelanggan utama, sentiasa membayar tepat pada masanya', 'ACTIVE'),
('Siti Nurhaliza binti Hassan', 'siti.nurhaliza@email.com', '+60 13-987 6543', 'No. 28, Jalan Bukit Bintang, 55100 Kuala Lumpur', '930111111112', 'IC', 'D9876543', 'Pembayaran lewat pada sewa terakhir', 'WARNING'),
('Mohd Faizal bin Omar', 'mohd.faizal@email.com', '+60 17-456 7890', 'No. 42, Jalan Sultan, 80000 Johor Bahru', '930111111113', 'IC', 'D4567891', 'Pelanggan VIP, kerap menyewa kenderaan mewah', 'ACTIVE'),
('Nurul Aisyah binti Ibrahim', 'nurul.aisyah@email.com', '+60 19-321 9876', 'No. 7, Jalan Alor, 10450 Georgetown, Pulau Pinang', '930111111114', 'IC', 'D3219876', 'Pelanggan baru, sewa pertama berjalan lancar', 'ACTIVE'),
('Azman bin Hashim', 'azman.hashim@email.com', '+60 16-654 3210', 'No. 88, Jalan Tun Razak, 50400 Kuala Lumpur', '930111111115', 'IC', 'D6543210', 'Tidak menyewa lebih 6 bulan', 'INACTIVE'),
('Mohd Rizal bin Abdullah', 'mohd.rizal@email.com', '+60 12-345 6789', 'No. 15, Jalan Merdeka, 50100 Kuala Lumpur', '930111111116', 'IC', 'D1234567', 'Pelanggan utama, sentiasa membayar tepat pada masanya', 'ACTIVE'),
('Che Nurhaliza binti Hassan', 'che.nurhaliza@email.com', '+60 13-987 6543', 'No. 28, Jalan Bukit Bintang, 55100 Kuala Lumpur', '930111111117', 'IC', 'D1234567', 'Pembayaran lewat pada sewa terakhir', 'WARNING');


-- RENTALS
INSERT INTO `velorentdb`.`rentals`
(`car_id`, `customer_id`, `total_amount`, `start_date`, `end_date`, `pickup_location`, `dropoff_location`, `rental_status`)
VALUES
(3, 1, 500.00, '2025-09-20', '2025-10-01', 'Pejabat Pusat Bandar', 'Pejabat Pusat Bandar', 'COMPLETED'),
(4, 2, 300.00, '2025-09-25', '2025-09-28', 'Terminal Lapangan Terbang', 'Terminal Lapangan Terbang', 'ACTIVE'),
(9, 3, 1000.00, '2025-10-01', '2025-10-14', 'KL', 'KL', 'PENDING'),
(10, 4, 1000.00, '2025-10-01', '2025-10-14', 'KL', 'KL', 'UPCOMING'),
(13, 6, 1000.00, '2025-10-01', '2025-10-14', 'KL', 'KL', 'CANCELLED'),
(13, 6, 700.00, '2025-10-01', '2025-10-14', 'KL', 'KL', 'REJECTED');


-- PAYMENTS
INSERT INTO `velorentdb`.`payments`
(`rental_id`, `paid_amount`, `payment_method`, `payment_status`)
VALUES
('1', 500.00, 'CREDITCARD', 'PAID'),
('2', 300.00, 'BANKTRANSFER', 'PAID'),
('3', 1000.00, 'CASH', 'PAID'),
('4', 500.00, 'EWALLET', 'FAILED'),
('4', 500.00, 'EWALLET', 'PARTIAL');

-- ~PHASE 2~
-- REVIEWS
-- INSERT INTO reviews (rental_id, rating, comment)
-- VALUES
-- (1, 5, 'Car was clean and smooth to drive!');
-- INSERT INTO reviews (review_id, rental_id, rating, comment, created_at) VALUES
-- (1,  1, 5, 'Kereta bersih dan sangat jimat minyak.',                    '2025-10-05 10:00:00'),
-- (2,  2, 4, 'Proses sewa lancar dan kereta selesa dipandu.',             '2025-10-06 12:10:00'),
-- (3,  4, 5, 'Sangat berpuas hati dengan layanan dan keadaan kereta.',    '2025-10-08 15:20:00'),
-- (4,  9, 4, 'Kereta baik, cuma pickup lambat sedikit.',                  '2025-10-13 09:40:00'),
-- (5,  14,5, 'Mercedes sangat selesa untuk perjalanan kerja.',            '2025-10-18 18:15:00'),
-- (6,  18,4, 'Pengalaman sewa yang memuaskan.',                           '2025-10-22 11:05:00'),
-- (7,  1,  5, 'Akan repeat lagi selepas ini.',                            '2025-10-05 10:15:00'),
-- (8,  2,  4, 'Staf mesra dan cepat membantu.',                           '2025-10-06 12:30:00'),
-- (9,  4,  5, 'Harga berpatutan dan mudah berurusan.',                    '2025-10-08 15:40:00'),
-- (10, 9,  4, 'Kereta bersih dan pickup point mudah dicari.',             '2025-10-13 10:10:00'),
-- (11, 14, 5, 'Pengalaman premium, sangat recommended.',                  '2025-10-18 18:25:00'),
-- (12, 18, 4, 'Semua baik, cuma mahu lebih banyak pilihan masa hadapan.', '2025-10-22 11:25:00');

-- NOTIFICATIONS
-- INSERT INTO notifications (user_id, message)
-- VALUES
-- (2, 'Your car Myvi has been rented by John Tan.'),
-- (3, 'Your rental for Honda Civic is pending approval.');
-- INSERT INTO notifications (notification_id, user_id, message, is_read, created_at) VALUES
-- (1,  6,  'Tempahan anda untuk Perodua Myvi telah selesai.',                         1, '2025-10-05 10:30:00'),
-- (2,  7,  'Bayaran tempahan Toyota Vios berjaya diterima.',                          1, '2025-10-06 09:45:00'),
-- (3,  8,  'Sila lengkapkan bayaran baki untuk Honda City.',                          0, '2025-10-03 12:30:00'),
-- (4,  9,  'Tempahan Proton Saga anda telah selesai.',                                1, '2025-10-08 16:10:00'),
-- (5,  10, 'Tempahan anda untuk Perodua Bezza telah diluluskan.',                     0, '2025-10-05 09:20:00'),
-- (6,  11, 'Tempahan Toyota Camry sedang menunggu pengesahan.',                       0, '2025-10-06 11:15:00'),
-- (7,  12, 'Tempahan Honda Civic telah diluluskan.',                                  0, '2025-10-07 08:50:00'),
-- (8,  13, 'Tempahan Mazda CX-5 telah diluluskan.',                                   0, '2025-10-08 10:00:00'),
-- (9,  14, 'Tempahan Proton X50 telah selesai.',                                      1, '2025-10-12 09:00:00'),
-- (10, 15, 'Tempahan Nissan Almera telah ditolak.',                                   1, '2025-10-10 14:00:00'),
-- (11, 16, 'Bayaran untuk Toyota Hilux berjaya diterima.',                            1, '2025-10-10 17:20:00'),
-- (12, 17, 'Tempahan Isuzu D-Max sedang menunggu bayaran penuh.',                     0, '2025-10-11 11:00:00'),
-- (13, 18, 'Bayaran Tesla Model 3 berjaya diterima.',                                 1, '2025-10-12 11:40:00'),
-- (14, 19, 'Tempahan Mercedes C200 telah selesai.',                                   1, '2025-10-18 18:30:00'),
-- (15, 20, 'Tempahan Tesla Model 3 telah dibatalkan.',                                1, '2025-10-15 09:10:00'),
-- (16, 21, 'Bayaran Hyundai Elantra berjaya diterima.',                               1, '2025-10-15 18:35:00'),
-- (17, 22, 'Tempahan Volkswagen Golf telah diluluskan.',                              0, '2025-10-16 09:20:00'),
-- (18, 23, 'Tempahan Honda HR-V telah selesai.',                                      1, '2025-10-22 11:35:00'),
-- (19, 24, 'Bayaran awal untuk Perodua Axia telah direkodkan.',                       0, '2025-10-18 10:50:00'),
-- (20, 25, 'Bayaran Toyota Fortuner berjaya diterima.',                               1, '2025-10-19 12:00:00');

-- SYSTEM LOGS
-- INSERT INTO system_logs (user_id, action, description)
-- VALUES
-- (2, 'Add Car', 'Added Honda Civic to fleet'),
-- (3, 'Book Car', 'Booked Perodua Myvi for 3 days');
-- INSERT INTO system_logs (log_id, user_id, action, description, log_time) VALUES
-- (1,  1,  'CREATE_USER',     'Admin menambah akaun renter baharu.',                     '2025-09-25 09:00:00'),
-- (2,  3,  'ADD_CAR',         'Renter menambah Perodua Myvi ke dalam sistem.',           '2025-09-25 09:20:00'),
-- (3,  4,  'ADD_CAR',         'Renter menambah Proton X50 ke dalam sistem.',             '2025-09-25 09:30:00'),
-- (4,  5,  'ADD_CAR',         'Renter menambah BMW X3 ke dalam sistem.',                 '2025-09-25 09:40:00'),
-- (5,  6,  'BOOK_CAR',        'Customer membuat tempahan Perodua Myvi.',                 '2025-09-30 08:10:00'),
-- (6,  7,  'BOOK_CAR',        'Customer membuat tempahan Toyota Vios.',                  '2025-10-01 09:15:00'),
-- (7,  8,  'BOOK_CAR',        'Customer membuat tempahan Honda City.',                   '2025-10-02 10:20:00'),
-- (8,  9,  'BOOK_CAR',        'Customer membuat tempahan Proton Saga.',                  '2025-10-03 11:30:00'),
-- (9,  10, 'BOOK_CAR',        'Customer membuat tempahan Perodua Bezza.',                '2025-10-04 12:40:00'),
-- (10, 11, 'BOOK_CAR',        'Customer membuat tempahan Toyota Camry.',                 '2025-10-05 13:50:00'),
-- (11, 12, 'BOOK_CAR',        'Customer membuat tempahan Honda Civic.',                  '2025-10-06 14:00:00'),
-- (12, 13, 'BOOK_CAR',        'Customer membuat tempahan Mazda CX-5.',                   '2025-10-07 15:10:00'),
-- (13, 14, 'BOOK_CAR',        'Customer membuat tempahan Proton X50.',                   '2025-10-08 16:20:00'),
-- (14, 15, 'BOOK_CAR',        'Customer membuat tempahan Nissan Almera.',                '2025-10-09 17:30:00'),
-- (15, 16, 'BOOK_CAR',        'Customer membuat tempahan Toyota Hilux.',                 '2025-10-10 18:40:00'),
-- (16, 17, 'BOOK_CAR',        'Customer membuat tempahan Isuzu D-Max.',                  '2025-10-11 08:50:00'),
-- (17, 18, 'BOOK_CAR',        'Customer membuat tempahan Tesla Model 3.',                '2025-10-12 09:00:00'),
-- (18, 19, 'BOOK_CAR',        'Customer membuat tempahan Mercedes C200.',                '2025-10-13 10:15:00'),
-- (19, 20, 'CANCEL_BOOKING',  'Customer membatalkan tempahan Tesla Model 3.',            '2025-10-14 11:25:00'),
-- (20, 1,  'UPDATE_STATUS',   'Admin mengemaskini status beberapa tempahan dan bayaran.', '2025-10-19 14:00:00');

-- SELECT RECORDS
SELECT * FROM payments;
SELECT * FROM rentals;
SELECT * FROM customers;
SELECT * FROM car_images;
SELECT * FROM cars;
SELECT * FROM users;
-- SELECT * FROM reviews;
-- SELECT * FROM notifications;
-- SELECT * FROM system_logs;
