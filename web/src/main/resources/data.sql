-- INSERT INTO PUBLIC.T_STATIC_ATTEND (ATTEND_INDEX, ATTEND_TYPE, END_TIME, START_TIME, DESCRIPTION) VALUES (1, 1, '2023-12-22 15:38:20.725869 +09:00', '2023-12-02 15:38:20.725869 +09:00', '1일 출석');

INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (100, 2, 999, '청바지1');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (200, 2, 999, '청바지2');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (300, 2, 999, '청바지3');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (400, 2, 999, '청바지4');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (100, 1, 999, '상의1');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (200, 1, 999, '상의2');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (300, 1, 999, '상의3');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (400, 1, 999, '상의4');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (100, 1, 999, '신발1');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (200, 1, 999, '신발2');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (300, 1, 999, '신발3');
INSERT INTO PUBLIC.T_STATIC_PRODUCT (PRICE, PRODUCT_TYPE, QUANTITY, PRODUCT_NAME) VALUES (400, 1, 999, '신발4');


INSERT INTO PUBLIC.T_USER_INFO (USER_INDEX, CREATED_AT, LAST_LOGIN_AT, MONEY, EMAIL_ADDRESS, NICK_NAME) VALUES (999, '2023-12-12 15:31:46.000000', '2023-12-12 15:31:48.000000', 10000, 'beomkyung@naver.com', 'beomkyung');

INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-03-10 08:48:50.000000 +00:00', '2021-03-10 08:48:50.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.

Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.

Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '#pink', 'Quisque ut erat.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-05-23 08:34:54.000000 +00:00', '2021-05-23 08:34:54.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Phasellus in felis. Donec semper sapien a libero. Nam dui.

Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.

Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', '#purple', 'Morbi ut odio.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-04-02 02:58:19.000000 +00:00', '2021-04-02 02:58:19.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', '#purple', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-10-31 17:46:08.000000 +00:00', '2021-10-31 17:46:08.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', '#mauv', 'Fusce posuere felis sed lacus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-11-08 08:47:03.000000 +00:00', '2021-11-08 08:47:03.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', '#green', 'Aliquam erat volutpat.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-12-08 11:27:30.000000 +00:00', '2021-12-08 11:27:30.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.

Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', '#maroon', 'Donec ut mauris eget massa tempor convallis.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-10-09 16:52:09.000000 +00:00', '2021-10-09 16:52:09.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.

Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', '#orange', 'Nullam molestie nibh in lectus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-08-05 14:19:36.000000 +00:00', '2021-08-05 14:19:36.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.

Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', '#teal', 'Sed ante.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-04-15 05:02:39.000000 +00:00', '2021-04-15 05:02:39.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Sed ante. Vivamus tortor. Duis mattis egestas metus.

Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '#khaki', 'In hac habitasse platea dictumst.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-09-01 13:54:55.000000 +00:00', '2021-09-01 13:54:55.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.

Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.

Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', '#puce', 'Vivamus in felis eu sapien cursus vestibulum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-07-18 05:30:34.000000 +00:00', '2021-07-18 05:30:34.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '#orange', 'Morbi a ipsum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-05-16 14:20:27.000000 +00:00', '2021-05-16 14:20:27.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Sed ante. Vivamus tortor. Duis mattis egestas metus.

Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '#purple', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-10-10 06:05:30.000000 +00:00', '2021-10-10 06:05:30.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '#maroon', 'Duis at velit eu est congue elementum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-10-01 13:01:41.000000 +00:00', '2021-10-01 13:01:41.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.

Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', '#pink', 'In hac habitasse platea dictumst.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-07-12 00:20:12.000000 +00:00', '2021-07-12 00:20:12.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.

Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.

Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '#purple', 'Nulla suscipit ligula in lacus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-09-05 17:15:51.000000 +00:00', '2021-09-05 17:15:51.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.

Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', '#purple', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-05-07 08:06:52.000000 +00:00', '2021-05-07 08:06:52.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', '#mauv', 'Donec semper sapien a libero.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-05-11 00:47:43.000000 +00:00', '2021-05-11 00:47:43.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.

Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', '#green', 'Quisque id justo sit amet sapien dignissim vestibulum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-07-05 18:44:15.000000 +00:00', '2021-07-05 18:44:15.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', '#maroon', 'Morbi quis tortor id nulla ultrices aliquet.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-01-26 02:34:46.000000 +00:00', '2021-01-26 02:34:46.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.

Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '#orange', 'In sagittis dui vel nisl.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2022-01-31 12:02:00.000000 +00:00', '2022-01-31 12:02:00.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.

Phasellus in felis. Donec semper sapien a libero. Nam dui.', '#teal', 'Integer non velit.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-06-28 19:55:49.000000 +00:00', '2021-06-28 19:55:49.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.

Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '#khaki', 'Quisque id justo sit amet sapien dignissim vestibulum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-11-30 13:45:21.000000 +00:00', '2021-11-30 13:45:21.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.

Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.

Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.', '#puce', 'Nullam varius.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-06-20 13:09:41.000000 +00:00', '2021-06-20 13:09:41.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.

Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.

Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', '#orange', 'Sed ante.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-01-26 00:43:20.000000 +00:00', '2021-01-26 00:43:20.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.

In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.

Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', '#purple', 'Morbi non lectus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-04-26 23:42:00.000000 +00:00', '2021-04-26 23:42:00.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '#maroon', 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-01-30 05:24:22.000000 +00:00', '2021-01-30 05:24:22.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '#pink', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-06-24 17:50:44.000000 +00:00', '2021-06-24 17:50:44.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Sed ante. Vivamus tortor. Duis mattis egestas metus.

Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '#purple', 'Nulla justo.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-11-18 17:42:45.000000 +00:00', '2021-11-18 17:42:45.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '#purple', 'Pellentesque viverra pede ac diam.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-01-15 04:11:23.000000 +00:00', '2021-01-15 04:11:23.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.

Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', '#mauv', 'Morbi a ipsum.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2021-06-21 07:33:19.000000 +00:00', '2021-06-21 07:33:19.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.

Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.

Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '#green', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO PUBLIC.T_USER_ARTICLE (CREATED_AT, MODIFIED_AT, USER_INDEX, CREATED_BY, MODIFIED_BY, CONTENT, HASH_TAG, TITLE) VALUES ('2022-01-24 21:15:02.000000 +00:00', '2022-01-24 21:15:02.000000 +00:00', 999, 'beomkyung', 'beomkyung', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.

Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', '#maroon', 'Duis at velit eu est congue elementum.');
