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
(2, 'minh1234567', '123', 'John Doe', 'john.doe@example.com', '1990-05-15', NULL, '0987654321', '456 John Street', GETDATE(), 0)
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
