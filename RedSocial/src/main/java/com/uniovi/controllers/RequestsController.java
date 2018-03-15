package com.uniovi.controllers;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;
import com.uniovi.services.RequestsService;
import com.uniovi.services.UsersService;

@Controller
public class RequestsController {
	
	
	@Autowired 
	private UsersService usersService;
	
	@Autowired 
	private RequestsService requestsService;
	
	
	
	@RequestMapping(value="/request/add/{id}", method=RequestMethod.GET)
	public String createRequest(Model model, Principal principal, @PathVariable Long id){
		String email = principal.getName();
		User target = usersService.findById(id);
		User maker = usersService.getUserByEmail(email);
		Request r = new Request(maker, target);
		
		requestsService.addRequest(r);
		
		return "redirect:/user/list/update"; //refresca la lista de notas
	}
	@RequestMapping(value="/mark/remove/{id}", method=RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id){
//		requestsService.setRequestAccepted(false, id);
		return "redirect:/user/list";
	}
	
	
	@RequestMapping("/request/list" )
	public String getListado(Model model,Pageable pageable,Principal principal){
		String email = principal.getName(); // DNI es el name de la autenticación
		User userEnSesion = usersService.getUserByEmail(email);
		
		Page<Request> requests = requestsService.getRequestsForUser(pageable, userEnSesion);
		
		model.addAttribute("requestsList", requests.getContent());
		model.addAttribute("page", requests);
		model.addAttribute("userEnSesion", userEnSesion);
		return "request/list";
	}
	
	@RequestMapping("/request/list/update" )
	public String updateListado(Model model,Pageable pageable,Principal principal){
		String email = principal.getName(); // DNI es el name de la autenticación
		User userEnSesion = usersService.getUserByEmail(email);
		
		Page<Request> requests = requestsService.getRequestsForUser(pageable, userEnSesion);
		
		model.addAttribute("requestsList", requests.getContent());
//		model.addAttribute("page", requests);
		return "request/list :: tableRequests";
	}
	
	@RequestMapping("request/accept/{id}" )
	public String getListado(Model model,Pageable pageable,Principal principal, @PathVariable Long id){
		String email = principal.getName(); // DNI es el name de la autenticación
		User userEnSesion = usersService.getUserByEmail(email);
		
		Request r = requestsService.findRequestById(id);
		r.getUserMaker().getFriendList().add(r.getUserTarget());
		r.getUserTarget().getFriendList().add(r.getUserMaker());
		
		requestsService.deleteRequest(r.getId());
		//comprueba se hay una peticion con los roles al reves
		Request r2 =requestsService.findRequestByTargetAndMaker(r.getUserMaker(), r.getUserTarget());
		if(r2!=null)
			requestsService.deleteRequest(r2.getId());
		
		model.addAttribute("userEnSesion", userEnSesion);
//		return "user/list :: tableUsers";
		return "redirect:/request/list";
	}
}
