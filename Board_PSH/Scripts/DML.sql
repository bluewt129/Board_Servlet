SELECT ID, PASSWORD, NAME, PHONE, ENABLE, AUTH
	FROM EMPINFO e;

SELECT SEQ, ID, TITLE, CONTENT, DELFLAG, CREATEAT 
	FROM EDUBOARD e;

SELECT *
	FROM EMPINFO e ;

SELECT *
	FROM EDUBOARD e;

--로그인
SELECT ID, NAME, PHONE, AUTH
	FROM EMPINFO e
	WHERE ID = 'USER' AND PASSWORD = 'USER'
	AND ENABLE = 'Y';

SELECT ID, PASSWORD, NAME, PHONE, ENABLE, AUTH
	FROM EMPINFO e;

--회원 가입
INSERT INTO EMPINFO (ID, PASSWORD, NAME, PHONE, ENABLE, AUTH)
	VALUES('A007', 'A007', 'A006', '015', 'D', 'USER');

--회원 가입 승인 대기 목록
SELECT ID, PASSWORD, NAME, PHONE, ENABLE, AUTH
	FROM EMPINFO e
	WHERE ENABLE = 'D';

--회원 가입 승인 조정
UPDATE EMPINFO SET ENABLE = 'Y'
	WHERE ID = 'A999';

--내 정보 조회
SELECT ID, NAME, PHONE, PROFILE_IMG
	FROM EMPINFO e
	WHERE ID = 'A001';

--프로필 사진 업로드
UPDATE EMPINFO SET PROFILE_IMG = 'ABC'
WHERE ID = 'A001';

--회원탈퇴
UPDATE EMPINFO SET ENABLE = 'N'
WHERE ID = 'A147';


--글 전체 조회
SELECT SEQ, ID, TITLE, READCOUNT, GOODCOUNT, TO_CHAR(CREATEAT, 'YYYY-MM-DD') AS CREATEAT 
	FROM EDUBOARD e
	WHERE DELFLAG = 'N'
	ORDER BY SEQ DESC;

--글 전체 조회 (10개씩)
SELECT SEQ, ID, TITLE, CREATEAT, GOODCOUNT
FROM (
    SELECT SEQ, ID, TITLE, GOODCOUNT, TO_CHAR(CREATEAT, 'YYYY-MM-DD') AS CREATEAT,
           ROW_NUMBER() OVER(ORDER BY GOODCOUNT DESC) AS RN
    FROM EDUBOARD
    WHERE DELFLAG = 'N')
WHERE RN BETWEEN 1 AND 10;

--글 조회수 증가
UPDATE EDUBOARD SET READCOUNT = (SELECT MAX(READCOUNT)+1 FROM EDUBOARD e WHERE SEQ = 127) WHERE SEQ = 127;

--글 상세 조회
SELECT ID, TITLE, CONTENT, TO_CHAR(CREATEAT, 'YYYY-MM-DD') AS CREATEAT 
	FROM EDUBOARD e
	WHERE DELFLAG = 'N'
	AND SEQ = '2';

--글 좋아요 증가
UPDATE EDUBOARD SET GOODCOUNT = (SELECT MAX(GOODCOUNT)+1 FROM EDUBOARD e WHERE SEQ = 122) WHERE SEQ = 122;

--글 작성
INSERT INTO EDUBOARD e (SEQ, ID, TITLE, CONTENT, DELFLAG, CREATEAT)
	VALUES((SELECT MAX(SEQ)+1 AS SEQ FROM EDUBOARD e), 'USER', '제목', '내용', 'N', SYSDATE);

CREATE SEQUENCE EDUBOARD_SEQ
	START WITH 1
	INCREMENT BY 1;

--글 수정
UPDATE EDUBOARD SET CONTENT = '내용수정'
	WHERE SEQ = '2';

--글 삭제
UPDATE EDUBOARD SET DELFLAG ='Y'
 	WHERE SEQ = '1';

ALTER TABLE EDUINFO RENAME TO EMPINFO;

--SEQ값 증가
SELECT MAX(SEQ)+1 AS SEQ
FROM EDUBOARD;

--내가 작성한 게시글 조회
SELECT SEQ, ID, TITLE, CREATEAT
FROM (
SELECT SEQ, ID, TITLE, TO_CHAR(CREATEAT, 'YYYY-MM-DD') AS CREATEAT,
ROW_NUMBER() OVER(ORDER BY SEQ DESC) AS RN
FROM EDUBOARD
WHERE DELFLAG = 'N'
AND ID = 'USER'
)
WHERE RN BETWEEN 1 AND 10;

--게시글 별 댓글 갯수 조회
SELECT SEQ, ID, TITLE, CREATEAT, GOODCOUNT, REPLY_COUNT
FROM (
    SELECT B.SEQ, B.ID, B.TITLE, B.GOODCOUNT, TO_CHAR(B.CREATEAT, 'YYYY-MM-DD') AS CREATEAT,
           ROW_NUMBER() OVER(ORDER BY B.GOODCOUNT DESC) AS RN,
           COUNT(R.POST_ID) AS REPLY_COUNT
    FROM EDUBOARD B
    LEFT JOIN REPLY R ON B.SEQ = R.POST_ID
    WHERE B.DELFLAG = 'N'
    GROUP BY B.SEQ, B.ID, B.TITLE, B.GOODCOUNT, B.CREATEAT
)
WHERE RN BETWEEN 1 AND 10;

--같은 DEPT의 순서(STEP) 조정
UPDATE REPLY
SET STEP = STEP + 1
WHERE POST_ID = 129
AND DEPT = 0;

--대댓글 작성시 DEPT 조정
UPDATE REPLY
SET DEPT = DEPT + 1
WHERE 

--새로운 댓글 작성 (DEPT 0)
INSERT INTO REPLY (SEQ, USER_ID, POST_ID, RCONTENT, STEP, REGDATE)
VALUES((SELECT MAX(SEQ)+1 FROM REPLY), 'ADMIN', 128, '새로운 댓글', (SELECT MAX(STEP)+1 FROM REPLY WHERE POST_ID = 128), SYSDATE);

--대댓글 작성
INSERT INTO REPLY (SEQ, USER_ID, POST_ID, RCONTENT, STEP, DEPT, REGDATE)
VALUES((SELECT MAX(SEQ)+1 FROM REPLY), 'ADMIN', 128, '대대댓글1', 1, 2, SYSDATE);

--댓글 테이블 조회
SELECT 
    SEQ, USER_ID, POST_ID, 
    LPAD(' ', DEPT * 3) || RCONTENT AS RCONTENT,
    STEP, DEPT, TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE, DELFLAG, MODIFYDATE, GOODCOUNT, PARENT_RSEQ
FROM REPLY
WHERE POST_ID = 128
ORDER BY STEP ASC, DEPT ASC;

-- IF:같은 DEPT라면 STEP+1/대댓글을 달기 위해서는 부모의 DEPT+1/댓글 달때 부모의 SEQ 가져와야한다
--부모의 SEQ
SELECT SEQ
FROM REPLY
WHERE POST_ID = 128;

--댓글 좋아요 update
UPDATE REPLY  SET GOODCOUNT = GOODCOUNT + 1
WHERE SEQ = 2;

--베스트 댓글 조회
SELECT USER_ID, RCONTENT, GOODCOUNT, REGDATE
	FROM REPLY
	WHERE DELFLAG = 'N'
	AND GOODCOUNT > 0
	ORDER BY GOODCOUNT DESC;

--댓글 삭제
UPDATE REPLY SET DELFLAG = 'Y'
WHERE SEQ = 7;


