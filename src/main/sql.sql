create database researchmanagementsystem;
use researchmanagementsystem;

CREATE TABLE user(
id VARCHAR(20) PRIMARY KEY,
pwd VARCHAR(20),
user_role INT NOT NULL 
);

CREATE TABLE laboratory (
  lab_id INT AUTO_INCREMENT PRIMARY KEY,
  lab_name VARCHAR(255) NOT NULL,
  lab_introduction VARCHAR(255),
  employee_id INT,
  CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES secretary(employee_id)
);

CREATE TABLE office (
office_id INT AUTO_INCREMENT PRIMARY KEY,
lab_id INT,
office_location VARCHAR(255),
office_area DOUBLE,
CONSTRAINT fk_laboratory
FOREIGN KEY (lab_id)
REFERENCES laboratory (lab_id)
ON DELETE CASCADE
);

CREATE TABLE researcher (
employee_id INT PRIMARY KEY,
name VARCHAR(100),
gender CHAR(1), 
title VARCHAR(50),
age INT,
research_area VARCHAR(255),
lab_id INT,
FOREIGN KEY (lab_id) REFERENCES laboratory(lab_id)
);

CREATE TABLE director (
employee_id INT PRIMARY KEY,
start_date DATE,
tenure_years INT,
lab_id INT,
FOREIGN KEY (employee_id) REFERENCES researcher(employee_id),
FOREIGN KEY (lab_id) REFERENCES laboratory(lab_id),
UNIQUE (lab_id)
);

CREATE TABLE secretary (
employee_id INT PRIMARY KEY,
name VARCHAR(100),
gender CHAR(1),
age INT,
hire_date DATE,
duties VARCHAR(255)
);

CREATE TABLE achievement (
  achievement_id INT AUTO_INCREMENT PRIMARY KEY,
  project_id INT,
  achievement_name VARCHAR(255) NOT NULL,
  get_time DATE NOT NULL,
  rank_number INT,
  type ENUM('patent', 'paper', 'software_copyright') NOT NULL,
  FOREIGN KEY (project_id) REFERENCES project(project_id)
);

CREATE TABLE achievement_contributors (
  achievement_id INT,
  employee_id INT,
  PRIMARY KEY(achievement_id, employee_id),
  FOREIGN KEY (achievement_id) REFERENCES achievement(achievement_id),
  FOREIGN KEY (employee_id) REFERENCES researcher(employee_id)
);

CREATE TABLE patents (
  achievement_id INT PRIMARY KEY,
  patent_type ENUM('invention', 'utility_model', 'design') NOT NULL,
  information VARCHAR(255),
  FOREIGN KEY (achievement_id) REFERENCES achievement(achievement_id)
);

CREATE TABLE papers (
  achievement_id INT PRIMARY KEY,
  information VARCHAR(255),
  FOREIGN KEY (achievement_id) REFERENCES achievement(achievement_id)
);

CREATE TABLE software (
  achievement_id INT PRIMARY KEY,
  information VARCHAR(255),
  FOREIGN KEY (achievement_id) REFERENCES achievement(achievement_id)
);

CREATE TABLE project(
  project_id INT PRIMARY KEY,
  project_name VARCHAR(30),
  head VARCHAR(100),
  content VARCHAR(100),
  total_funds DECIMAL(10, 2),
  start_date DATE,
  end_date DATE
);

CREATE TABLE project_researchers(
  participation_id INT AUTO_INCREMENT PRIMARY KEY,
  project_id INT NOT NULL,
  employee_id INT NOT NULL,
  join_date DATE NOT NULL,
  workload DECIMAL(10, 2), -- 假设以小时或其他单位计算
  control_funds DECIMAL(10, 2),
  FOREIGN KEY (project_id) REFERENCES project(project_id),
  FOREIGN KEY (employee_id) REFERENCES researcher(employee_id)
);

CREATE TABLE subtopic(
  subtopic_id INT AUTO_INCREMENT PRIMARY KEY,
  project_id INT NOT NULL,
  head VARCHAR(100),
  end_date DATE,
  total_funds DECIMAL(10, 2),
  technology_condition VARCHAR(255),
  FOREIGN KEY (project_id) REFERENCES project(project_id)
);

CREATE TABLE company (
  company_id INT AUTO_INCREMENT PRIMARY KEY,
  company_name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE contact (
  contact_id INT AUTO_INCREMENT PRIMARY KEY,
  company_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  office_phone VARCHAR(50),
  mobile_phone VARCHAR(50),
  email VARCHAR(100),
  role ENUM('head', 'contact') NOT NULL,
  FOREIGN KEY (company_id) REFERENCES company(company_id)
);

CREATE TABLE project_company (
  project_id INT NOT NULL,
  company_id INT NOT NULL,
  role ENUM('client', 'partner', 'regulator') NOT NULL,
  PRIMARY KEY (project_id, company_id, role),
  FOREIGN KEY (project_id) REFERENCES project(project_id),
  FOREIGN KEY (company_id) REFERENCES company(company_id)
);

