package com.uniovi.services;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * Clase que define la lógica de negocio de la aplicacion realcionada con los usuarios
 * 
 * @author UO231379, UO239718
 * 
 */
@Service
public class UsersService {
	
	@Autowired 
	private UsersRepository usersRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostConstruct
	public void init() {
		
	}
	
	/**
	 * Metodo que devuelve una lista paginada de todos los usuairos de 
	 * la aplicacion haciendo uso del respositorio usersRepository
	 * @param pageable
	 * @return
	 */
	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}
	

	/**
	 * Metodo que permite añadir un usuario con su contraseña encriptada
	 *  a la aplicacion haciendo uso del repositorio usersRepository
	 * @param user
	 */
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	/**
	 * Metodo que devuelve un usuario haciendo la busqueda por el email
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	/**
	 * Metodo que devuelve una lista paginada de usuarios en funcion de la cadena 
	 * proporcionada por el usuario
	 * @param pageable
	 * @param searchText
	 * @param user
	 * @return
	 */
	public Page<User> searchUsersByNameForUser(Pageable pageable,String searchText, User user) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.searchByNameOrEmail(pageable,searchText, user);
		
		return users;
	}
	
	/**
	 * Metodo que devuelve una lista paginada de usuarios menos el usuario en 
	 * sesion haciendo uso del resositorio usersRepository
	 * @param pageable
	 * @param user
	 * @return
	 */
	public Page<User> getUsersForUser(Pageable pageable,User user) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users= usersRepository.findAllButUser(pageable, user);
		return users;
	}


	/**
	 * Metodo que devuelve un usuario haciendo uso del respositorio usersRepository
	 * @param id del usuairo a buscar
	 * @return
	 */
	public User findById(Long id) {
		return usersRepository.findById(id).orElse(null);
	}


	/**
	 * Metodo que devuelve una lista paginada de usuairos amigos de un usuairo
	 * haciendo uso del repositorio usersRepository
	 * @param pageable
	 * @param user 
	 * @return
	 */
	public Page<User> getFriendsForUser(Pageable pageable, User user) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		users = usersRepository.searchFriendsForUser(pageable, user);
		return users;
	}

}
