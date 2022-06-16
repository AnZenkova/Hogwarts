-- добавляем ограничения для таблиц в базе данных
ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age >= 16 );

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (name, color);

ALTER TABLE student
    ADD COLUMN age_student INTEGER DEFAULT 20;