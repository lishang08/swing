create database swing;

create table user (
username varchar(100),
password varchar(100)
);

-- for test
insert into user (username, password) values ('test','test');

commit;