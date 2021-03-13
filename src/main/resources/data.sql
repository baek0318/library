INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);

INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목1', '작가1', 1L);
INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목2', '작가2', 1L);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목3', '작가3', 1L, true);
INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목4', '작가4', 2L);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, false);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, true);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, true);
