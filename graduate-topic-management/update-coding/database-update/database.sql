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

INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý đề tài tốt nghiệp thuận lợi hơn.', 'Quản lý thực hiện đề tài tốt nghiệp', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý bãi đỗ xe thuận lợi hơn.', 'Quản lý bãi đỗ xe', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý quá trình tiêm vacxin thuận lợi hơn.', 'Quản lý tiêm chủng vacxin', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý phòng bán vé máy bay thuận lợi hơn.', 'Quản lý phòng bán vé máy bay', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý trang trại heo thuận lợi hơn.', 'Quản lý trang trại heo', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý hệ thống bán hàng thuận lợi hơn.', 'Quản lý hệ thống bán hàng', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý khách hàng thuận lợi hơn.', 'Quản lý khách hàng', 1);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý ô tô thuận lợi hơn.', 'Quản lý ô tô', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý xe máy thuận lợi hơn.', 'Quản lý xe máy', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý nhà kho thuận lợi hơn.', 'Quản lý nhà kho', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý siêu thị thuận lợi hơn.', 'Quản lý siêu thị', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý công viên thuận lợi hơn.', 'Quản lý công viên', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý khách sạn thuận lợi hơn.', 'Quản lý khách sạn', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý nhà nghỉ thuận lợi hơn.', 'Quản lý nhà nghỉ', 2);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý nhà hàng thuận lợi hơn.', 'Quản lý nhà hàng', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý nhà khách thuận lợi hơn.', 'Quản lý nhà khách', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý máy bay thuận lợi hơn.', 'Quản lý máy bay', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý tàu hỏa thuận lợi hơn.', 'Quản lý tàu hỏa', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý xe buýt thuận lợi hơn.', 'Quản lý xe buýt', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý taxi thuận lợi hơn.', 'Quản lý taxi', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý trung tâm thuận lợi hơn.', 'Quản lý trung tâm thương mại', 3);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý vườn trái cây thuận lợi hơn.', 'Quản lý vườn trái cây', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (5, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý bò sữa thuận lợi hơn.', 'Quản lý bò sữa', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý cá heo thuận lợi hơn.', 'Quản lý cá heo', 4);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý cá mập thuận lợi hơn.', 'Quản lý cá mập', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý cá voi xanh thuận lợi hơn.', 'Quản lý cá voi xanh', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý cá voi sát thủ thuận lợi hơn.', 'Quản lý cá voi sát thủ', 5);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim bồ câu thuận lợi hơn.', 'Quản lý chim bồ câu', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim đại bàng thuận lợi hơn.', 'Quản lý chim đại bàng', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim gõ kiến thuận lợi hơn.', 'Quản lý chim gõ kiến', 6);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim cánh cụt thuận lợi hơn.', 'Quản lý chim cánh cụt', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim chào mào thuận lợi hơn.', 'Quản lý chim chào mào', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý chim sẻ thuận lợi hơn.', 'Quản lý chim sẻ', 7);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý khủng long thuận lợi hơn.', 'Quản lý khủng long', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý đà điểu thuận lợi hơn.', 'Quản lý đà điểu', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý gấu trúc thuận lợi hơn.', 'Quản lý gấu trúc', 8);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý Lamborghini thuận lợi hơn.', 'Quản lý Lamborghini', 9);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý Ferrari thuận lợi hơn.', 'Quản lý Ferrari', 9);
INSERT INTO project3_thesis_management.thesis (amount, create_date, description, statement, teacher_id) VALUES (3, '2021-01-21 11:28:59', 'Hệ thống giúp quản lý Lykan Hypersport thuận lợi hơn.', 'Quản lý Lykan Hypersport', 9);

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