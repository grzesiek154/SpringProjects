DROP DATABASE IF EXISTS Trainings_notebooks_v2;
CREATE DATABASE Trainings_notebooks_v2;

CREATE TABLE Workouts (
 workout_id INT Primary Key NOT NULL AUTO_INCREMENT,
 name VARCHAR(30) NOT NULL,
 description VARCHAR(300)
 );

CREATE TABLE Exercises (
 exercise_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 name VARCHAR(30) NOT NULL,
 description VARCHAR(300),
 type VARCHAR(30),
 workout_id INT NOT NULL,
 reps INT,
 duration INT
 );

 CREATE TABLE Trainings_Exercises (
 exercise_id INT NOT NULL,
 training_id INT NOT NULL
 );

 CREATE TABLE Trainings (
 training_id INT Primary Key NOT NULL AUTO_INCREMENT,
 name VARCHAR(30) NOT NULL,
 type VARCHAR(30),
 date Date
 );