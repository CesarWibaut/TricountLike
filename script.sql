DROP TABLE SPENT_FOR;
DROP TABLE PARTICIPATE;
DROP TABLE SPENT;
DROP TABLE USERS;
DROP TABLE NOT_USER;
DROP TABLE EVENT;

CREATE TABLE USERS (uno SERIAL,
                   login text not null,
                   pwd text not null,
                   lastname text,
                   firstname text,
                   CONSTRAINT pk_user PRIMARY KEY(uno));

CREATE TABLE NOT_USER (name text not null);

CREATE TABLE EVENT (eno SERIAL,
                    title text not null,
                    descr text not null,
                    CONSTRAINT pk_event PRIMARY KEY(eno));

CREATE TABLE SPENT (sno SERIAL,
					uno INTEGER,
                    createdAt date not null,
                    descr text not null,
                    CONSTRAINT pk_spent PRIMARY KEY(sno),
					CONSTRAINT fk_user3 FOREIGN KEY(uno) REFERENCES USERS(uno));

CREATE TABLE PARTICIPATE (uno INTEGER,
						  eno INTEGER,
						 CONSTRAINT fk_user FOREIGN KEY (uno) REFERENCES USERS(uno),
						 CONSTRAINT fk_event FOREIGN KEY (eno) REFERENCES EVENT(eno));

CREATE TABLE SPENT_FOR (uno INTEGER,
						sno INTEGER,
						CONSTRAINT fk_user2 FOREIGN KEY (uno) REFERENCES USERS(uno),
					    CONSTRAINT fk_spent FOREIGN KEY (sno) REFERENCES SPENT(sno));

