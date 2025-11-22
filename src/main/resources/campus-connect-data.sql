
-- ===============================
-- CAMPUS CONNECT ADDITIONAL DATA
-- ===============================
-- Additional Members for Campus Connect
INSERT INTO members (
	username, email, password, mobile, name, 
	"aadhar_no", address, created_timestamp, updated_timestamp
) VALUES
-- Administrators & Staff (11-15)
('admin.campus', 'admin@campus.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9999999999', 'Campus Administrator', '111111111111', 'Administration Block, Campus', NOW(), NOW()),
('dr.johnson', 'johnson@campus.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9888888888', 'Dr. Sarah Johnson', '222222222222', 'Faculty Quarters Block-A', NOW(), NOW()),
('prof.kumar', 'kumar@campus.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9777777777', 'Prof. Amit Kumar', '333333333333', 'Faculty Quarters Block-B', NOW(), NOW()),
('librarian.smith', 'smith@campus.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9666666666', 'Jennifer Smith', '444444444444', 'Library Staff Quarters', NOW(), NOW()),
('security.raj', 'raj@campus.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9555555555', 'Rajesh Kumar', '555555555555', 'Security Office', NOW(), NOW()),

-- Students (16-30) - Extended student list
('student.alice', 'alice@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9111111111', 'Alice Brown', '666666666666', 'Hostel A-101', NOW(), NOW()),
('student.bob', 'bob@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9222222222', 'Bob Wilson', '777777777777', 'Hostel B-205', NOW(), NOW()),
('student.carol', 'carol@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9333333333', 'Carol Davis', '888888888888', 'Hostel A-315', NOW(), NOW()),
('student.david', 'david@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9444444444', 'David Miller', '999999999999', 'Hostel C-120', NOW(), NOW()),
('student.emma', 'emma@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '9000000000', 'Emma Taylor', '101010101010', 'Hostel D-210', NOW(), NOW()),
('student.frank', 'frank@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8999999999', 'Frank Anderson', '121212121212', 'Hostel B-315', NOW(), NOW()),
('student.grace', 'grace@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8888888888', 'Grace Thomas', '131313131313', 'Hostel A-220', NOW(), NOW()),
('student.henry', 'henry@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8777777777', 'Henry Jackson', '141414141414', 'Hostel C-305', NOW(), NOW()),
('student.isabel', 'isabel@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8666666666', 'Isabel Martinez', '151515151515', 'Hostel D-115', NOW(), NOW()),
('student.jack', 'jack@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8555555555', 'Jack White', '161616161616', 'Hostel B-410', NOW(), NOW()),
('student.kate', 'kate@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8444444444', 'Kate Thompson', '171717171717', 'Hostel A-405', NOW(), NOW()),
('student.leo', 'leo@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8333333333', 'Leo Garcia', '181818181818', 'Hostel C-210', NOW(), NOW()),
('student.maya', 'maya@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8222222222', 'Maya Patel', '191919191919', 'Hostel D-305', NOW(), NOW()),
('student.noah', 'noah@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8111111111', 'Noah Rodriguez', '202020202020', 'Hostel B-120', NOW(), NOW()),
('student.olivia', 'olivia@student.edu', '$2a$10$ix4H8Tvaga./6cYdkpZCxuzzXU3I62ahG2tBPI04PUkfW7qOzSWim', '8000000000', 'Olivia Chen', '212121212121', 'Hostel A-510', NOW(), NOW());

-- Additional Roles
INSERT INTO roles (role_id, role_name) VALUES (3, 'STUDENT') ON CONFLICT DO NOTHING;
INSERT INTO roles (role_id, role_name) VALUES (4, 'FACULTY') ON CONFLICT DO NOTHING;
INSERT INTO roles (role_id, role_name) VALUES (5, 'STAFF') ON CONFLICT DO NOTHING;

-- Campus Connect Authorities
INSERT INTO authorities (authority_id, authority_name) VALUES (41, 'EVENT_CREATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (42, 'EVENT_UPDATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (43, 'EVENT_DELETE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (44, 'EVENT_VIEW') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (45, 'EVENT_SEARCH') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (51, 'NOTICE_CREATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (52, 'NOTICE_UPDATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (53, 'NOTICE_DELETE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (54, 'NOTICE_VIEW') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (55, 'NOTICE_SEARCH') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (61, 'LOST_ITEM_CREATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (62, 'LOST_ITEM_UPDATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (63, 'LOST_ITEM_DELETE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (64, 'LOST_ITEM_VIEW') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (65, 'LOST_ITEM_SEARCH') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (71, 'FOUND_ITEM_CREATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (72, 'FOUND_ITEM_UPDATE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (73, 'FOUND_ITEM_DELETE') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (74, 'FOUND_ITEM_VIEW') ON CONFLICT DO NOTHING;
INSERT INTO authorities (authority_id, authority_name) VALUES (75, 'FOUND_ITEM_SEARCH') ON CONFLICT DO NOTHING;
-- Role-Authority Mappings for Campus Connect
INSERT INTO role_authority_map(role_id, authority_id) VALUES
-- ADMIN role (2) - Full access to all campus connect features
(2, 41), (2, 42), (2, 43), (2, 44), (2, 45),
(2, 51), (2, 52), (2, 53), (2, 54), (2, 55),
(2, 61), (2, 62), (2, 63), (2, 64), (2, 65),
(2, 71), (2, 72), (2, 73), (2, 74), (2, 75),
-- STUDENT role (3) - View events/notices, manage own lost/found items
(3, 44), (3, 45), (3, 54), (3, 55),
(3, 61), (3, 62), (3, 64), (3, 65),
(3, 71), (3, 72), (3, 74), (3, 75),
-- FACULTY role (4) - Create/manage events and notices, manage lost/found items
(4, 41), (4, 42), (4, 44), (4, 45), (4, 51), (4, 52), (4, 54), (4, 55),
(4, 61), (4, 62), (4, 64), (4, 65),
(4, 71), (4, 72), (4, 74), (4, 75),
-- STAFF role (5) - Administrative access to notices and lost/found items
(5, 44), (5, 45), (5, 51), (5, 52), (5, 54), (5, 55),
(5, 61), (5, 62), (5, 64), (5, 65),
(5, 71), (5, 72), (5, 74), (5, 75);

-- User-Role Mappings for Campus Connect Members
INSERT INTO user_role_map(user_id, role_id) VALUES
(11, 2), -- Campus Administrator - ADMIN
(12, 4), -- Dr. Sarah Johnson - FACULTY
(13, 4), -- Prof. Amit Kumar - FACULTY  
(14, 5), -- Jennifer Smith (Librarian) - STAFF
(15, 5), -- Rajesh Kumar (Security) - STAFF
-- Students (16-30) - STUDENT role
(16, 3), (17, 3), (18, 3), (19, 3), (20, 3),
(21, 3), (22, 3), (23, 3), (24, 3), (25, 3),
(26, 3), (27, 3), (28, 3), (29, 3), (30, 3);

-- Campus Events (15 records)
INSERT INTO events (title, description, event_date, location, status, image_url, created_timestamp, updated_timestamp, posted_by) VALUES 
('Annual Tech Fest 2025', 'Join us for the biggest technology festival featuring workshops, competitions, and exhibitions from top tech companies.', CURRENT_TIMESTAMP + INTERVAL '30 days', 'Main Auditorium', 'ACTIVE', '/images/tech-fest-2025.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Career Fair 2025', 'Meet with recruiters from leading companies. Bring your resume and dress professionally.', CURRENT_TIMESTAMP + INTERVAL '15 days', 'Student Center Hall', 'ACTIVE', '/images/career-fair.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Cultural Night', 'Showcase your talents! Music, dance, drama, and art performances by students.', CURRENT_TIMESTAMP + INTERVAL '7 days', 'University Theater', 'ACTIVE', '/images/cultural-night.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('Science Exhibition', 'Display of innovative projects by science students across all departments.', CURRENT_TIMESTAMP + INTERVAL '20 days', 'Science Block Atrium', 'ACTIVE', '/images/science-expo.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Sports Day 2025', 'Annual sports competition with various indoor and outdoor games.', CURRENT_TIMESTAMP + INTERVAL '25 days', 'Sports Complex', 'ACTIVE', '/images/sports-day.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Coding Marathon', '48-hour coding competition with exciting prizes and industry mentorship.', CURRENT_TIMESTAMP + INTERVAL '12 days', 'Computer Lab Complex', 'ACTIVE', '/images/coding-marathon.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('Green Campus Initiative', 'Tree plantation drive and environmental awareness program.', CURRENT_TIMESTAMP + INTERVAL '5 days', 'University Garden', 'ACTIVE', '/images/green-campus.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
('Alumni Meet 2025', 'Connect with successful alumni and learn from their experiences.', CURRENT_TIMESTAMP + INTERVAL '35 days', 'Convention Center', 'ACTIVE', '/images/alumni-meet.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Research Symposium', 'Presentation of research papers by faculty and PhD students.', CURRENT_TIMESTAMP + INTERVAL '18 days', 'Conference Hall', 'ACTIVE', '/images/research-symposium.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Entrepreneurship Workshop', 'Learn about starting your own business from successful entrepreneurs.', CURRENT_TIMESTAMP + INTERVAL '10 days', 'Business Incubation Center', 'ACTIVE', '/images/entrepreneur-workshop.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('Photography Exhibition', 'Annual photography contest showcase featuring student works on campus life.', CURRENT_TIMESTAMP + INTERVAL '8 days', 'Art Gallery', 'ACTIVE', '/images/photo-exhibition.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Blood Donation Camp', 'Voluntary blood donation drive in association with city hospital.', CURRENT_TIMESTAMP + INTERVAL '14 days', 'Medical Center', 'ACTIVE', '/images/blood-donation.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14),
('International Food Festival', 'Taste cuisines from around the world prepared by international students.', CURRENT_TIMESTAMP + INTERVAL '21 days', 'Campus Grounds', 'ACTIVE', '/images/food-festival.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Debate Competition', 'Inter-college debate competition on current social and economic issues.', CURRENT_TIMESTAMP + INTERVAL '16 days', 'Auditorium Hall', 'ACTIVE', '/images/debate-competition.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('Mental Health Awareness', 'Workshop on stress management and mental wellness for students.', CURRENT_TIMESTAMP + INTERVAL '6 days', 'Counseling Center', 'ACTIVE', '/images/mental-health.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14);

-- Campus Notices (12 records)
INSERT INTO notices (title, description, priority, category, valid_until, status, attachment_url, created_timestamp, updated_timestamp, posted_by) VALUES 
('Semester Exam Schedule Released', 'The final examination schedule for the current semester has been released. Students can check their exam dates and timings on the university portal.', 'HIGH', 'ACADEMIC', CURRENT_TIMESTAMP + INTERVAL '60 days', 'ACTIVE', '/attachments/exam-schedule.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Library Hours Extended', 'Due to upcoming exams, the library will remain open 24/7 starting next week. Students need to show their ID cards for access after 10 PM.', 'NORMAL', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '45 days', 'ACTIVE', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14),
('New Parking Regulations', 'Please note the new parking regulations effective immediately. All vehicles must display valid parking permits. Unauthorized parking will result in fines.', 'HIGH', 'ADMINISTRATIVE', NULL, 'ACTIVE', '/attachments/parking-rules.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15),
('Sports Week Registration Open', 'Registration for annual sports week is now open. Multiple sports categories available. Register at the sports office or online portal.', 'NORMAL', 'EVENT', CURRENT_TIMESTAMP + INTERVAL '20 days', 'ACTIVE', '/attachments/sports-registration.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Scholarship Applications Due', 'Merit-based scholarship applications for next semester are due by end of this month. Submit all required documents to the scholarship office.', 'HIGH', 'ACADEMIC', CURRENT_TIMESTAMP + INTERVAL '15 days', 'ACTIVE', '/attachments/scholarship-form.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Campus WiFi Maintenance', 'Campus-wide WiFi maintenance scheduled for this weekend. Internet services may be intermittent during this period.', 'NORMAL', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '3 days', 'ACTIVE', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15),
('Guest Lecture Series', 'Industry experts will be conducting guest lectures throughout the month. Check department notice boards for schedules.', 'NORMAL', 'ACADEMIC', CURRENT_TIMESTAMP + INTERVAL '30 days', 'ACTIVE', '/attachments/lecture-schedule.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('Fire Safety Drill', 'Mandatory fire safety drill scheduled for all buildings next Tuesday at 2 PM. All students and staff must participate.', 'HIGH', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '7 days', 'ACTIVE', '/attachments/fire-safety-info.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
('Student ID Card Renewal', 'Student ID card renewal process starts next week. Bring passport size photo and current ID card.', 'NORMAL', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '25 days', 'ACTIVE', '/attachments/id-renewal.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14),
('Hostel Room Allocation', 'New semester hostel room allocation results are published. Check your allocation on the student portal.', 'HIGH', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '30 days', 'ACTIVE', '/attachments/hostel-allocation.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15),
('Research Paper Submission', 'Final date for research paper submission for the annual conference extended to next month.', 'NORMAL', 'ACADEMIC', CURRENT_TIMESTAMP + INTERVAL '35 days', 'ACTIVE', '/attachments/research-submission.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Campus Security Update', 'New security protocols implemented. All students must carry valid ID cards at all times on campus.', 'HIGH', 'ADMINISTRATIVE', NULL, 'ACTIVE', '/attachments/security-update.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15);

-- Lost Items (15 records)
INSERT INTO lost_items (item_name, description, location, lost_date, status, category, reward_amount, contact_info, urgent, is_anonymous, created_timestamp, updated_timestamp, reported_by) VALUES 
('Blue Backpack', 'Blue Jansport backpack with my name tag inside. Contains important textbooks and laptop', 'Library 2nd Floor', CURRENT_TIMESTAMP - INTERVAL '2 days', 'PENDING', 'ACCESSORIES', 50.0, 'alice@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
('iPhone 12', 'Black iPhone 12 with a red protective case. Has a crack on the screen', 'Student Center Cafeteria', CURRENT_TIMESTAMP - INTERVAL '1 days', 'SEARCHING', 'ELECTRONICS', 100.0, 'bob@student.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 17),
('Car Keys', 'Honda keys with university keychain and small teddy bear keyring', 'Parking Lot B', CURRENT_TIMESTAMP - INTERVAL '3 days', 'PENDING', 'OTHER', 20.0, 'johnson@campus.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
('Laptop Charger', 'Dell laptop charger, 65W with black cable. Model PA-1650-02D3', 'Computer Lab 301', CURRENT_TIMESTAMP - INTERVAL '4 days', 'SEARCHING', 'ELECTRONICS', 30.0, 'carol@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 18),
('Red Wallet', 'Red leather wallet with student ID and some cash inside', 'Canteen Area', CURRENT_TIMESTAMP - INTERVAL '1 days', 'PENDING', 'ACCESSORIES', 40.0, 'david@student.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 19),
('Chemistry Textbook', 'Organic Chemistry by Morrison & Boyd, heavily highlighted', 'Chemistry Lab 205', CURRENT_TIMESTAMP - INTERVAL '5 days', 'PENDING', 'BOOKS', 15.0, 'emma@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 20),
('Black Umbrella', 'Large black umbrella with wooden handle', 'Main Entrance', CURRENT_TIMESTAMP - INTERVAL '2 days', 'SEARCHING', 'OTHER', 10.0, 'frank@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 21),
('Wireless Earbuds', 'Apple AirPods Pro in white charging case', 'Gymnasium', CURRENT_TIMESTAMP - INTERVAL '3 days', 'PENDING', 'ELECTRONICS', 80.0, 'grace@student.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 22),
('Calculator', 'Casio scientific calculator FX-991ES Plus', 'Mathematics Department', CURRENT_TIMESTAMP - INTERVAL '6 days', 'PENDING', 'OTHER', 25.0, 'henry@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 23),
('Water Bottle', 'Stainless steel water bottle with university logo', 'Sports Complex', CURRENT_TIMESTAMP - INTERVAL '1 days', 'SEARCHING', 'ACCESSORIES', 12.0, 'isabel@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 24),
('MacBook Pro', 'Silver MacBook Pro 13-inch with engineering stickers on lid', 'Engineering Lab 405', CURRENT_TIMESTAMP - INTERVAL '2 days', 'PENDING', 'ELECTRONICS', 200.0, 'kate@student.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 26),
('Gold Ring', 'Small gold ring with engraved initials "ML"', 'Gymnasium Shower Area', CURRENT_TIMESTAMP - INTERVAL '4 days', 'SEARCHING', 'ACCESSORIES', 150.0, 'leo@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 27),
('Prescription Medicines', 'Blue medicine bottle with diabetes medication', 'Student Health Center', CURRENT_TIMESTAMP - INTERVAL '1 days', 'PENDING', 'OTHER', 0.0, 'maya@student.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 28),
('Bicycle Helmet', 'Red and black cycling helmet with "SPECIALIZED" brand', 'Bicycle Parking Area', CURRENT_TIMESTAMP - INTERVAL '3 days', 'SEARCHING', 'ACCESSORIES', 35.0, 'noah@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 29),
('Research Notebook', 'Black hardcover notebook with physics equations and diagrams', 'Physics Department', CURRENT_TIMESTAMP - INTERVAL '5 days', 'PENDING', 'BOOKS', 25.0, 'olivia@student.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30);

-- Found Items (12 records)
INSERT INTO found_items (item_name, description, location, found_date, status, category, contact_info, distinctive_features, handover_location, verification_required, is_anonymous, created_timestamp, updated_timestamp, reported_by) VALUES 
('Red Notebook', 'Red spiral notebook with chemistry notes and formulas', 'Science Building Room 205', CURRENT_TIMESTAMP - INTERVAL '1 days', 'AVAILABLE', 'BOOKS', 'jack@student.edu', 'Has "Sarah" written on the cover', 'Student Services Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 25),
('Silver Watch', 'Silver digital watch, brand appears to be Casio with black strap', 'Gymnasium Locker Room', CURRENT_TIMESTAMP - INTERVAL '2 days', 'AVAILABLE', 'ACCESSORIES', 'raj@campus.edu', 'Shows time in 24-hour format, has small scratch on face', 'Security Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15),
('Reading Glasses', 'Black frame reading glasses in a brown leather case', 'Library Study Room 3', CURRENT_TIMESTAMP - INTERVAL '3 days', 'AVAILABLE', 'ACCESSORIES', 'smith@campus.edu', 'Progressive lenses, slight wear on nose pads', 'Library Information Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14),
('Mobile Phone', 'Samsung Galaxy phone with cracked screen protector', 'Cafeteria Table 12', CURRENT_TIMESTAMP - INTERVAL '1 days', 'AVAILABLE', 'ELECTRONICS', 'alice@student.edu', 'Blue protective case with stickers', 'Student Affairs Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
('Textbook', 'Engineering Mathematics by K.A. Stroud', 'Engineering Block Corridor', CURRENT_TIMESTAMP - INTERVAL '4 days', 'AVAILABLE', 'BOOKS', 'kumar@campus.edu', 'Bookmarks on pages 150-200', 'Faculty Office 301', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
('House Keys', 'Set of 4 keys on a metal ring with car remote', 'Parking Lot A', CURRENT_TIMESTAMP - INTERVAL '2 days', 'AVAILABLE', 'OTHER', 'bob@student.edu', 'Honda car remote attached', 'Security Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 17),
('Headphones', 'Sony over-ear headphones, black color', 'Music Room 105', CURRENT_TIMESTAMP - INTERVAL '3 days', 'AVAILABLE', 'ELECTRONICS', 'carol@student.edu', 'Slightly worn padding', 'Library Front Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 18),
('Scarf', 'Woolen scarf, blue and white striped', 'Main Building Entrance', CURRENT_TIMESTAMP - INTERVAL '5 days', 'AVAILABLE', 'CLOTHING', 'david@student.edu', 'Soft wool material, about 6 feet long', 'Lost & Found Counter', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 19),
('USB Drive', '32GB SanDisk USB drive with blue casing', 'Computer Lab 205', CURRENT_TIMESTAMP - INTERVAL '2 days', 'AVAILABLE', 'ELECTRONICS', 'kate@student.edu', 'Has a keychain attached', 'IT Help Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 26),
('Coffee Mug', 'White ceramic mug with "World''s Best Student" text', 'Library Café', CURRENT_TIMESTAMP - INTERVAL '1 days', 'AVAILABLE', 'OTHER', 'leo@student.edu', 'Small chip on the handle', 'Library Reception', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 27),
('Earrings', 'Pair of silver hoop earrings in small velvet pouch', 'Student Union Building', CURRENT_TIMESTAMP - INTERVAL '3 days', 'AVAILABLE', 'ACCESSORIES', 'maya@student.edu', 'Medium size hoops, slightly tarnished', 'Student Affairs Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 28),
('Lunch Box', 'Blue plastic lunch box with cartoon characters', 'Cafeteria Table Area', CURRENT_TIMESTAMP - INTERVAL '4 days', 'AVAILABLE', 'OTHER', 'noah@student.edu', 'Contains spoon and fork', 'Cafeteria Counter', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 29);

