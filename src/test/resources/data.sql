insert into authority(role) values('USER');
insert into authority(role) values('ADMIN');

insert into user(email, password, authority_id) values('peach18@kakao.com','1234',1);

insert into author(name) values('baek');
insert into author(name) values('baeked');

insert into book_info(title, author_id) values('peachberr', 1);

insert into book(status, book_info_id) values(TRUE, 1);