--liquibase formatted sql


--changeset azenkova:1
CREATE INDEX student_name_index ON student (name);

--changeset azenkova:2
CREATE INDEX faculty_color_name_index ON faculty (color, name);
