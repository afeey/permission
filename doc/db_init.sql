INSERT INTO `sys_role` VALUES 
('1','管理员','Admin','系统管理员',0,1,'2015-10-01 00:00:00','2015-10-01 00:00:00',''),
('2','VIP会员','Vip','VIP会员',0,2,'2015-10-01 00:00:00','2015-10-01 00:00:00',''),
('3','普通会员','Member','普通会员',0,3,'2015-10-01 00:00:00','2015-10-01 00:00:00','');

INSERT INTO `sys_user` VALUES 
('1','admin','3980e4044675f6339248ee0c735c7d72','18662717353','',0.00,'管理员','管理员',0,'1900-01-01 00:00:00','','2015-01-01 00:00:00',0,'2015-01-01 00:00:00','49.77.196.92',0,0),
('2','wyf','3980e4044675f6339248ee0c735c7d72','18662729909','',0.00,'9527','王小强',0,'1900-01-01 00:00:00','','2015-01-01 00:00:00',0,'2015-01-01 00:00:00','49.77.196.92',0,0),
('3','yjl','3980e4044675f6339248ee0c735c7d72','18651665700','',0.00,'小金鱼','小金鱼',0,'1900-01-01 00:00:00','','2015-01-01 00:00:00',0,'2015-01-01 00:00:00','49.77.196.92',0,0),
('4','dj','3980e4044675f6339248ee0c735c7d72','15950592126','',0.00,'董胖','董胖',0,'1900-01-01 00:00:00','','2015-01-01 00:00:00',0,'2015-01-01 00:00:00','49.77.196.92',0,0);

INSERT INTO `sys_user_role` VALUES 
('1','1','1','1900-01-01 00:00:00'),
('2','2','2','1900-01-01 00:00:00'),
('3','3','3','1900-01-01 00:00:00'),
('4','4','3','1900-01-01 00:00:00');