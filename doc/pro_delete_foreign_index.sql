CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_foreign_index`(IN tb_sehema VARCHAR(50))
BEGIN
	DECLARE count INT DEFAULT 0; 	 -- 数量
	DECLARE tb_name VARCHAR(100); 	 -- 表名
	DECLARE in_name VARCHAR(100); 	 -- 索引名
    DECLARE done INT DEFAULT FALSE;  -- 完成标志
	DECLARE cur CURSOR FOR SELECT table_name,index_name FROM information_schema.STATISTICS WHERE table_schema=tb_sehema and index_name<>'PRIMARY';
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
	OPEN cur;
	read_loop: LOOP
		FETCH cur INTO tb_name, in_name;
    
		IF done THEN
			LEAVE read_loop;
		END IF;
    
        SET @drop_sql = CONCAT('ALTER TABLE ',tb_name,' DROP INDEX ',in_name);

		prepare stmt from @drop_sql;
        EXECUTE stmt;
        deallocate prepare stmt;

        set count = count+1;
	END LOOP;

	CLOSE cur;
    
	select count;
END