package com.uniovi.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.Request;
import com.uniovi.entities.User;

/**
 * Clase InsertSampleDataService para crear usuarios de prueba
 * 
 * @author UO231379, UO239718
 * 
 */
@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RequestsService requestsService;

	/**
	 * Metodo que crea dinámicmente usuarios con sus publicaciones y peticiones
	 * de amistad
	 */
	@PostConstruct
	public void init() {

		User user1 = new User("99999990A", "pedro@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		User user2 = new User("99999991B", "lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		User user3 = new User("99999992C", "maria@gmail.com", "María",
				"Rodríguez");
		user3.setPassword("123456");
		User user4 = new User("99999993D", "marta@gmail.com", "Marta",
				"Almonte");
		user4.setPassword("123456");
		User user5 = new User("99999977E", "pelayo@gmail.com", "Pelayo",
				"Valdes");
		user5.setPassword("123456");
		User user6 = new User("99999988F", "andrea@gmail.com", "Andrea",
				"Núñez");
		user6.setPassword("123456");
		User user7 = new User("99999977F", "UO777@uniovi.es", "José",
				"Álvarez");
		user7.setPassword("123456");
		User user8 = new User("99999988F", "UO888@uniovi.es", "Sansa", "Stark");
		user8.setPassword("123456");
		User user9 = new User("99999999F", "UO999@uniovi.es", "Cersei",
				"Lannister");
		user9.setPassword("123456");
		User user10 = new User("99999910F", "U010@uniovi.es", "Jon", "Snow");
		user10.setPassword("123456");

		@SuppressWarnings("serial")
		Set<Publication> user1Publications = new HashSet<Publication>() {
			{
				add(new Publication("Publicacion1",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user1));
				add(new Publication("Publicacion2",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user1));
			}
		};
		user1.setPublications(user1Publications);

		@SuppressWarnings("serial")
		Set<Publication> user2Publications = new HashSet<Publication>() {
			{
				add(new Publication("Publicacion3",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user2));
				add(new Publication("Publicacion4",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user2));
			}
		};
		user2.setPublications(user2Publications);

		@SuppressWarnings("serial")
		Set<Publication> user3Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion5",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user3));
				add(new Publication("Publicacion6",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user3));
			}
		};
		user3.setPublications(user3Publications);

		@SuppressWarnings("serial")
		Set<Publication> user4Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion7",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user4));
				add(new Publication("Publicacion8",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user4));
			}
		};
		user4.setPublications(user4Publications);

		@SuppressWarnings("serial")
		Set<Publication> user5Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion9",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user5));
				add(new Publication("Publicacion10",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user5));
			}
		};
		user5.setPublications(user5Publications);

		@SuppressWarnings("serial")
		Set<Publication> user6Publications = new HashSet<Publication>() {
			{
				add(new Publication("Publicacion11",
						"Esta es una publicacion de prueba",
						new Date(new java.util.Date().getTime()), user6));
			}
		};
		user6.setPublications(user6Publications);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);

		Request requestDeUser1_1 = new Request(user1, user2);
		Request requestDeUser1_2 = new Request(user1, user4);

		Request requestParaUser1_1 = new Request(user6, user1);
		Request requestParaUser1_2 = new Request(user3, user1);
		Request requestParaUser1_3 = new Request(user7, user1);
		Request requestParaUser1_4 = new Request(user8, user1);
		Request requestParaUser1_5 = new Request(user9, user1);
		Request requestParaUser1_6 = new Request(user10, user1);

		requestsService.addRequest(requestDeUser1_1);
		requestsService.addRequest(requestDeUser1_2);

		requestsService.addRequest(requestParaUser1_1);
		requestsService.addRequest(requestParaUser1_2);
		requestsService.addRequest(requestParaUser1_3);
		requestsService.addRequest(requestParaUser1_4);
		requestsService.addRequest(requestParaUser1_5);
		requestsService.addRequest(requestParaUser1_6);

	}

}
