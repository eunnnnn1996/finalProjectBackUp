create table onclass(
	on_num number not null,
	user_num number not null,
	on_name varchar2(12) not null,
	on_price number(8) not null,
	hit number default 0 not null,
	category_num number not null,
	uploadfile blob,
	filename varchar2(100) not null,
	on_content clob not null,
	reg_date date default sysdate not null,
	modify_date date,
	constraint onclass_pk primary key (on_num),
	constraint onclass_fk foreign key (user_num) 
                         references ouser (user_num)
);
create table ostar(
	ostar_num number not null,
	user_num number not null,
	on_num number not null,
	text varchar2(100) not null,
	rating number default 0 not null,
	constraint ostar_pk primary key (ostar_num),
	constraint ostar_fk1 foreign key (user_num) 
                         references ouser (user_num),
	constraint ostar_fk2 foreign key (on_num)
	 					 references onclass (on_num)
);
create sequence ostar_seq;

create table onreg(
    onreg_num number not null,
    user_num number not null,
    on_regdate date default sysdate not null,
    on_moregdate date,
    constraint onreg_pk primary key (onreg_num),
	constraint onreg_fk1 foreign key (user_num) references ouser (user_num)
);
create sequence onreg_seq;

create table onreg_detail(
    ondetail_num number not null,
    onreg_num number not null,
    on_num number not null,
    on_payment number not null,
    on_status number(1) default 1 not null, -- 수강신청상태(1), 수강취소상태(2)
    constraint onreg_detail_pk primary key (ondetail_num),
	constraint onreg_detail_fk1 foreign key (onreg_num) references onreg (onreg_num),
    constraint onreg_detail_fk2 foreign key (on_num) references onclass (on_num)
);
create sequence onreg_detail_seq;
--찜
create table onlike (
	onlike_num number not null,
	user_num number not null,
	on_num number not null,
	olike number(1) not null,
	constraint onlike_pk primary key (onlike_num),
	constraint onlike_fk1 foreign key (user_num) references ouser(user_num),
	constraint onlike_fk2 foreign key (on_num) references onclass(on_num)
);
create sequence onlike_seq;
 -- 평점답변
create table ostar_reply(
    ore_num number not null,
    ostar_num number not null,
    user_num number not null,
    ore_content varchar2(900) not null,
    ore_date date default sysdate not null,
    constraint ostar_reply_pk primary key(ore_num),
    constraint ostar_reply_fk1 foreign key(user_num) references ouser(user_num),
    constraint ostar_reply_fk2 foreign key(ostar_num) references ostar(ostar_num)
);
create sequence ostar_reply_seq;