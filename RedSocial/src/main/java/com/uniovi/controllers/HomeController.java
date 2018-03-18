package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase que procesa las peticiones de los usuarios de la aplicacion
 * 
 * @author UO231379, UO239718
 * 
 */
@Controller
public class HomeController {
	
	/**
	 * Metodo que devuelve la vista index
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * Metodo que responde a la peticion /home
	 * 
	 * @return vista home
	 */
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}

}
