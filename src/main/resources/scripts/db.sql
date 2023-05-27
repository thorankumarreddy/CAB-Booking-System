create schema if not exists CAB_BOOKING collate utf8mb4_0900_ai_ci;

create table if not exists USER_LOGIN
(
	USERNAME varchar(255) null,
	EMAIL varchar(255) null,
	CONTACT varchar(255) null,
	PASSWORD varchar(255) null
);

create table if not exists location
(
	id int auto_increment
		primary key,
	latitude decimal(9,6) not null,
	longitude decimal(9,6) not null,
	trip_id varchar(255) not null,
	created_at timestamp default CURRENT_TIMESTAMP null
);



