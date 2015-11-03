package com.afeey.permission.core.exception;

/**
 * 邮箱已存在异常
 * 
 * @author 杨金龙
 */
public class EmailExistException extends ExistException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7487438545324607631L;

	/**
	 * 构造方法
	 * @param message 内容
	 */
	public EmailExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
