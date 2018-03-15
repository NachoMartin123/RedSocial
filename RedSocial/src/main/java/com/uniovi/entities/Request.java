package com.uniovi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name="request")
public class Request {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userTarget_id")
	private User userTarget;
	
	@ManyToOne
	@JoinColumn(name = "userMaker_id")
	private User userMaker;
	
	public Request(User userMaker, User userTarget) {
		super();
		this.userMaker = userMaker;
		this.userTarget = userTarget;
	}
	
		
	public Request(){}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(User userTarget) {
		this.userTarget = userTarget;
	}

	public User getUserMaker() {
		return userMaker;
	}

	public void setUserMaker(User userMaker) {
		this.userMaker = userMaker;
	}



	@Override
	public String toString() {
		return "Request [id=" + id + ", userTarget=" + userTarget.getEmail() + ", userMaker=" + userMaker.getEmail() + "]";
	}

	
	

}
