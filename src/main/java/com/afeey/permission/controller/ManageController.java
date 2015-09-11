package com.afeey.permission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理控制器
 * 
 * @author afeey
 *
 */
@Controller
public class ManageController {

	@Autowired
	private SessionRegistry sessionRegistry;

	@RequestMapping("/manage")
	public String manage(Model model) {

		model.addAttribute("online", sessionRegistry.getAllPrincipals().size());

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("user",userDetails);

		return "manage/manage";
	}
}
