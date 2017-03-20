DROP TABLE IF EXISTS tb_user;
CREATE TABLE `tb_user` (
  id             INT          NOT NULL  AUTO_INCREMENT
  COMMENT '用户编号',
  username       VARCHAR(20)  NOT NULL  DEFAULT ''
  COMMENT '用户姓名',
  age            INT          NOT NULL  DEFAULT 0
  COMMENT '年龄',
  gender         INT          NOT NULL  DEFAULT 0
  COMMENT '性别,0表示男,1表示女',
  remark         VARCHAR(255) NOT NULL  DEFAULT ''
  COMMENT '备注',
  isValid        INT          NOT NULL  DEFAULT 1
  COMMENT '是否有效,1表示有效，0表示无效',
  registerTime   DATETIME     NOT NULL  DEFAULT ''
  COMMENT '注册时间',
  lastModifyTime TIMESTAMP    NOT NULL  DEFAULT CURRENT_TIMESTAMP()
  COMMENT '最近修改时间',
  PRIMARY KEY (id)
);

INSERT INTO tb_user
(username, age, gender, remark, isValid, lastModifyTime, registerTime)
VALUES
  ('张三', 45, 1, '丝袜控', 1, now(), now()), ('李四', 22, 0, '恋足癖', 1, now(), now()),
  ('老易', 32, 1, 'gay', 1, now(), now()), ('小刘', 26, 0, '肌肉男', 1, now(), now()),
  ('王大拿', 89, 1, '异性恋', 1, now(), now());