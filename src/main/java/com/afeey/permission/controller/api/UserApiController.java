package com.afeey.permission.controller.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.exception.CellphoneExistException;
import com.afeey.permission.core.exception.EmailExistException;
import com.afeey.permission.core.exception.UserNameExistException;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.User;
import com.afeey.permission.core.service.IPage;
import com.afeey.permission.core.service.IUserService;

/**
 * 用户控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserApiController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/add")
	public Object add(HttpServletRequest request){
		
		boolean success=false;
		String msg="";
	
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName").trim();
		String cellphone = request.getParameter("cellphone")==null?"":request.getParameter("cellphone").trim();
		String email = request.getParameter("email")==null?"":request.getParameter("email").trim();
		String[] roleIds =request.getParameterValues("roleIds");
		
		User user=new User();
		user.setUserName(userName);
		user.setCellphone(cellphone);
		user.setMail(email);
		
		List<String> roleIdList=new ArrayList<String>();
		if(roleIds!=null){
			for (String roleId : roleIds) {
				roleIdList.add(roleId);
			}
		}
		try {
			userService.add(user, roleIdList);
			success = true;
			msg = "保存成功";
		} catch (UserNameExistException e) {
			success = false;
			msg = "用户名已注册";
		} catch (CellphoneExistException e) {
			success = false;
			msg = "手机号已注册";
		} catch (EmailExistException e) {
			success = false;
			msg = "邮箱已注册";
		} catch (Exception e) {
			success = false;
			msg = e.getMessage();
		}

		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	@RequestMapping(value = "/update")
	public Object update(HttpServletRequest request){
		
		boolean success=false;
		String msg="";
	
		String id = request.getParameter("id")==null?"":request.getParameter("id").trim();
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName").trim();
		String cellphone = request.getParameter("cellphone")==null?"":request.getParameter("cellphone").trim();
		String email = request.getParameter("email")==null?"":request.getParameter("email").trim();
		
		try {
			User user=userService.getById(id);
			user.setUserName(userName);
			user.setCellphone(cellphone);
			user.setMail(email);
			userService.update(user);
			success = true;
			msg = "保存成功";
		} catch (UserNameExistException e) {
			success = false;
			msg = "用户名已注册";
		} catch (CellphoneExistException e) {
			success = false;
			msg = "手机号已注册";
		} catch (EmailExistException e) {
			success = false;
			msg = "邮箱已注册";
		} catch (Exception e) {
			success = false;
			msg = e.getMessage();
		}

		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	@RequestMapping(value = "/delete")
	public Object delete(@RequestParam("id") String id){
		
		boolean success=false;
		String msg="";
		
		try{
			userService.delete(id);
			success = true;
			msg = "删除成功";
		}catch(Exception e){
			success = false;
			msg = e.getMessage();
		}
		
		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	/**
	 * 查询用户
	 * @param id id
	 * @return json
	 */
	@RequestMapping(value = "/query")
	public Object query(@RequestParam("id") String id){
		
		boolean success=false;
		String msg="";
		
		User user = userService.getById(id);
		success = true;
		msg = "查询成功";
		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		result.setData(user);

		return result;
	}
	
	/**
	 * 用户列表
	 * @param name 名称
	 * @param code 代码
	 * @param start 起始索引
	 * @param length 记录数
	 * @return json
	 */
	@RequestMapping(value = "/list")
	public Object list(
			@RequestParam("userName") String userName, 
			@RequestParam("roleId") String roleId,
			@RequestParam("start") Integer start, 
			@RequestParam("length") Integer length){

		int number = start / length + 1;
		IPage<User> page = userService.list(roleId, userName, null, null, null, number, length);

		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for (User user : page.getList()) {
			HashMap<String, String> map=new HashMap<String, String>();
			
			//获取角色名
			List<Role> roleList = userService.listRoles(user.getId());
			String roles = "";
			for (Role role : roleList) {
				roles+=","+role.getName();
			}
			if(roles.length()>0){
				roles = roles.substring(1);
			}
			
			String sex = "";
			switch (user.getSex()) {
				case 1:
					sex="男";
					break;
				case 2:
					sex="女";
					break;
				default:
					break;
			}
			
			map.put("roles", roles);
			map.put("id", user.getId());
			map.put("userName", user.getUserName());
			map.put("cellphone", user.getCellphone());
			map.put("mail", user.getMail());
			map.put("money", user.getMoney().toString());
			map.put("alias", user.getAlias());
			map.put("fullName", user.getFullName());
			map.put("sex", sex);
			map.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
			map.put("idCard", user.getIdCard());
			map.put("regTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getRegTime()));
			map.put("validate", user.getValidate().name());
			map.put("loginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLoginTime()));
			map.put("loginIp", user.getLoginIp());
			map.put("loginTimes",Integer.toString(user.getLoginTimes()));
			map.put("status", user.getStatus().name());
			list.add(map);
		}
		
		Result result = new Result();
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		result.setData(list);

		return result;
	}

}
