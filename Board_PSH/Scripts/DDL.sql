-- Board 게시판

CREATE TABLE EDUBOARD(
	SEQ NUMBER,
	ID VARCHAR2(20) NOT NULL,
	TITLE VARCHAR2(100) NOT NULL,
	CONTENT VARCHAR2(500) NOT NULL,
	DELFLAG CHAR(1) NOT NULL,
	CREATEAT DATE
);

CREATE TABLE EMPINFO AS SELECT ID, PASSWORD, NAME, TEL, ENABLE, AUTH
	FROM MEMBERINFO m;

ALTER TABLE EDUBOARD ADD CONSTRAINT EDUBOARD_PK PRIMARY KEY (SEQ);

ALTER TABLE EDUBOARD ADD CONSTRAINT EDUBOARD_FK FOREIGN KEY (ID) REFERENCES EMPINFO (ID);


SELECT * FROM EMPINFO e;

SELECT * FROM EDUBOARD e;

--아래의 순서로 컬럼 추가할 것
--1. 이미지 파일 컬럼 추가
ALTER TABLE EMPINFO ADD PROFILE_IMG VARCHAR2(1000);
-
--2. 좋아요 컬럼 추가()
ALTER TABLE EDUBOARD ADD GOODCOUNT NUMBER;

--3.좋아요가 NULL일때 0으로 모두 변경()
UPDATE EDUBOARD SET GOODCOUNT = 0 WHERE GOODCOUNT IS NULL;

--4.좋아요 컬럼에 DEFAULT 값을 0으로 변경하고 NOT NULL 설정
ALTER TABLE EDUBOARD MODIFY GOODCOUNT NUMBER DEFAULT 0 NOT NULL;

--5. 조회수 컬럼 추가 DEFAULT 값이 1이고 NOT NULL
ALTER TABLE EDUBOARD ADD READCOUNT NUMBER DEFAULT 1 NOT NULL;

--6. 댓글테이블 생성 (REPLY TABLE)
-- seq, user_id, comment, post_id, step, dept, regdate, delflag, modifydate, goodcount, parent_comment_id

CREATE TABLE REPLY (
    SEQ NUMBER,
    USER_ID VARCHAR2(20) NOT NULL,
    POST_ID NUMBER NOT NULL,
    RCONTENT VARCHAR2(1000) NOT NULL,
    STEP NUMBER DEFAULT 1,
    DEPT NUMBER DEFAULT 0,
    REGDATE DATE NOT NULL,
    DELFLAG CHAR(1) NOT NULL,
    MODIFYDATE DATE,
    GOODCOUNT NUMBER DEFAULT 0, 
    PARENT_RSEQ NUMBER DEFAULT NULL,
    CONSTRAINT REPLY_PK PRIMARY KEY (SEQ),
    CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES EMPINFO (ID),
    CONSTRAINT POST_ID_FK FOREIGN KEY (POST_ID) REFERENCES EDUBOARD (SEQ)
);

--7. DELFLAG 컬럼에 DEFAULT 값 N 추가
ALTER TABLE REPLY MODIFY DELFLAG DEFAULT 'N';


	
