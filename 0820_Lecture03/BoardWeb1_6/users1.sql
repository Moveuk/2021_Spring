
CREATE TABLE users1(
    id VARCHAR2(8) PRIMARY KEY,
    password VARCHAR2(8),
    name VARCHAR2(20),
    role VARCHAR2(10)
 );
 
 INSERT INTO users1 VALUES('admin','1234','주인장','관리자');
 
 SELECT * FROM users1 WHERE id = 'admin' and password = '1234';