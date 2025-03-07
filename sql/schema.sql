create table posts (
    post_id varchar(8) not null,
    comments mediumtext,
    picture mediumblob,

    constraint pk_post_id primary key post_id
);

CREATE TABLE acme.cities (
	code varchar(5) NOT NULL,
	city_name varchar(100) NULL,
	CONSTRAINT cities_pk_code PRIMARY KEY (code)
);

insert into cities (code, city_name)
	values ('KL', 'Kuala Lumpur'),
		('SG', 'Singapore'),
		('BJ', 'Beijing'),
		('BK', 'Bangkok'),
		('MN', 'Manila'),
		('LN', 'London'),
		('NZ', 'Auckland');