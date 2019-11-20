-- 秒杀执行的存储过程
DELIMITER $$  --设置DELIMITER转换为$$
-- 定义存储过程
--in: 输入参数  out: 输出
--row_count(): 返回上一条修改类型sql(delete, insert, update)影响的行数
--row_count: 0:未修改数据  >0: 修改的行数  <0: sql错误或者为执行过该sql
CREATE PROCEDURE `jieTest`.`execute_seckill`
( in v_seckill_id bigint, in v_phone bigint,in v_state int,
  in v_kill_time timestamp, out r_result int) 
BEGIN
  DECLARE insert_count int DEFAULT 0;
  START TRANSACTION;
  insert ignore into success_kill
    (state, user_phone, seckill_id, create_time) 
       values (v_state, v_phone, v_seckill_id, v_kill_time);
   select row_count() into insert_count;
   IF (insert_count = 0) THEN
      ROLLBACK;
      SET r_result = -1;
   ELSEIF(insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -2;
    ELSE
    update seckill 
     set number = number-1 
     where videri_id = v_seckill_id 
       and start_time <= v_kill_time 
       and end_time >= v_kill_time 
        and number > 0;
     select row_count() into insert_count;
     IF (insert_count = 0) THEN
      ROLLBACK;
      SET r_result = 0;
     ELSEIF(insert_count < 0) THEN
      ROLLBACK;
      SET r_result = -2;
    ELSE
       COMMIT;
      SET r_result = 1;
      END IF;
    END IF;
END
$$  
--存储过程定义已结束
--还原DELIMITER为 ;
DELIMITER ;

set @r_result=-3;
--执行存储过程
call execute_seckill(15, 15611033565, 1, now(), @r_result);

--获取结果
select @r_result;


