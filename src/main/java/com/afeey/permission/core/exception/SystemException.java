package com.afeey.permission.core.exception;

/**
 * 系统内部异常
 * 
 * @author 王跃飞
 *
 */
public class SystemException extends Exception {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6362688308237200777L;

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	
}
