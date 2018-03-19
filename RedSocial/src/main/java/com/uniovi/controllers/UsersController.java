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
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

/**
 * Clase que procesa las peticiones de los usuarios de la aplicacion
 * 
 * @author UO231379, UO239718
 * 
 */
@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	/**
	 * Metodo que responde a la peticion /user/list Este método invoca la lógica
	 * de negocio correspondiente a los usuarios para devolver la vista con la
	 * lista paginada de usuarios. En caso de que un usuario introduzca una
	 * cadena de busqueda la lista contendra unicamente los usuairos cuyo emial
	 * o nombre coincida con a cadena introducida, en caso contrario se
	 * mostraran todos los usuarios menos el usuario en sesion.
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @param searchText
	 * @return
	 */
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User userEnSesion = usersService.getUserByEmail(email);
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNameForUser(pageable, searchText,
					userEnSesion);
		} else {
			users = usersService.getUsersForUser(pageable, userEnSesion);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		model.addAttribute("userEnSesion", userEnSesion);
		return "user/list";
	}

	/**
	 * Metodo que responde a la peticion /user/list/update. Este metodo invoca
	 * la logica de negocio correspondiente a los usuarios para obtener el
	 * usuario en sesion, obtener y actualizar el fragmento de la vista que
	 * contiene la lista paginada con todos usuarios de la aplicacion menos el
	 * usuario en sesion
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @return
	 */
	@RequestMapping("/user/list/update")
	public String updateListado(Model model, Pageable pageable,
			Principal principal) {
		String email = principal.getName();
		User userEnSesion = usersService.getUserByEmail(email);

		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users = usersService.getUsersForUser(pageable, userEnSesion);

		model.addAttribute("usersList", users.getContent());
		model.addAttribute("userEnSesion", userEnSesion);
		return "user/list :: tableUsers";
	}

	/**
	 * Metodo que responde a la peticion /user/add.
	 * 
	 * @param model
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/user/add")
	public String getUser(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "user/add";
	}

	/**
	 * Metodo POST que responde a la peticion /user/add
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	/**
	 * Metodo GET que responde a la peticion /signup y devuelve la vista signup
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	/**
	 * Metodo POST que responde a la peticion /signup. Este metodo valida,
	 * registra al usuario y en caso de que no haya errores lo redirige a la
	 * vista home
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated User user,
			BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	/**
	 * Metodo que respode a la peticion/login Este metodo devuelve la vista
	 * login. En caso de que haya algun error en el proceso de login se añade un
	 * parámetro adicional a la URL para registrar el error
	 * 
	 * @param error
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			Model model) {
		if (error != null) {
			model.addAttribute("error", "Datos incorrectos");
		}
		return "login";
	}

	/**
	 * Metodo que responde a la peticion /home Este metodo devuelve la vista
	 * home.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

}
