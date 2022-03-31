create table okit(
	kit_num number not null,
	user_num number not null,
    category_num number not null,
	kit_name varchar2(30) not null,
	kit_price number not null,
	kit_quantity number not null,
	kit_content clob not null,
    hit number(5) default 0 not null,
    filename varchar2(100),
	uploadfile blob,
	reg_date date default sysdate not null,
	modify_date date,
	constraint okit_pk primary key (kit_num),
	constraint okit_fk foreign key (user_num) references ouser (user_num),
   
);
/* constraint okit_fk2 foreign key (category_num) references category (category_num)*/
create sequence okit_seq;

/*(찜한 상품 테이블)*/
CREATE TABLE okitlike(
	kitlike_num number not null,
	kit_num number not null,
	user_num number not null,
	constraint kitlike_pk primary key(kitlike_num),
	constraint kit_fk1 foreign key(kit_num) references okit(kit_num),
	constraint user_fk2 foreign key(user_num) references ouser(user_num)
);
create sequence okitlike_seq;


