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
    created_at DATETIME DEFAULT GETDATE(),
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
	[status] NVARCHAR(10) NOT NULL,
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

INSERT INTO Roles (name)
VALUES ('Admin'), ('User'), ('Expert');


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
