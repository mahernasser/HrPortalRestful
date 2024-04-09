CREATE TABLE Employees
(
    employee_id   INT PRIMARY KEY,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50),
    email         VARCHAR(100),
    phone_number  VARCHAR(20),
    hire_date     DATE,
    job_id        VARCHAR(20),
    salary        DECIMAL(10, 2),
    manager_id    INT,
    department_id INT
);

CREATE TABLE Departments
(
    department_id   INT PRIMARY KEY,
    department_name VARCHAR(50),
    manager_id      INT,
    location_id     INT
);

CREATE TABLE Jobs
(
    job_id     VARCHAR(20) PRIMARY KEY,
    job_title  VARCHAR(50),
    min_salary DECIMAL(10, 2),
    max_salary DECIMAL(10, 2)
);

CREATE TABLE Locations
(
    location_id    INT PRIMARY KEY,
    street_address VARCHAR(100),
    postal_code    VARCHAR(20),
    city           VARCHAR(50),
    state_province VARCHAR(50),
    country_id     VARCHAR(2)
);

CREATE TABLE Job_History
(
    employee_id   INT,
    start_date    DATE,
    end_date      DATE,
    job_id        VARCHAR(20),
    department_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employees (employee_id),
    FOREIGN KEY (job_id) REFERENCES Jobs (job_id),
    FOREIGN KEY (department_id) REFERENCES Departments (department_id)
);
