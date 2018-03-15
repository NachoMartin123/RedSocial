package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RequestsService requestsService;
	
	
	@PostConstruct
	public void init() {
		
		User user1 = new User("99999990A","UO111@uniovi.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		User user2 = new User("99999991B","UO222@uniovi.es", "Lucas", "Núñez");
		user2.setPassword("123456");
		User user3 = new User("99999992C","UO333@uniovi.es", "María", "Rodríguez");
		user3.setPassword("123456");
		User user4 = new User("99999993D","UO444@uniovi.es", "Marta", "Almonte");
		user4.setPassword("123456");
		User user5 = new User("99999955F","UO555@uniovi.es", "Pelayo", "Valdes");
		user5.setPassword("123456");
		User user6 = new User("99999966F","UO666@uniovi.es", "Luis", "García");
		user6.setPassword("123456");
		User user7 = new User("99999977F","UO777@uniovi.es", "José", "Álvarez");
		user7.setPassword("123456");
		User user8 = new User("99999988F","UO888@uniovi.es", "Sansa", "Stark");
		user8.setPassword("123456");
		User user9 = new User("99999999F","UO999@uniovi.es", "Cersei", "Lannister");
		user9.setPassword("123456");
		User user10 = new User("99999910F","U010@uniovi.es", "Jon", "Snow");
		user10.setPassword("123456");
		
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
