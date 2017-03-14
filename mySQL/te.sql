一.creat table
CREATE TABLE book(
id INT UNSIGNED NOT NULl AUTO_INCREMENT COMMENT '自增id',
book_id VARCHAR(20) UNIQUE NOT NULL COMMENT '图书id',
author_id VARCHAR(50) NOT NULL COMMENT '图书作者id',
book_name VARCHAR(50) NOT NULL COMMENT '图书名',
pages SMALLINT UNSIGNED NOT NULL COMMENT '图书页数',
press VARCHAR(100) NOT NULL COMMENT '出版社',
PRIMARY KEY (id),
UNIQUE KEY book_id(book_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表' ;


CREATE TABLE prize(
id INT UNSIGNED NOT NULl AUTO_INCREMENT COMMENT '自增id',
book_id VARCHAR(20) UNIQUE NOT NULL COMMENT '图书id',
author_id VARCHAR(50) NOT NULL COMMENT '图书作者id',
cup_type VARCHAR(20) NOT NULL COMMENT '获奖类型',
cup_time DATE NOT NULL COMMENT '获奖时间',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='获奖表' ;

CREATE TABLE author(
id INT UNSIGNED NOT NULl AUTO_INCREMENT COMMENT '自增id',
author_id VARCHAR(50) UNIQUE NOT NULL COMMENT '图书作者id',
author_name VARCHAR(100) NOT NULL COMMENT '图书作者姓名',
content TEXT NOT NULL COMMENT '作者简介',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='作者表';

二.creat index

book表索引
ALTER TABLE book ADD  UNIQUE INDEX idx_book_id_author(book_id, author_id);

prize表索引
ALTER TABLE prize ADD INDEX idx_author_id_book_id(author_id, book_id);
ALTER TABLE prize ADD INDEX idx_book_id(book_id);

author表索引
ALTER TABLE author ADD INDEX idx_author_id_author_name(author_id, author_name);
ALTER TABLE author ADD INDEX idx_author_name(author_name);


INSERT INTO book(book_id, author_id, book_name, pages, press) VALUES ('4124','5466','大裂',434,'九州出版社'),
('35325','54646','代码大全',154,'电子工业出版社'),
('5446','76868','C程序设计语言',657,'机械工业出版社'),
('52642','5253','算法导论',756,'机械工业出版社'),
('7888','646467','编程珠玑',435,'人民邮电出版社');

INSERT INTO prize(book_id, author_id, cup_type, cup_time) VALUES ('4124','4322','金奖','2012-12-21'),
('35325','876967','银奖','2009-10-12'),
('5446','126578','铜奖','2016-08-09');

INSERT INTO author(author_id, author_name, family_name, content) VALUES ('4322','金戈','学者'),
('54646','徐宝文','研究人员'),
('76868','潘金贵 ','教师'),
('5253','塞奇威克','研究者'),
('646467','黄倩','编程研究人员');

三.sql
1.查找姓王的作者有多少
SELECT count(*) AS '姓王的人数' FROM author WHERE auther_name LIKE '王%';

2.查询获奖作者的总人数
SELECT count(DISTINCT author_id) AS '获奖者总人数' FROM prize;

3.查询同时获得过金奖、银奖的作者姓名
SELECT DISTINCT author.author_name AS '获奖作者姓名'  from author JOIN prize p1 ON author.author_id=p1.author_id AND p1.cup_type='金奖'
JOIN prize p2 ON author.author_id=p2.author_id AND p2.cup_type='银奖';

4.查询获得金奖的图书有多少本，获得银奖的图书有多少本
SELECT cup_type AS '奖项类别',count(book_id) AS '获奖数目'
FROM prize
WHERE cup_type IN ('金奖', '银奖')
GROUP BY cup_type;

5.查询最近一年内获过奖的作者姓名
SELECT DISTINCT author_name AS'近一年内获奖作者姓名'
FROM author JOIN prize on author.author_id=prize.author_id
WHERE cup_time BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND CURDATE();


四.问答题
1.如何查看表结构
查看表结构的命令： DESC table_name;
使用 show create table table_name\G 可查看建表语句。

2.联合索引中的字段顺序应该如何设计？
在建立联合索引时，经常要用到的字段放在前面，用到的频率较低的字段放在后面;当两个字段的使用的频率相差不大时，将选择性较高，也就是匹配出记录较少的字段放在前面。

3.int(10)和varchar(10)两个字段的(10)有什么区别？
int(10)中的10代表最大的显示宽度，与int(10)所占的空间大小并没有关系，都是占用4个字节的空间;
而varchar(10)中的10指定了可以存储的字符串的最大的长度，具体占用多少空间由传入的字符串决定，是可变的。

4.以下查询如何创建索引能够实现覆盖索引优化？（请给出具体SQL）

select invalid_time_flag from pushtoken_android_62

where uid = 'AC54E24E-FB73-3981-C4BC-CED8D69407F8'

and pid = '10010'

select count(*) from pushtoken_android_62

where uid = 'AC54E24E-FB73-3981-C4BC-CED8D69407F8'

and pid = '10010'


具体的SQL：CREATE INDEX idx_uid_pid_invalid_time_flag ON pushtoken_android_62(uid, pid, invalid_time_flag);