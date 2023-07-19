SELECT * FROM instructor;
SELECT first_name, last_name FROM instructor;

SELECT first_name, advisor_id FROM student;

SELECT first_name, advisor_id FROM student WHERE advisor_id IS NULL;