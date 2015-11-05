/*
	删除外键约束
*/
call drop_foreign_key('permission');
SELECT * FROM information_schema.key_column_usage WHERE table_schema='permission' and constraint_name<>'PRIMARY';

/*
	删除外键索引
*/
call drop_foreign_index('permission');
SELECT * FROM information_schema.STATISTICS WHERE table_schema='permission' and index_name<>'PRIMARY';

