-- Create the database
USE [master]

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'SWP391_SE1815')
	DROP DATABASE SWP391_SE1815

CREATE DATABASE SWP391_SE1815;

USE SWP391_SE1815;

-- Create the Roles table
CREATE TABLE Roles (
    role_id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(20) NOT NULL
);

-- Create the Users table
CREATE TABLE Users (
    user_id INT IDENTITY(1, 1) PRIMARY KEY,
    role_id INT NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    full_name NVARCHAR(100) NULL,
    email VARCHAR(100) NULL,
    birth_date DATE NULL,
    image VARCHAR(255) NULL,
    phone_number VARCHAR(20) NULL,
    address NVARCHAR(255) NULL,
    created_at DATETIME NOT NULL,
    banned BIT NOT NULL DEFAULT 0,
    CONSTRAINT FK_Users_Roles FOREIGN KEY (role_id) REFERENCES Roles (role_id)
);

-- Create the Categories table
CREATE TABLE Categories (
    category_id INT IDENTITY(1, 1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(100) NULL
);

-- Create the Courses table
CREATE TABLE Courses (
    course_id INT IDENTITY(1, 1) PRIMARY KEY,
    title NVARCHAR(100) NOT NULL,
	owner_id INT NOT NULL,
	category_id INT,
    description NVARCHAR(MAX) NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
--    deleted BIT NOT NULL DEFAULT 0,
	CONSTRAINT FK_Courses_Users FOREIGN KEY (owner_id) REFERENCES Users (user_id),
	CONSTRAINT FK_Courses_category FOREIGN KEY (category_id) REFERENCES Categories (category_id)

);

CREATE TABLE CourseContents (
	content_id INT IDENTITY(1, 1) PRIMARY KEY,
	course_id INT NOT NULL,
	type VARCHAR(10) NOT NULL,
	content_path VARCHAR(50) NULL,
    CONSTRAINT FK_CourseContents_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id)
);

--Luu id cua user da lam xong content nao
CREATE TABLE UserProgress (
	user_id INT NOT NULL,
	content_id INT NOT NULL,
	grade INT NOT NULL,
	CONSTRAINT PK_UserProgress PRIMARY KEY (user_id, content_id),
    CONSTRAINT FK_UserProgress_Users FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT FK_UserProgress_CourseContents FOREIGN KEY (content_id) REFERENCES CourseContents (content_id)
);



-- Create the Feedback table
CREATE TABLE Feedback (
    feedback_id INT IDENTITY(1, 1) PRIMARY KEY,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    rating INT NOT NULL,
    comment NVARCHAR(100) NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT FK_Feedback_Users FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT FK_Feedback_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id)
);

-- Create the Orders table
CREATE TABLE Orders (
    order_id INT IDENTITY(1, 1) PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATETIME NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    CONSTRAINT FK_Orders_Users FOREIGN KEY (user_id) REFERENCES Users (user_id),
);

-- Create the OrderItems table to link AddToCart with Orders
CREATE TABLE OrderItems (
    order_id INT NOT NULL,
    course_id INT NOT NULL,
    CONSTRAINT PK_OrderItems PRIMARY KEY (order_id, course_id),
    CONSTRAINT FK_OrderItems_Orders FOREIGN KEY (order_id) REFERENCES Orders (order_id),
    CONSTRAINT FK_OrderItems_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id)
);

-- Create the Cart table
CREATE TABLE Cart (
    user_id INT NOT NULL,
    course_id INT NOT NULL,
	CONSTRAINT PK_Cart PRIMARY KEY (user_id, course_id),
    CONSTRAINT FK_Cart_Users FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT FK_Cart_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id)
);

-- Create the Purchases table
CREATE TABLE Purchases (
    user_id INT NOT NULL,
    course_id INT NOT NULL,
	CONSTRAINT PK_Purchases PRIMARY KEY (user_id, course_id),
    CONSTRAINT FK_Purchases_Users FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT FK_Purchases_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id)
);


INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(1, 'admin', 'admin123', 'Admin User', 'admin@example.com', '1980-01-01', NULL, '1234567890', '123 Admin Street', GETDATE(), 0),
(2, 'john_doe', 'password123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0),
(3, 'jane_smith', 'securepassword', 'Jane Smith', 'jane.smith@example.com', '1985-08-25', NULL, '1122334455', '789 Jane Street', GETDATE(), 0);

INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'thanhvu', 'password123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'thanh', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'thanhviet', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'thanhvietvu', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh1', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh12', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh123', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh1234', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh12345', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)

INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh123456', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)

INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'huongthoi', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh12345678', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'minh123456789', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'hao', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES 
(2, 'thanhvu2k4', '123', 'John Doe', 'vuthanhml102@gmail.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)








INSERT INTO Roles (name)
VALUES ('Admin'), ('User'), ('Expert');

select * from Roles
select * from Users

select * from Users u where  u.username = 'john_doe' and u.password = 'password123'

-- update 27/05
CREATE TABLE Experts (
    expert_id INT IDENTITY(1, 1) PRIMARY KEY,
    user_id INT NOT NULL,
    description NVARCHAR(MAX) NOT NULL,
    certification NVARCHAR(MAX), -- Missing comma added here
    CONSTRAINT FK_Experts_Users FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE ExpertCourses (
    expert_id INT NOT NULL,
    course_id INT NOT NULL,
    CONSTRAINT FK_ExpertCourses_Experts FOREIGN KEY (expert_id) REFERENCES Experts (expert_id),
    CONSTRAINT FK_ExpertCourses_Courses FOREIGN KEY (course_id) REFERENCES Courses (course_id),
    CONSTRAINT PK_ExpertCourses PRIMARY KEY (expert_id, course_id)
);

INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES (3, 'ranga_karanam', 'ranga_karaman', 'ranga', 'ranga@example.com', '1980-01-01', NULL, '1234567890', '123 Tech Street', GETDATE(), 0);
INSERT INTO Users (role_id, username, password, full_name, email, birth_date, image, phone_number, address, created_at, banned)
VALUES (3, 'thanh123', 'thanh', 'vu', 'ranga@example.com', '1980-01-01', NULL, '1234567890', '123 Tech Street', GETDATE(), 0);


INSERT INTO Experts (user_id, description, certification)
VALUES (
    (SELECT user_id FROM Users WHERE username = 'thanh123'),
    'Ranga Karanam, the founder of in28minutes, has 2 decades of experience with technology - architecture, design, and programming. We are helping learners take their first steps into modern cloud native technology and gain expertise on AWS, Azure, Google Cloud, Docker, Kubernetes amongst others.\n\nOur happiest moments are when learners tag us on LinkedIn or reach out to us with their stories of getting their first job, getting a promotion, or a big raise.',
    'Ranga is multi-cloud certified - Google Cloud Certified Professional Cloud Architect, AWS Certified Solutions Architect Associate, Google Cloud Certified Associate Cloud Engineer, AWS Certified Developer Associate, AWS Certified Cloud Practitioner, Azure Fundamentals Certified AZ-900, DP-900 and AI-900.'
);
INSERT INTO Experts (user_id, description, certification)
VALUES (
    (SELECT user_id FROM Users WHERE username = 'ranga_karanam'),
    'Ranga Karanam, the founder of in28minutes, has 2 decades of experience with technology - architecture, design, and programming. We are helping learners take their first steps into modern cloud native technology and gain expertise on AWS, Azure, Google Cloud, Docker, Kubernetes amongst others.\n\nOur happiest moments are when learners tag us on LinkedIn or reach out to us with their stories of getting their first job, getting a promotion, or a big raise.',
    'Ranga is multi-cloud certified - Google Cloud Certified Professional Cloud Architect, AWS Certified Solutions Architect Associate, Google Cloud Certified Associate Cloud Engineer, AWS Certified Developer Associate, AWS Certified Cloud Practitioner, Azure Fundamentals Certified AZ-900, DP-900 and AI-900.'
);

select * from Experts
delete from Expert e
where e.expert_id = 2
select * from Users

select * from Experts e
join Users u on e.user_id = u.user_id
where expert_id = 1

-- update 30/5
INSERT INTO Categories (name, description)
VALUES 
('Programming', 'Courses related to programming and software development'),
('Data Science', 'Courses related to data analysis, machine learning, and AI'),
('Web Development', 'Courses related to building and designing websites'),
('Cloud Computing', 'Courses related to cloud services and architecture'),
('Cyber Security', 'Courses related to security in the digital world');

select * from Categories

ALTER TABLE Courses
DROP CONSTRAINT FK_Courses_Users;

ALTER TABLE Courses
DROP COLUMN owner_id;

INSERT INTO Courses (title, category_id, description, price, created_at, updated_at)
VALUES 
('Java Programming Basics', 1, 'Learn the basics of Java programming, including syntax, variables, and control flow.', 99.99, GETDATE(), NULL),
('Advanced Machine Learning', 2, 'An advanced course on machine learning algorithms and techniques.', 199.99, GETDATE(), NULL),
('Full-Stack Web Development', 3, 'Become a full-stack web developer by learning both front-end and back-end technologies.', 149.99, GETDATE(), NULL),
('AWS Cloud Essentials', 4, 'Get started with Amazon Web Services and understand the core services and architecture.', 79.99, GETDATE(), NULL),
('Introduction to Cyber Security', 5, 'Learn the fundamentals of cyber security, including threat detection and mitigation.', 129.99, GETDATE(), NULL),
('Python for Data Science', 2, 'Master Python programming and its applications in data science and analytics.', 109.99, GETDATE(), NULL),
('React.js for Beginners', 3, 'An introductory course to React.js, a popular JavaScript library for building user interfaces.', 89.99, GETDATE(), NULL),
('Google Cloud Platform Fundamentals', 4, 'Learn the basics of Google Cloud Platform services and how to use them effectively.', 99.99, GETDATE(), NULL),
('Ethical Hacking and Penetration Testing', 5, 'An in-depth course on ethical hacking and penetration testing techniques.', 149.99, GETDATE(), NULL),
('SQL for Data Analysis', 2, 'Learn SQL from scratch and use it to perform data analysis and manipulation.', 59.99, GETDATE(), NULL);

INSERT INTO ExpertCourses (expert_id, course_id)
VALUES 
(1, 1), 
(2, 1), 
(1, 2), 
(2, 3),
(3, 4),
(3, 5), 
(1, 6), 
(2, 7), 
(3, 8), 
(1, 9), 
(2, 10); 

select * from ExpertCourses
select * from Courses

INSERT INTO Orders (user_id, order_date, total_amount)
VALUES 
(1, '2023-06-01 12:34:56', 99.99),
(2, '2023-06-05 08:15:30', 199.99),
(3, '2023-06-10 10:20:00', 149.99),
(1, '2023-06-15 14:45:00', 79.99),
(2, '2023-06-20 16:30:45', 129.99),
(3, '2023-06-25 18:10:20', 109.99),
(1, '2023-06-30 11:22:33', 89.99),
(2, '2023-07-01 09:40:00', 99.99),
(3, '2023-07-05 17:50:10', 149.99),
(1, '2023-07-10 20:15:25', 59.99);
select * from Orders
select * from Users
SELECT * FROM Users WHERE user_id IN (1, 2, 3);
INSERT INTO Orders (user_id, order_date, total_amount)
VALUES 
(9, '2023-06-01 12:34:56', 99.99),
(10, '2023-06-05 08:15:30', 199.99),
(11, '2023-06-10 10:20:00', 149.99),
(9, '2023-06-15 14:45:00', 79.99),
(10, '2023-06-20 16:30:45', 129.99),
(11, '2023-06-25 18:10:20', 109.99),
(9, '2023-06-30 11:22:33', 89.99),
(10, '2023-07-01 09:40:00', 99.99),
(11, '2023-07-05 17:50:10', 149.99),
(9, '2023-07-10 20:15:25', 59.99);

INSERT INTO OrderItems (order_id, course_id)
VALUES 
(3, 1),
(5, 2),
(4, 3),
(7, 4),
(3, 5),
(4, 1),
(4, 3),
(5, 2),
(6, 4),
(6, 5),
(7, 1),
(7, 2),
(8, 3),
(8, 4),
(9, 5),
(10, 1),
(10, 2);
-- update 1/6
CREATE TABLE ExpertCategories (
    expert_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT FK_ExpertCategories_Experts FOREIGN KEY (expert_id) REFERENCES Experts (expert_id),
    CONSTRAINT FK_ExpertCategories_Categories FOREIGN KEY (category_id) REFERENCES Categories (category_id),
    CONSTRAINT PK_ExpertCategories PRIMARY KEY (expert_id, category_id)
);

INSERT INTO ExpertCategories (expert_id, category_id)
VALUES 
(1, 1), 
(1, 2), 
(2, 2), 
(2, 3),
(3, 4),
(3, 5);
INSERT INTO ExpertCategories (expert_id, category_id)
Values(4,1)

select * from Experts
select * from Categories
select * from Users
select * from Experts
select * from ExpertCourses
select * from Courses
select * from ExpertCategories
select * from Experts c

select  u.username, c.expert_id, u.email, ca.name from Experts c
join ExpertCategories e on c.expert_id = e.expert_id
join Categories ca on e.category_id = ca.category_id
join Users u on u.user_id = c.user_id
where u.username like '%h%' 



select  * from Experts c
join ExpertCategories e on c.expert_id = e.expert_id
join Categories ca on e.category_id = ca.category_id
join Users u on u.user_id = c.user_id
where u.username like '%h%' 

select * from Experts e
join Users u on e.certification = u.user_id
select * from Experts e where e.expert_id =2
SELECT *
FROM (
    SELECT 
       c.expert_id, 
       u.user_id, 
       c.description, 
       c.certification, 
       u.role_id,
       u.username,
       u.password,
       u.full_name,
       u.email,
       u.birth_date,
       u.image,
       u.phone_number,
       u.address,
       u.created_at,
       u.banned,
       u.failedAttempt,
	   u.lockTime,
       ROW_NUMBER() OVER (PARTITION BY u.username ORDER BY c.expert_id) AS row_num
    FROM Experts c
    JOIN ExpertCategories e ON c.expert_id = e.expert_id
    JOIN Categories ca ON e.category_id = ca.category_id
    JOIN Users u ON u.user_id = c.user_id
    WHERE u.username LIKE '%h%'
) AS subquery
WHERE row_num = 1;

select * from Experts e
join Users u on e.user_id = u.user_id
where u.username like't%'
select * from Users

join Users u on u.user_id = c.user_id
where u.username like '%h%' 


select * from ExpertCourses ec
join Experts e on ec.expert_id = e.expert_id
join Courses c on ec.course_id = c.course_id

update Experts set description = 'haha' where expert_id = 2

select u.username, u.email, c.name
from Experts e
join Users u on e.user_id = u.user_id
join ExpertCategories ec on e.expert_id = ec.expert_id
join Categories c on ec.category_id = c.category_id

update Users set username = 'minhdeptrai', email = 'vosong10x@gmail.com'
where user_id = (select e.user_id
				 from Experts e
				 where expert_id = 2)
update Categories set name 

select * from Users
select * from Experts

select * from Admin
select * from Courses
select * from Courses co
join Categories c
on co.category_id = c.category_id
where c.name like '%P%' and co.title like '%J%'
sec
select * From Courses c
where c.course_id = 1

UPDATE Courses SET description = 'hehe' WHERE course_id = 1;

update Courses set category_id = 2 where course_id = 1
update Courses set description = 'huhu', Title ='Java Test' where course_id =1;
select * from ExpertCategories
select * from Experts
Insert into ExpertCategories Values (1,3)
select * from Categories
select * from Categories where Categories.name = 'Programming'

select * from Users

select * from Users

SELECT u.username
FROM Users u
JOIN Experts e ON u.user_id = e.user_id  
JOIN ExpertCategories ec on e.expert_id = ec.expert_id 
JOIN Categories c ON ec.category_id = c.category_id
WHERE c.name like '%P%'

SELECT u.username
FROM Users u
JOIN Experts e ON u.user_id = e.user_id 

select * from Users
select * from Roles

INSERT INTO Orders (user_id, order_date, total_amount)
VALUES 
(12, '2023-06-01', 300.00),
(13, '2023-06-02', 200.00),
(14, '2023-06-03', 150.00),
(15, '2023-06-04', 400.00),
(16, '2023-06-05', 250.00),
(17, '2023-06-06', 350.00),
(18, '2023-06-07', 450.00),
(19, '2023-06-08', 500.00),
(20, '2023-06-09', 600.00),
(21, '2023-06-10', 700.00);

INSERT INTO OrderItems (order_id, course_id)
VALUES 
(3, 1),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6),
(9, 7),
(10, 8),
(11, 9),
(12, 10),
(13, 2),
(14, 3),
(15, 4);

select * from Orders

select * from Courses
SELECT * 
FROM Orders 
WHERE order_date BETWEEN '2023-06-16' AND '2023-06-30';

UPDATE Orders
SET order_date = DATEADD(year, 1, order_date)
WHERE YEAR(order_date) = 2023;

SELECT Month(o.order_date),sum(o.total_amount) as monthly
FROM Orders o
group by MONTH(o.order_date)
select * from Users
INSERT INTO Orders (user_id, order_date, total_amount)
VALUES 
(12, '2024-01-05', 300.00),
(13, '2024-01-15', 150.00),
(14, '2024-01-25', 450.00),
(15, '2024-02-10', 500.00),
(16, '2024-02-20', 300.00),
(17, '2024-02-28', 200.00),
(18, '2024-03-05', 350.00),
(19, '2024-03-15', 400.00),
(20, '2024-03-25', 250.00),
(21, '2024-04-05', 300.00),
(22, '2024-04-15', 150.00),
(23, '2024-04-25', 450.00),
(12, '2024-05-10', 500.00),
(13, '2024-05-20', 300.00),
(14, '2024-05-28', 200.00),
(15, '2024-06-01', 300.00),
(16, '2024-06-02', 200.00),
(17, '2024-06-03', 150.00),
(18, '2024-06-04', 400.00),
(19, '2024-06-05', 250.00),
(20, '2024-06-06', 350.00),
(21, '2024-06-07', 450.00),
(22, '2024-06-08', 500.00),
(23, '2024-06-09', 600.00),
(12, '2024-06-10', 700.00),
(13, '2024-07-05', 300.00),
(14, '2024-07-15', 150.00),
(15, '2024-07-25', 450.00),
(16, '2024-08-10', 500.00),
(17, '2024-08-20', 300.00),
(18, '2024-08-28', 200.00),
(19, '2024-09-05', 350.00),
(20, '2024-09-15', 400.00),
(21, '2024-09-25', 250.00),
(22, '2024-10-05', 300.00),
(23, '2024-10-15', 150.00),
(12, '2024-10-25', 450.00),
(13, '2024-11-10', 500.00),
(14, '2024-11-20', 300.00),
(15, '2024-11-28', 200.00),
(16, '2024-12-05', 350.00),
(17, '2024-12-15', 400.00),
(18, '2024-12-25', 250.00);


