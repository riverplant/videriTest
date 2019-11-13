---init db

CREATE DATABASE seckill;

use seckill ; 

CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id), 
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT'秒杀库存'


insert into seckill(name,number,start_time,end_time)
values
  ('1000mioashaiphone6',100,'2015-11-11 00:00:00','2015-11-12 00:00:00' ),
  ('500mioashaipad2',200,'2015-11-11 00:00:00','2015-11-12 00:00:00' ),
  ('300mioashaixiaomi4',300,'2015-11-11 00:00:00','2015-11-12 00:00:00' ),
  ('200mioashahongminote',400,'2015-11-11 00:00:00','2015-11-12 00:00:00' ),
  ('3000mioashaiphone7',10,'2015-11-11 00:00:00','2015-11-12 00:00:00' );



---details


CREATE TABLE success_kill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2: 已发货 3:已收货',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone), /*  联合主键 */
key idx_create_time(create_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT'秒杀成功眯细表'



