SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN faculty ON student.faculty_name = faculty.id;

SELECT student.name, student.age, avatar.media_type
FROM student
         RIGHT JOIN avatar ON student.id = avatar.student_id;