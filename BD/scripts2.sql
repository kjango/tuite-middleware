﻿update tb_login
set user_password = '9135d8523ad3da99d8a4eb83afac13d1'
where login = 'rafael'

select * from tb_login
select * from tb_users
select * from tb_tweet
select * from rl_follow

SELECT DISTINCT tu.id, tu.email, tu.real_name, tu.register_date, tu.protected_tweet, tl.login
 FROM tb_users tu JOIN tb_login tl ON tu.id = tl.id_user
 WHERE tl.login like 'paulo';

SELECT tw.id, tw.text, tw.created_at, tw.my_user FROM tb_tweet tw 
 WHERE tw.my_user = 5
 ORDER BY tw.created_at desc
 
select login from tb_login where id_user = 6

--following
SELECT rf.id_user, rf.id_follow, tbl.login FROM rl_follow rf JOIN tb_login tl ON tl.id_user = rf.id_user
JOIN tb_login tbl ON tbl.id_user = rf.id_follow WHERE tl.id_user = 5
 
--follower
SELECT DISTINCT (login) FROM tb_login
WHERE id_user = (select rf.id_user FROM rl_follow rf
JOIN tb_login tl ON tl.id_user = rf.id_follow
WHERE rf.id_follow = 5)

SELECT * FROM tb_login
where id_user = (select id_user FROM rl_follow where id_user = 5)

SELECT * FROM tb_login
where id_user = (select id_follow FROM rl_follow where id_user = 5)

select * from rl_follow




SELECT rf.id_user, rf.id_follow, tl.login FROM rl_follow rf 
  JOIN tb_login tbl  ON tbl.id_user = rf.id_user
  JOIN tb_login tb   ON tl.id_user = rf.id_follow 
  WHERE tbl.id_follow = 6




SELECT rf.id_user, rf.id_follow, tbl.login 
  FROM rl_follow rf 
  JOIN tb_login tl 
    ON tl.id_user = rf.id_user
  JOIN tb_login tbl 
    ON tbl.id_user = rf.id_follow 
   WHERE tl.id_user = 5

