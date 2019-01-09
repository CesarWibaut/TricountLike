DROP TABLE USER;
DROP TABLE NOT_USER;
DROP TABLE EVENT;
DROP TABLE SPENT;

CREATE TABLE USER (uno SERIAL,
                   login text not null,
                   password text not null,
                   lastname text,
                   firstname text,
                   CONSTRAINT pk_user PRIMARY KEY(uno));

CREATE TABLE NOT_USER (name text not null);

CREATE TABLE EVENT (eno SERIAL,
                    title text not null,
                    desc text not null,
                    CONSTRAINT pk_event PRIMARY KEY(eno));

CREATE TABLE SPENT (sno SERIAL,
                    createdAt date not null,
                    desc text not null,
                    CONSTRAINT pk_spent PRIMARY KEY(sno));

