package com.afeey.permission.po;

import java.math.BigDecimal;
import java.util.Date;

import com.afeey.permission.enums.UseStatusEnum;

import java.util.List;

/**
 * 用户
 */
public class User {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机
	 */
	private String cellphone;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * 余额
	 */
	private BigDecimal money;

	/**
	 * 昵称
	 */
	private String alias;

	/**
	 * 真实姓名
	 */
	private String full_name;

	/**
	 * 性别 0未填写 1男 2女
	 */
	private int sex;

	private Date birthday;

	/**
	 * 身份证
	 */
	private String idCard;

	/**
	 * 注册时间
	 */
	private String regTime;

	/**
	 * 已验证 0未验证 1已验证
	 */
	private int validate = 0;

	/**
	 * 最后登录时间
	 */
	private Date loginTime;

	/**
	 * 最后登录IP
	 */
	private String loginIp;

	/**
	 * 登录次数
	 */
	private int loginTimes;

	/**
	 * 状态 0使用 1禁用
	 */
	private UseStatusEnum status;

	/**
	 * 角色列表
	 */
	private List<Role> roleList;
	
	/**
	 * 地址列表
	 */
	// private List<Address> addressList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public int getValidate() {
		return validate;
	}

	public void setValidate(int validate) {
		this.validate = validate;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public UseStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UseStatusEnum status) {
		this.status = status;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
}
