package com.afeey.permission.core.exception;

/**
 * 代码已存在异常
 * 
 * @author 杨金龙
 *
 */
public class CodeExistException extends ExistException {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 * @param message 内容
	 */
	public CodeExistException(String message) {
		super(message);
	}
}
