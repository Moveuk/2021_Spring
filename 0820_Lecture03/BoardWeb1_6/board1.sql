create table board1(
    seq number(5) primary key,
    title varchar2(200),
    writer varchar2(20),
    content varchar2(2000),
    regdate date default sysdate,
    cnt number(5) default 0
);

SELECT count(*) FROM board1;

INSERT INTO board1(seq,title,writer,content) VALUES ((SELECT COUNT(*) FROM board1)+1,'a','a','a');

SELECT * FROM board1;

-- max : null일 때는 max값도 null이 됨.
SELECT max(seq) FROM board1;
