/* rcp */
create table Rcp(
rcpnum INT PRIMARY KEY,
title varchar2(50),
foodname varchar2(100),
subtitle varchar2(500),
cookingtime varchar2(50),
memnum int,
reg_date date,
thumbnail varchar2(50),
hashtag varchar2(1000),
category varchar2(1000)
);

create table ingredient (
rcpNum INT,
ingredient varchar2(50),
quantity varchar2(50)
);

create table imgFile(
rcpNum int not null,
step int,
fileSize int,
fileName varchar(30),
content varchar(100)
);


create table RcpContent(
rcpNum int not null,
step int,
fileName varchar2(100),
content varchar2(1000)
);

create table category(
cateNum number(2) primary key,
division varchar2(40),
cateName varchar2(40)
);


/* member */

create table brand_member(
  brand varchar(60) not null,
  name varchar(10) not null,   
  tel varchar(12) not null,
  email varchar(60) not null,   
  selfIntroduction varchar(200),
  inquiry varchar(200) ,
  id varchar(50) primary key
);

create table member(
  memNum int UNIQUE,
  email varchar(60) primary key,   
  name varchar(10) not null,   
  passwd varchar(12) not null,
  profile varchar(50),
  selfIntroduction varchar(200)
);

create table follow(
  memNum varchar(50),
  followNum varchar(50)
);

create table scrap(
  memnum varchar(50),
  scrapnum varchar(50)
);

/*shopping*/

create table sale(
rcpNum int not null primary key,
thumbnail varchar2(50) not null,
productname varchar(128) not null,
price number not null,
stock int,
sales int
);

create table cart(
cartnum int not null primary key,
memNum int not null,
productname varchar(128),
qty int,
price number 
);

create table order_info(
ordernum number not null primary key,
memNum int not null,
orderdate varchar(25) not null,
amount number,
memName varchar(10),
Recipient varchar(30),
address varchar(128) not null,
zipcode varchar(20) not null,
memo varchar(100),
paid varchar(15)
);

create table order_product(
productnum int not null primary key,
ordernum number not null,
productname varchar(128),
qty int,
price number 
);

create table mem_address(
addressnum int not null primary key,
addrName varchar(50),
memNum int not null,
memName varchar(10),
address1 varchar(128),
address2 varchar(128),
address3 varchar(128),
zipcode int
);

create table jjim(
jjimnum int not null primary key,
memNum int not null,
productname varchar(128),
price number,
regist_date date
);

insert into RCP values(1,'백종원 두부부침', '두부에 계란입혀 부침', '두부부침','5분',  1, sysdate,'1.png', '두부#계란#식요유#', '백종원#두부부침#간단요리#간단반찬#밥반찬' );
insert into sale values(1,'백종원의 두부조림', 5000, 10,0);
commit

SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;