package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
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

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.services.PublicationsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.RegisterPublicationFormValidator;


@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired 
	private RegisterPublicationFormValidator registerPublicationFormValidator;
	
	
	@RequestMapping(value = "/publication/addPublication", method = RequestMethod.GET)
	public String getPublication(Model model,Pageable pageable) {
		Page<Publication> publications = publicationsService.getPublications(pageable);
		model.addAttribute("publication", new Publication());
		model.addAttribute("publicationList", publications.getContent());
		return "publication/addPublication";
	}

	@RequestMapping(value = "/publication/addPublication", method = RequestMethod.POST)
	public String setPublication(@ModelAttribute @Validated Publication publication, BindingResult result,Principal principal) {
		registerPublicationFormValidator.validate(publication, result);
		if (result.hasErrors()) {
			return "publication/addPublication";
		}
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		publication.setUser(user);
		publication.setFecha_Creacion(new Date(new java.util.Date().getTime()));
		publicationsService.addPublication(publication);
		return "redirect:/publication/listPublications";
	}
	
	@RequestMapping("/publication/listPublications")
	public String getList(Model model, Principal principal,Pageable pageable) {
		String email = principal.getName(); 
		User user = usersService.getUserByEmail(email);
		Page<Publication> publications = new PageImpl<Publication>(new LinkedList<Publication>());
	
		publications = publicationsService.getPublicationsForUser(pageable,user) ;
		//publications = publicationsService.getPublications(pageable);
		model.addAttribute("publicationList",publications.getContent());
		model.addAttribute("page", publications);
		
		return "publication/listPublications";
	}

}
