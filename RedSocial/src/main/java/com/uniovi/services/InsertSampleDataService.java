package com.uniovi.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	
	@PostConstruct
	public void init() {
		
		User user1 = new User("99999990A","pedro@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		User user2 = new User("99999991B","lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		User user3 = new User("99999992C","maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		User user4 = new User("99999993D","marta@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		User user5 = new User("99999977E","pelayo@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		User user6 = new User("99999988F","andrea@gmail.com", "Andrea", "Núñez");
		user6.setPassword("123456");
		
		
		Set user1Publications = new HashSet<Publication>() {

			{

				add(new Publication("Publicacion1","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user1));
				add(new Publication("Publicacion2","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user1));
			}
		};
		user1.setPublications(user1Publications);
		
		Set user2Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion3","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user2));
				add(new Publication("Publicacion4","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user2));
			}
		};
		user2.setPublications(user2Publications);
		
		Set user3Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion5","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user3));
				add(new Publication("Publicacion6","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user3));
			}
		};
		user3.setPublications(user3Publications);
		
		Set user4Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion7","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user4));
				add(new Publication("Publicacion8","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user4));
			}
		};
		user4.setPublications(user4Publications);
		

		Set user5Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion9","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user5));
				add(new Publication("Publicacion10","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user5));
			}
		};
		user5.setPublications(user5Publications);


		Set user6Publications = new HashSet<Publication>() {
			{

				add(new Publication("Publicacion11","Esta es una publicacion de prueba",new Date(new java.util.Date().getTime()), user6));
				
			}
		};
		user6.setPublications(user6Publications);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}


}
