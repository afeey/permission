package com.afeey.permission.core.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.enums.YesNoEnum;
import com.afeey.permission.core.po.Address;

/**
 * 用户实体类
 * 
 * @author 杨金龙
 *
 */
public class User {
	private String id;
	private String userName;
	private String password;
	private String cellphone;
	private String mail;
	private BigDecimal money;
	private String alias;
	private String fullName;
	private int sex;
	private Date birthday;
	private String idCard;
	private Date regTime;
	private YesNoEnum validate;
	private Date loginTime;
	private String loginIp;
	private int loginTimes;
	private UseStatusEnum status;
	private List<Role> roleList;
	private List<Address> addressList;

	public User() {
		this.id = "";
		this.userName = "";
		this.password = "";
		this.cellphone = "";
		this.mail = "";
		this.money = new BigDecimal(0);
		this.alias = "";
		this.fullName = "";
		this.sex = 0;
		this.birthday = new Date();
		this.idCard = "";
		this.regTime = new Date();
		this.validate = YesNoEnum.NO;
		this.loginTime = new Date();
		this.loginIp = "";
		this.loginTimes = 0;
		this.status = UseStatusEnum.ENABLE;
		this.roleList = new ArrayList<Role>();
		this.addressList = new ArrayList<Address>();
	}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public YesNoEnum getValidate() {
		return validate;
	}

	public void setValidate(YesNoEnum validate) {
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

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
}
