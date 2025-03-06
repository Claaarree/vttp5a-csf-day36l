create table posts (
    post_id varchar(8) not null,
    comments mediumtext,
    picture mediumblob,

    constraint pk_post_id primary key post_id
);