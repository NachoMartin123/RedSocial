package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class FriendsController {
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping("/friend/list" )
	public String getListado(Model model,Pageable pageable,Principal principal){
		String dni = principal.getName(); // DNI es el name de la autenticación
		User userEnSesion = usersService.getUserByEmail(dni);
		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users = usersService.getFriendsForUser(pageable, userEnSesion) ;
		model.addAttribute("friendList", users.getContent());
		model.addAttribute("page", users);
		model.addAttribute("userEnSesion", userEnSesion);
		return "friend/list";
	}

}
