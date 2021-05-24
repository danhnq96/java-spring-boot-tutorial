# Role
INSERT INTO project2_parking_management.app_role (id, role_name) VALUES (1, 'Admin');
INSERT INTO project2_parking_management.app_role (id, role_name) VALUES (2, 'Employee');
INSERT INTO project2_parking_management.app_role (id, role_name) VALUES (3, 'Customer');
# Car type
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (1, '4 chỗ');
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (2, '7 chỗ');
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (3, '9 chỗ');
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (4, 'Bán tải');
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (5, 'Tải 500kg');
INSERT INTO project2_parking_management.car_type (id, car_type_name) VALUES (6, 'Khác');
# Member card type
INSERT INTO project2_parking_management.member_card_type (id, member_type_name) VALUES (1, 'Tuần');
INSERT INTO project2_parking_management.member_card_type (id, member_type_name) VALUES (2, 'Tháng');
INSERT INTO project2_parking_management.member_card_type (id, member_type_name) VALUES (3, 'Năm');
# Slot type
INSERT INTO project2_parking_management.slot_type (id, height, slot_name, width) VALUES (1, 1900, 'S', 4950);
INSERT INTO project2_parking_management.slot_type (id, height, slot_name, width) VALUES (2, 1850, 'M', 5350);
INSERT INTO project2_parking_management.slot_type (id, height, slot_name, width) VALUES (3, 2000, 'L', 5500);