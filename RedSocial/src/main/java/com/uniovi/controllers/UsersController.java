package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
//import com.uniovi.services.RequestsService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
//	@Autowired
//	private RequestsService requestsService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@RequestMapping("/user/list" )
	public String getListado(Model model,Pageable pageable,Principal principal,
			@RequestParam(value = "", required=false) String searchText){
		String email = principal.getName();
		User userEnSesion = usersService.getUserByEmail(email);
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users =  usersService.getUsers(pageable);
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNameForUser(pageable,searchText,userEnSesion);
		}else {
			users = usersService.getUsersForUser(pageable, userEnSesion) ;
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		model.addAttribute("userEnSesion", userEnSesion);
		return "user/list";
	}
	
	@RequestMapping("/user/list/update" )
	public String updateListado(Model model,Pageable pageable,Principal principal){
		String email = principal.getName(); // DNI es el name de la autenticación
		User userEnSesion = usersService.getUserByEmail(email);
		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users =  usersService.getUsers(pageable);
		
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("userEnSesion", userEnSesion);
		return "user/list :: tableUsers";
	}

	@RequestMapping(value = "/user/add")
	public String getUser(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList",users.getContent());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		usersService.addUser(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated  User user,BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	
}
