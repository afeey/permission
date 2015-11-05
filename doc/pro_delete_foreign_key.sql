CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_foreign_key`(IN tb_sehema VARCHAR(50))
BEGIN
	DECLARE count INT DEFAULT 0; 	-- 数量
	DECLARE tb_name VARCHAR(50); 	-- 表名
	DECLARE key_name VARCHAR(50); 	-- 外键约束名
    DECLARE done INT DEFAULT FALSE; -- 完成标志
	DECLARE cur CURSOR FOR SELECT table_name, constraint_name FROM information_schema.TABLE_CONSTRAINTS WHERE table_schema=tb_sehema and constraint_type<>'PRIMARY KEY';
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
	OPEN cur;
	read_loop: LOOP
		FETCH cur INTO tb_name, key_name;

		IF done THEN
			LEAVE read_loop;
		END IF;
		
        SET @drop_sql = CONCAT('ALTER TABLE ',tb_name,' DROP FOREIGN KEY ',key_name); 

		prepare stmt from @drop_sql;
        EXECUTE stmt;
        deallocate prepare stmt; 

        set count = count+1;
	END LOOP;

	CLOSE cur;
    
	select count;
END