select *
from faculty;

-- Получаем студентов в определенном возрасном промежутке
select *
from student
where age > 10 and age < 15;

-- Получаем только имена студентов
select student.name
from student;

-- Получаем имена студентов в которых содержится буква о
select name
from student
where char() = 'о';

-- Получаем студентов у которых возраст меньше чем их id
select *
from student
where age < student.id;

-- Получаем всех студентов упорядоченных по возрасту
select *
from student
order by age