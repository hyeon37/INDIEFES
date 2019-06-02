drop table play_list;

drop table good_log;

drop table like_log;

drop table strm_pay_log;

drop table pay_log;

drop table bank_cate;

drop table bank_account;

drop table team_member;

drop table indie_team;

drop table music_info;

drop table art_info;

drop table user_info;

drop table account_grade;


drop sequence seq_art;

drop sequence seq_music;

drop sequence seq_team;

drop sequence seq_member;

drop sequence seq_bank;



create table account_grade(
user_level	number	primary key,
level_info	varchar2(20)	not null
);


create table user_info(
    user_id	varchar2(50)	primary key,
    user_pw	varchar2(50)	not null,
    user_nick	varchar2(20)	not null,
    user_name	varchar2(20)	not null,
    user_email	varchar2(50)	not null,
    user_birth	date	not null,
    user_gender	varchar2(5)	check (user_gender in('F','M','E')),
    reg_date	date	default sysdate,
    point	number	default 0,
    user_level	number	default 4 references account_grade(user_level),
    auto_login	varchar2(5)	default 'X' check (auto_login in('X','O')),
    reg_indie	varchar2(5)	default 'X' check (reg_indie in('X','O')),
    deleted	varchar2(5)	default 'X' check (deleted in('X','O'))
);

create table indie_team(
    team_number	number	primary key,
    art_team	varchar2(20)	not null
);

create table team_member(
    member_number	number	primary key,
    team_number	number	references indie_team(team_number),
    user_id	varchar2(50)	references user_info(user_id)
);

create table art_info(
    art_number	number	primary key,
    art_genre	varchar2(10),
    user_id	varchar2(50)	references user_info(user_id),
    team_number	number	references indie_team(team_number),
    art_title	varchar2(100)	not null,
    art_cover	varchar2(100),
    art_pr	varchar2(1000),
    reg_art	date	default sysdate,
    liked_count	number	default 0,
    good_count	number	default 0,
    deleted	varchar2(5)	default 'X' check (deleted in('X','O'))
);

create table music_info(
    art_number	number	references art_info(art_number),
    music_number	number	primary key,
    track_number	number	default 0 not null,
    music_title	varchar2(100)	not null,
    heard_count	number	default 0,
    price	number	default 100,
    pay_count	number	default 0,
    deleted	varchar2(5)	default 'X' check (deleted in('X','O')),
    file_path	varchar2(100)	not null
);

create table bank_cate(
    bank_index	number	primary key,
    bank_name	varchar2(10)	not null
);



create table bank_account(
    bank_number	number	primary key,
    team_number	number	references indie_team(team_number),
    bank_cate	number	references bank_cate(bank_index),
    account_num	varchar2(50)	not null,
    account_name	varchar2(20)	not null
);

create table pay_log(
    user_id	varchar2(50)	references user_info(user_id),
    music_number	number	references music_info(music_number),
    price	number,
    pay_date	date	default sysdate,
    exp_date	date	
);

create table strm_pay_log(
    user_id	varchar2(50)	references user_info(user_id),
    price	number,
    pay_date	date	default sysdate,
    exp_date	date	
);

create table like_log(
    user_id	varchar2(50)	references user_info(user_id),
    art_number	number	references art_info(art_number),
    like_date	date	default sysdate
);

create table good_log(
    user_id	varchar2(50)	references user_info(user_id),
    art_number	number	references art_info(art_number),
    good_date	date	default sysdate
);

create table play_list(
    user_id	varchar2(50)	references user_info(user_id),
    music_number	number	references music_info(music_number),
    play_index	number	default 0
);


create sequence seq_art;

create sequence seq_music;

create sequence seq_team;

create sequence seq_member;

create sequence seq_bank;



insert into account_grade values (0, 'admin');
insert into account_grade values (1, 'staff');
insert into account_grade values (2, 'indie');
insert into account_grade values (3, 'common');

insert into bank_cate values (10, '국민');
insert into bank_cate values (20, '기업');
insert into bank_cate values (30, '농협');
insert into bank_cate values (40, '신한');
insert into bank_cate values (50, '하나');