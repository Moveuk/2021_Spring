create table board2(
    seq number(5) primary key,
    title varchar2(200),
    writer varchar2(20),
    content varchar2(2000),
    regdate date default sysdate,
    cnt number(5) default 0
);

SELECT count(*) FROM board2;

INSERT INTO board2(seq,title,writer,content) VALUES ((SELECT COUNT(*) FROM board2)+1,'a','a','a');

SELECT * FROM board2;

-- max : null일 때는 max값도 null이 됨.
SELECT max(seq) FROM board1;
