use project3_thesis_management;

INSERT INTO project3_thesis_management.course_requirement (course_name) VALUES ('CGDN2020');
INSERT INTO project3_thesis_management.course_requirement (course_name) VALUES ('CGDN2021');

INSERT INTO project3_thesis_management.class_requirement (class_name, course_requirement_id) VALUES ('C06G1', 1);
INSERT INTO project3_thesis_management.class_requirement (class_name, course_requirement_id) VALUES ('C07G1', 2);
INSERT INTO project3_thesis_management.class_requirement (class_name, course_requirement_id) VALUES ('C09G1', 2);


INSERT INTO project3_thesis_management.app_role (id, role_name) VALUES (1, 'Admin');
INSERT INTO project3_thesis_management.app_role (id, role_name) VALUES (2, 'Teacher');
INSERT INTO project3_thesis_management.app_role (id, role_name) VALUES (3, 'Student');

#Mat khau: 123
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'trungdoan@gmail.com', 'fiptbxUB', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'trungdang@gmail.com', 'EtmhzYKj', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'tiennguyen@Gmail.com', 'lTXflNqy', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'tamtran@gmail.com', 'fItadAJo', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'linhdoan@gmail.com', 'VgBdjVbc', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'haitruong@gmail.com', 'VgBdjVbc', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'chanhtran@gmail.com', 'VgBdjVbc', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'nguyenle@gmail.com', 'VgBdjVbc', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'nhungnguyen@gmail.com', 'VgBdjVbc', 2);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'supea52795@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'maiho@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'lanhnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'dinluong@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'danhnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'khanhnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'nhatnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'ngantran@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'vinhmai@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'quanhoang@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'quocnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'hoangtran@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'datnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'khanhphan@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'hoatle@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'anguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'bnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'cnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'dnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'hnguyen@gmail.com', 'VgBdjVbc', 3);
INSERT INTO project3_thesis_management.app_account (enabled, password, username, verification_code, app_role_id) VALUES (true, '$2a$10$WpO5LrvU6SAa08.JpinXw.g3hct9ANZk94APt59/zBAgwUq0P9vAa', 'knguyen@gmail.com', 'VgBdjVbc', 3);

INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('supea52795@gmail.com', 'Luong Phu Chau', '0975985541', 'SV-55555', 10, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('maiho@gmail.com', 'Ho Quynh Mai', '0975985541', 'SV-99999', 11, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('lanhnguyen@gmail.com', 'Nguyen Quang Nhat Lanh', '0975985541', 'SV-11111', 12, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('dinluong@gmail.com', 'Luong Vu Quang Din', '0975985541', 'SV-22222', 13, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('danhnguyen@gmail.com', 'Nguyen Quang Danh', '0975985541', 'SV-33333', 14, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('khanhnguyen@gmail.com', 'Nguyen Quoc Khanh', '0975985541', 'SV-44444', 15, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('nhatnguyen@gmail.com', 'Nguyen Hong Nhat', '0975985541', 'SV-66666', 16, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('ngantran@gmail.com', 'Tran Huynh Kim Ngan', '0975985541', 'SV-77777', 17, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('vinhmai@gmail.com', 'Mai The Vinh', '0975985541', 'SV-88888', 18, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('quanhoang@gmail.com', 'Hoang Minh Quan', '0975985541', 'SV-12345', 19, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('quocnguyen@gmail.com', 'Nguyen Tien Quoc', '0975985541', 'SV-23456', 20, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('hoangtran@gmail.com', 'Tran Thanh Hoang', '0975985541', 'SV-34567', 21, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('datnguyen@gmail.com', 'Nguyen Tran Dat', '0975985541', 'SV-45678', 22, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('khanhphan@gmail.com', 'Phan Quoc Khanh', '0975985541', 'SV-56789', 23, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('hoatle@gmail.com', 'Le Van Hoat', '0975985541', 'SV-45679', 24, 1);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('anguyen@gmail.com', 'Nguyen Van An', '0975985541', 'SV-45677', 25, 2);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('bnguyen@gmail.com', 'Nguyen Van Binh', '0975985541', 'SV-45676', 26, 2);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('cnguyen@gmail.com', 'Nguyen Van Chuong', '0975985541', 'SV-45675', 27, 2);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('dnguyen@gmail.com', 'Nguyen Van Dung', '0975985541', 'SV-45674', 28, 3);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('hnguyen@gmail.com', 'Nguyen Van Hien', '0975985541', 'SV-45673', 29, 3);
INSERT INTO project3_thesis_management.student (email, full_name, phone, student_code, app_account_id, class_requirement_id) VALUES ('knguyen@gmail.com', 'Nguyen Van Kien', '0975985541', 'SV-45672', 30, 3);

INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('trungdoan@codegym.com', 'Doan Phuoc Trung', '205555666', '0905123456', 10, 1);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('trungdang@codegym.com', 'Dang Chi Trung', '205555666', '0905456789', 15, 2);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('tiennguyen@codegym.com', 'Nguyen Vu Thanh Tien', '205555666', '0905987321', 10, 3);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('tamtran@codegym.com', 'Tran Thi To Tam', '205555666', '0905987321', 10, 4);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('linhdoan@codegym.com', 'Doan Ngoc Linh', '205555666', '0905987321', 15, 5);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('haitruong@codegym.com', 'Truong Tan Hai', '205555666', '0905987321', 10, 6);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('chanhtran@codegym.com', 'Tran Van Chanh', '205555666', '0905987321', 15, 7);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('nguyenle@codegym.com', 'Le Vu Nguyen', '205555666', '0905987321', 10, 8);
INSERT INTO project3_thesis_management.teacher (email, full_name, identity_number, phone, number_student, app_account_id) VALUES ('nhungnguyen@codegym.com', 'Nguyen Hong Nhung', '205555666', '0905987321', 10, 9);

INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? ????? t??i t???t nghi???p thu???n l???i h??n.', 'Qu???n l?? th???c hi???n ????? t??i t???t nghi???p', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? b??i ????? xe thu???n l???i h??n.', 'Qu???n l?? b??i ????? xe', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? qu?? tr??nh ti??m vacxin thu???n l???i h??n.', 'Qu???n l?? ti??m ch???ng vacxin', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? ph??ng b??n v?? m??y bay thu???n l???i h??n.', 'Qu???n l?? ph??ng b??n v?? m??y bay', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? trang tr???i heo thu???n l???i h??n.', 'Qu???n l?? trang tr???i heo', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? h??? th???ng b??n h??ng thu???n l???i h??n.', 'Qu???n l?? h??? th???ng b??n h??ng', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? kh??ch h??ng thu???n l???i h??n.', 'Qu???n l?? kh??ch h??ng', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? ?? t?? thu???n l???i h??n.', 'Qu???n l?? ?? t??', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? xe m??y thu???n l???i h??n.', 'Qu???n l?? xe m??y', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? nh?? kho thu???n l???i h??n.', 'Qu???n l?? nh?? kho', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? si??u th??? thu???n l???i h??n.', 'Qu???n l?? si??u th???', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? c??ng vi??n thu???n l???i h??n.', 'Qu???n l?? c??ng vi??n', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? kh??ch s???n thu???n l???i h??n.', 'Qu???n l?? kh??ch s???n', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? nh?? ngh??? thu???n l???i h??n.', 'Qu???n l?? nh?? ngh???', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? nh?? h??ng thu???n l???i h??n.', 'Qu???n l?? nh?? h??ng', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? nh?? kh??ch thu???n l???i h??n.', 'Qu???n l?? nh?? kh??ch', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? m??y bay thu???n l???i h??n.', 'Qu???n l?? m??y bay', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? t??u h???a thu???n l???i h??n.', 'Qu???n l?? t??u h???a', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? xe bu??t thu???n l???i h??n.', 'Qu???n l?? xe bu??t', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? taxi thu???n l???i h??n.', 'Qu???n l?? taxi', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? trung t??m thu???n l???i h??n.', 'Qu???n l?? trung t??m th????ng m???i', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? v?????n tr??i c??y thu???n l???i h??n.', 'Qu???n l?? v?????n tr??i c??y', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? b?? s???a thu???n l???i h??n.', 'Qu???n l?? b?? s???a', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? c?? heo thu???n l???i h??n.', 'Qu???n l?? c?? heo', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? c?? m???p thu???n l???i h??n.', 'Qu???n l?? c?? m???p', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? c?? voi xanh thu???n l???i h??n.', 'Qu???n l?? c?? voi xanh', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? c?? voi s??t th??? thu???n l???i h??n.', 'Qu???n l?? c?? voi s??t th???', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim b??? c??u thu???n l???i h??n.', 'Qu???n l?? chim b??? c??u', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim ?????i b??ng thu???n l???i h??n.', 'Qu???n l?? chim ?????i b??ng', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim g?? ki???n thu???n l???i h??n.', 'Qu???n l?? chim g?? ki???n', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim c??nh c???t thu???n l???i h??n.', 'Qu???n l?? chim c??nh c???t', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim ch??o m??o thu???n l???i h??n.', 'Qu???n l?? chim ch??o m??o', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? chim s??? thu???n l???i h??n.', 'Qu???n l?? chim s???', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? kh???ng long thu???n l???i h??n.', 'Qu???n l?? kh???ng long', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? ???? ??i???u thu???n l???i h??n.', 'Qu???n l?? ???? ??i???u', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? g???u tr??c thu???n l???i h??n.', 'Qu???n l?? g???u tr??c', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? Lamborghini thu???n l???i h??n.', 'Qu???n l?? Lamborghini', 9);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? Ferrari thu???n l???i h??n.', 'Qu???n l?? Ferrari', 9);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'H??? th???ng gi??p qu???n l?? Lykan Hypersport thu???n l???i h??n.', 'Qu???n l?? Lykan Hypersport', 9);

CREATE VIEW instruction_status AS
    SELECT 
        project3_thesis_management.teacher.id AS id,
        project3_thesis_management.teacher.full_name AS fullName,
        project3_thesis_management.teacher.email AS email,
        project3_thesis_management.teacher.phone AS phone,
        project3_thesis_management.teacher.number_student AS limitStudentRegister,
        project3_thesis_management.student_group.quantity AS numberStudentRegister,
        SUM(project3_thesis_management.student_group.quantity) AS totalStudentRegister
    FROM
        project3_thesis_management.teacher
            LEFT JOIN
        project3_thesis_management.student_group ON teacher.id = student_group.teacher_id
    GROUP BY project3_thesis_management.teacher.id;
    
CREATE VIEW check_list_thesis AS
    SELECT 
        project3_thesis_management.check_thesis.id AS idCheckThesis,
        project3_thesis_management.teacher.id AS idTeacher,
        project3_thesis_management.student_group.group_name AS groupName,
        project3_thesis_management.student_group.quantity AS quantity,
        project3_thesis_management.thesis.statement AS statement,
        project3_thesis_management.thesis.description AS description,
        project3_thesis_management.check_thesis.check_date AS checkDate,
        project3_thesis_management.check_thesis.status AS status
    FROM
        project3_thesis_management.check_thesis
            INNER JOIN
        project3_thesis_management.student_group ON check_thesis.student_group_id = student_group.id
            INNER JOIN
        project3_thesis_management.teacher ON student_group.teacher_id = teacher.id
            INNER JOIN
        project3_thesis_management.thesis ON check_thesis.thesis_id = thesis.id;    