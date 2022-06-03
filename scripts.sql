select *
from faculty;

select *
from student
where age > 10 and age < 15;

select student.name
from student;

select name
from student
where char() = 'о';

select *
from student
where age < student.id;

select *
from student
order by age