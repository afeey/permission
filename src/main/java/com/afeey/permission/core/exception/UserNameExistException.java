package com.afeey.permission.core.exception;

/**
 * 用户名已存在异常
 * 
 * @author 杨金龙
 */
public class UserNameExistException extends ExistException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7487438545324607631L;

	/**
	 * 构造方法
	 * @param message 内容
	 */
	public UserNameExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
