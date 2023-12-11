-- public.department definition

-- Drop table

-- DROP TABLE public.department;

CREATE TABLE public.department (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(30) NOT NULL,
	CONSTRAINT department_pk PRIMARY KEY (id)
);


-- public.employee definition

-- Drop table

-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	last_name varchar(25) NOT NULL,
	first_name varchar(20) NOT NULL,
	second_name varchar(25) NOT NULL,
	birth_date date NOT NULL,
	phone varchar NOT NULL,
	address varchar(50) NOT NULL,
	personnel_number int4 NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY (id),
	CONSTRAINT employee_un UNIQUE (personnel_number)
);


-- public."position" definition

-- Drop table

-- DROP TABLE public."position";

CREATE TABLE public."position" (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(30) NOT NULL,
	salary int4 NOT NULL,
	CONSTRAINT position_pk PRIMARY KEY (id)
);


-- public.reason definition

-- Drop table

-- DROP TABLE public.reason;

CREATE TABLE public.reason (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(40) NOT NULL,
	CONSTRAINT reason_pk PRIMARY KEY (id)
);


-- public.speciality definition

-- Drop table

-- DROP TABLE public.speciality;

CREATE TABLE public.speciality (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(40) NOT NULL,
	code varchar(12) NOT NULL,
	CONSTRAINT speciality_pk PRIMARY KEY (id)
);


-- public.certification definition

-- Drop table

-- DROP TABLE public.certification;

CREATE TABLE public.certification (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(100) NOT NULL,
	"date" date NOT NULL,
	grade varchar NOT NULL,
	id_employee int4 NOT NULL,
	CONSTRAINT certification_pk PRIMARY KEY (id),
	CONSTRAINT grade_check CHECK ((((grade)::text = 'хорошо'::text) OR ((grade)::text = 'нормально'::text) OR ((grade)::text = 'плохо'::text))),
	CONSTRAINT certification_fk FOREIGN KEY (id_employee) REFERENCES public.employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public."document" definition

-- Drop table

-- DROP TABLE public."document";

CREATE TABLE public."document" (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"number" int4 NOT NULL,
	"name" varchar(40) NOT NULL,
	"type" varchar NOT NULL,
	create_date date NOT NULL,
	start_date date NOT NULL,
	id_employee int4 NOT NULL,
	CONSTRAINT document_pk PRIMARY KEY (id),
	CONSTRAINT document_un UNIQUE (number),
	CONSTRAINT type_check CHECK ((((type)::text = 'найм'::text) OR ((type)::text = 'перевод'::text) OR ((type)::text = 'увольнение'::text))),
	CONSTRAINT document_fk FOREIGN KEY (id_employee) REFERENCES public.employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.education definition

-- Drop table

-- DROP TABLE public.education;

CREATE TABLE public.education (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	"name" varchar(100) NOT NULL,
	finish_date date NOT NULL,
	university varchar(60) NOT NULL,
	id_speciality int4 NOT NULL,
	id_employee int4 NOT NULL,
	CONSTRAINT education_pk PRIMARY KEY (id),
	CONSTRAINT education_fk FOREIGN KEY (id_speciality) REFERENCES public.speciality(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT education_fk_1 FOREIGN KEY (id_employee) REFERENCES public.employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.hiring definition

-- Drop table

-- DROP TABLE public.hiring;

CREATE TABLE public.hiring (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	id_document int4 NOT NULL,
	id_position int4 NOT NULL,
	id_department int4 NOT NULL,
	CONSTRAINT hiring_pk PRIMARY KEY (id),
	CONSTRAINT hiring_fk FOREIGN KEY (id_document) REFERENCES public."document"(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT hiring_fk_1 FOREIGN KEY (id_position) REFERENCES public."position"(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT hiring_fk_2 FOREIGN KEY (id_department) REFERENCES public.department(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.infraction definition

-- Drop table

-- DROP TABLE public.infraction;

CREATE TABLE public.infraction (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	description varchar NOT NULL,
	"date" date NOT NULL,
	punishment varchar NOT NULL,
	id_employee int4 NOT NULL,
	CONSTRAINT infraction_pk PRIMARY KEY (id),
	CONSTRAINT infraction_fk FOREIGN KEY (id_employee) REFERENCES public.employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.speciality_position definition

-- Drop table

-- DROP TABLE public.speciality_position;

CREATE TABLE public.speciality_position (
	id_speciality int8 NOT NULL,
	id_position int8 NOT NULL,
	CONSTRAINT fkjx5liwisnle4rc1ikh4e48di1 FOREIGN KEY (id_position) REFERENCES public."position"(id),
	CONSTRAINT fkonfcferryyko2r5nyxnahaimq FOREIGN KEY (id_speciality) REFERENCES public.speciality(id)
);


-- public.transfer definition

-- Drop table

-- DROP TABLE public.transfer;

CREATE TABLE public.transfer (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	id_document int4 NOT NULL,
	id_reason int4 NOT NULL,
	id_position int4 NOT NULL,
	id_department int4 NOT NULL,
	CONSTRAINT transfer_pk PRIMARY KEY (id),
	CONSTRAINT transfer_fk FOREIGN KEY (id_document) REFERENCES public."document"(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT transfer_fk_1 FOREIGN KEY (id_reason) REFERENCES public.reason(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT transfer_fk_2 FOREIGN KEY (id_position) REFERENCES public."position"(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT transfer_fk_3 FOREIGN KEY (id_department) REFERENCES public.department(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.dismissal definition

-- Drop table

-- DROP TABLE public.dismissal;

CREATE TABLE public.dismissal (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
	description varchar(100) NOT NULL,
	id_document int4 NOT NULL,
	id_reason int4 NOT NULL,
	CONSTRAINT dismissal_pk PRIMARY KEY (id),
	CONSTRAINT dismissal_fk FOREIGN KEY (id_document) REFERENCES public."document"(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT dismissal_fk_1 FOREIGN KEY (id_reason) REFERENCES public.reason(id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO public.employee (id,last_name,first_name,second_name,birth_date,phone,address,personnel_number) VALUES
	 (22,'Батин','Андрей','Викторович','1980-11-30','87653546763','г',1111),
	 (23,'Анохин','Сергей','Александрович','2000-05-12','89765463542','адрес1',1231),
	 (24,'Кузнецова','Анастасия','Николаевна','2023-12-12','89764536453','г. Пенза',1232),
	 (25,'Комарова','Татьяна','Андреевна','2023-11-30','89876543289','ул. Центральная 12-40',1112),
	 (13,'Зубова','Ирина','Алексеевна','1990-01-09','89763748236','Заречный',1241),
	 (12,'Павлов','Никита','Иванович','2000-12-12','89546732819','г. Пенза',1230),
	 (2,'Лебедева','Мария','Федоровна','1970-12-23','89979789471','г. Пенза ул. Красная 40',3452),
	 (1,'Семенов','Валенитин','Иванович','1980-09-12','89885463743','г. Пенза ',1113);

INSERT INTO public."position" (id,"name",salary) VALUES
	 (1,'Менеджер',25000),
	 (2,'Бухгалтер',25000),
	 (3,'Администратор',30000),
	 (5,'Продавец',23000),
	 (4,'Юрист',40000),
	 (6,'Работник отдела кадров',30000),
	 (7,'Маркетолог',35000),
	 (8,'Финансовый аналитик',40000),
	 (9,'Логист',30000),
	 (10,'Логист-аналитик',34000);
INSERT INTO public."position" (id,"name",salary) VALUES
	 (11,'Менеджер по логистике',33000);
INSERT INTO public.reason (id,"name") VALUES
	 (1,'Нарушение ТБ'),
	 (2,'Выход на пенсию'),
	 (3,'Повышение'),
	 (4,'Семейные обстоятельства'),
	 (5,'Медицинское заключение'),
	 (6,'Судимость');
INSERT INTO public.speciality (id,"name",code) VALUES
	 (1,'Бухгалтерия','09.09.09'),
	 (3,'Управление персоналом','38.03.03'),
	 (2,'Менеджмент','38.03.02'),
	 (4,'Юриспруденция','40.03.01'),
	 (5,'Логистика','38.03.02');

	INSERT INTO public.speciality_position (id_speciality,id_position) VALUES
	 (1,2),
	 (2,1),
	 (2,5),
	 (3,6),
	 (3,3),
	 (2,5),
	 (4,4),
	 (2,7),
	 (2,8),
	 (5,9);
INSERT INTO public.speciality_position (id_speciality,id_position) VALUES
	 (5,10),
	 (5,11);

	
	INSERT INTO public.education (id,"name",finish_date,university,id_speciality,id_employee) VALUES
	 (7,'Диплом об окончании','2023-11-02','ПГУ',1,2),
	 (1,'Диплом об окончании ПГУ','2018-06-23','ПГУ',2,1),
	 (9,'Диплом об окончании МГУ','2022-06-23','МГУ',1,1),
	 (10,'Диплом об окончании ','2000-11-30','ПГУ',2,12),
	 (11,'Диплом об окончании','2000-06-12','ПГУ',5,12),
	 (12,'Диплом об окончании','2010-07-20','СПУ',4,13),
	 (13,'Диплом','2019-11-28','СПУ',3,22),
	 (14,'Диплом об окончании универститета','2023-11-30','ПГУ',4,23);
	
INSERT INTO public.certification (id,"name","date",grade,id_employee) VALUES
	 (13,'egar-test','2023-11-03','нормально',1),
	 (16,'test12','2023-11-08','хорошо',2),
	 (17,'test23','2023-11-09','хорошо',2),
	 (19,'Аттестация','2023-11-10','хорошо',13),
	 (3,'Аттестация №12','2020-09-02','хорошо',1),
	 (5,'Аттестация №15','2022-01-09','плохо',2),
	 (7,'Тест 12','2022-12-09','нормально',12),
	 (1,'Тест1','2023-12-12','хорошо',1),
	 (18,'Тест 1234','2023-11-08','плохо',13),
	 (15,'egar-test12','2023-11-03','хорошо',2);
INSERT INTO public.certification (id,"name","date",grade,id_employee) VALUES
	 (14,'egar-test','2023-11-03','плохо',1),
	 (21,'Тест на пригодность','2023-11-27','нормально',12),
	 (23,'Тест 5639','2023-12-10','хорошо',1),
	 (24,'Проверка на пригодность','2023-12-10','нормально',22);
INSERT INTO public.department (id,"name") VALUES
	 (1,'Отдел продаж'),
	 (2,'Отдел обслуживания'),
	 (3,'Юридический отдел'),
	 (4,'Бухгалтерия'),
	 (5,'Технический отдел'),
	 (6,'Отдел логистики');
	
	INSERT INTO public."document" (id,"number","name","type",create_date,start_date,id_employee) VALUES
	 (17,453353,'Увольнение','увольнение','2023-12-20','2023-12-03',2),
	 (18,234524,'Документ найма','найм','2023-11-30','2023-12-01',12),
	 (19,234458,'Документ найма','найм','2023-11-28','2023-11-30',13),
	 (20,765674,'Договор о переводе на новую должность','перевод','2023-12-01','2023-12-02',12),
	 (29,657589,'Документ найма','найм','2023-11-30','2023-12-03',23),
	 (3,447575,'Перевод сотрудника','перевод','2014-12-12','2014-12-14',1),
	 (2,123456,'Документ  найма сотрудника ','найм','2012-12-12','2012-12-12',1),
	 (15,435351,'Докуммент найма','найм','2023-12-02','2023-12-02',2),
	 (16,565465,'Перевод','перевод','2023-12-02','2023-12-03',2),
	 (27,198765,'Документ найма','найм','2023-11-27','2023-11-28',22);

	
	
INSERT INTO public.dismissal (id,description,id_document,id_reason) VALUES
	 (1,'Диагноз',17,5);

INSERT INTO public.hiring (id,id_document,id_position,id_department) VALUES
	 (1,2,2,1),
	 (2,15,2,4),
	 (3,18,9,2),
	 (4,19,4,1),
	 (6,27,6,1),
	 (7,29,4,3);
INSERT INTO public.infraction (id,description,"date",punishment,id_employee) VALUES
	 (2,'Прогул','2023-01-10','Выговор',1),
	 (3,'Опоздание на 10 минут','2023-11-16','Штраф',2),
	 (4,'Опоздание на 10 минут','2023-12-08','Штраф 1000',13),
	 (1,'Опоздание','2012-12-12','Штраф 1000',1),
	 (5,'Опоздание на час','2023-12-08','Предупреждение',12),
	 (6,'Нарушение ТБ','2023-12-05','Выговор',12),
	 (7,'Драка','2023-12-12','Выговор',22),
	 (8,'Кража','2023-12-05','Выговор',1),
	 (9,'Прогул','2023-12-05','Штраф 2000',1),
	 (10,'Опоздание на час','2023-12-10','Предупреждение',12);


INSERT INTO public.transfer (id,id_document,id_reason,id_position,id_department) VALUES
	 (1,3,3,3,1),
	 (2,16,4,2,1),
	 (3,20,3,10,2);

