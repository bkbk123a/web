-- 출석(기획 데이터)
insert into t_static_attend(attend_index, attend_type, end_time, start_time, description) values (1, 1, '2023-12-01 15:38:20', '2024-12-31 15:38:20', '1일 출석');

-- 상품(기획 데이터)
insert into t_static_product (price, product_type, quantity, product_name) values (100, 2, 999, '청바지1');
insert into t_static_product (price, product_type, quantity, product_name) values (200, 2, 999, '청바지2');
insert into t_static_product (price, product_type, quantity, product_name) values (300, 2, 999, '청바지3');
insert into t_static_product (price, product_type, quantity, product_name) values (400, 2, 999, '청바지4');
insert into t_static_product (price, product_type, quantity, product_name) values (100, 1, 999, '상의1');
insert into t_static_product (price, product_type, quantity, product_name) values (200, 1, 999, '상의2');
insert into t_static_product (price, product_type, quantity, product_name) values (300, 1, 999, '상의3');
insert into t_static_product (price, product_type, quantity, product_name) values (400, 1, 999, '상의4');
insert into t_static_product (price, product_type, quantity, product_name) values (100, 1, 999, '신발1');
insert into t_static_product (price, product_type, quantity, product_name) values (200, 1, 999, '신발2');
insert into t_static_product (price, product_type, quantity, product_name) values (300, 1, 999, '신발3');
insert into t_static_product (price, product_type, quantity, product_name) values (400, 1, 999, '신발4');

-- 유저
INSERT INTO mysql_web.t_user_info (created_at, last_login_at, money, user_index, email_address, nick_name, password, user_name) VALUES ('2023-12-12 15:31:46.000000', '2023-12-12 15:31:48.000000', 10000, 999, 'beomkyung@naver.com', 'bk', '{noop}qjarud', 'beomkyung');
INSERT INTO mysql_web.t_user_info (created_at, last_login_at, money, user_index, email_address, nick_name, password, user_name) VALUES ('2023-12-12 15:31:46.000000', '2023-12-12 15:31:48.000000', 10000, 1000, 'chulsoo@naver.com', 'ch', '{noop}chulsoo', 'chulsoo');

-- 게시글
INSERT INTO mysql_web.t_user_article (article_index, create_user_index, created_at, modified_at, modify_user_index, user_index, content, hash_tag, title) VALUES (1, 999, '2023-06-24 04:57:34.000000', '2023-06-25 11:45:34.000000', 999, 999, '2-915 - mulch', '#pink', 'hunnisett');

-- 게시글 댓글
INSERT INTO mysql_web.t_user_article_comment (article_index, comment_index, create_user_index, created_at, modified_at, modify_user_index, user_index, content) VALUES (1, 1, 999, '2023-12-29 11:48:45.000000', '2023-12-30 11:48:45.000000', 999, 999, '8-700 - hardware');
