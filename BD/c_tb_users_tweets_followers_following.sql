
CREATE SEQUENCE seq_users START 1;

CREATE TABLE tb_users (
    id	        	integer CONSTRAINT id_user PRIMARY KEY DEFAULT nextval('seq_users'),
    email       	varchar(40) NOT NULL,
    real_name		varchar(40) NOT NULL,
    register_date   	date,
    photo		bytea,
    protected_tweet	boolean
);

INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet)
values (nextval('seq_users'), 'pacefico@gmail.com', 'Paulo Figueiredo', '2012-10-29', false);

INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet)
values (nextval('seq_users'), 'kjango@gmail.com', 'Francisco', '2012-10-29', false);

INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet)
values (nextval('seq_users'), 'rafacpiva@gmail.com', 'Rafael', '2012-10-29', false);

CREATE TABLE tb_login (
    id_user	integer references tb_users(id),
    login	varchar(40) NOT NULL,
    user_password	character(100) NOT NULL
);

insert into tb_login (id_user, login, user_password) values (1, 'paulo', 'paulo');
insert into tb_login (id_user, login, user_password) values (2, 'francisco', 'francisco');
insert into tb_login (id_user, login, user_password) values (3, 'rafael', 'rafael');

CREATE SEQUENCE seq_tweets START 1;

CREATE TABLE tb_tweet (
    id	        	integer CONSTRAINT id_tweet PRIMARY KEY DEFAULT nextval ('seq_tweets'),
    text	       	varchar(140) NOT NULL,
    created_at   	date,
    my_user		integer references tb_users(id),
    protected_tweet 	boolean
);

Insert into tb_tweet (id, text, created_at, my_user)
values (nextval('seq_tweets'), 'Tweet from Paulo', '2012-10-29', 1);

Insert into tb_tweet (id, text, created_at, my_user)
values (nextval('seq_tweets'), 'Tweet from Francisco', '2012-10-29', 2);

Insert into tb_tweet (id, text, created_at, my_user)
values (nextval('seq_tweets'), 'Tweet from Rafael', '2012-10-29', 3);

CREATE TABLE rl_follow(
    id_user	integer references tb_users(id),
    id_follow	integer references tb_users(id)
);

select * from tb_users

insert into rl_follow (id_user, id_follow) values (1, 2);
insert into rl_follow (id_user, id_follow) values (2, 3);


