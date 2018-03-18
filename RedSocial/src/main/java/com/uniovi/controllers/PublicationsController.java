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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.services.PublicationsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.RegisterPublicationFormValidator;

/**
 * Clase que procesa las peticiones de los usuarios de la aplicacion
 * 
 * @author UO231379, UO239718
 * 
 */

@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired 
	private RegisterPublicationFormValidator registerPublicationFormValidator;
	
	/**
	 * Metodo que responde  la peticion /publication/addPublication
	 * Este metodo invoca la logica de negocio correspondiente a las publicaciones y
	 * devuelve la vista publication/addPublication
	 * @param model
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/publication/addPublication", method = RequestMethod.GET)
	public String getPublication(Model model,Pageable pageable) {
		Page<Publication> publications = publicationsService.getPublications(pageable);
		model.addAttribute("publication", new Publication());
		model.addAttribute("publicationList", publications.getContent());
		return "publication/addPublication";
	}

	/**
	 * Metodo POST que responde a la peticion publication/addPublication
	 * Este metodo valida, registra la nueva publicacion y en caso de que no 
	 * haya errores nos redirige a la vista publication/listPublications
	 * @param publication
	 * @param result
	 * @param principal
	 * @return
	 */
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
	
	/**
	 * Metodo que responde a la peticion /publication/listPublications
	 * Este metodo invoca la logica de negocio correspondiente a las publicaciones
	 * para devolver la vista con todas publicaciones de un usuario
	 * @param model
	 * @param principal
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/publication/listPublications")
	public String getList(Model model, Principal principal,Pageable pageable) {
		String email = principal.getName(); 
		User user = usersService.getUserByEmail(email);
		Page<Publication> publications = new PageImpl<Publication>(new LinkedList<Publication>());
	
		publications = publicationsService.getPublicationsForUser(pageable,user) ;
		model.addAttribute("publicationList",publications.getContent());
		model.addAttribute("page", publications);
		
		return "publication/listPublications";
	}

	/**
	 * Metodo que responde a la peticion /publication/publicationsFriend/{user}
	 * Este metodo invoca la logica de negocio correspondiente a las 
	 * publicaciones para devolver la vista con las publicaciones de un usuario amigo del 
	 * usuairo en sesion
	 * @param model
	 * @param principal
	 * @param pageable
	 * @param user
	 * @return
	 */
	@RequestMapping("/publication/publicationsFriend/{user}")
	public String getListForUserFriend(Model model, Principal principal,Pageable pageable,@PathVariable User user) {
		Page<Publication> publications = new PageImpl<Publication>(new LinkedList<Publication>());
	
		publications = publicationsService.getPublicationsForUser(pageable,user) ;
		model.addAttribute("publicationList",publications.getContent());
		model.addAttribute("page", publications);
		
		return "publication/listPublications";
	}

}
