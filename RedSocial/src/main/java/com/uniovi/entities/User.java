package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
//	@Column(unique = true)
	private String dni;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private String password;
	@Transient 
	private String passwordConfirm;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Publication> publications;
	
	//coleccion de invitaciones RECIBIDAS
	@OneToMany(mappedBy = "userTarget", cascade = CascadeType.ALL)
	private Set<Request> requestsReceived = new HashSet<Request>();
	
	
	@OneToMany(mappedBy = "userMaker", cascade = CascadeType.ALL)
	private Set<Request> requestsMaked = new HashSet<Request>();


	//relacion many to many para la lista de amigos
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="friends", 
			joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="friend_id",referencedColumnName="id")})
	private Set<User> friendList = new HashSet<User>();//lista de amigos 
	
	public User(String dni, String email, String name, String lastName) {
		super();
		this.dni = dni;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Request> getRequestsReceived() {
		return requestsReceived;
	}

	public void setRequestsReceived(Set<Request> requestsReceived) {
		this.requestsReceived = requestsReceived;
	}

	public Set<Request> getRequestsMaked() {
		return requestsMaked;
	}

	public void setRequestsMaked(Set<Request> requestsMaked) {
		this.requestsMaked = requestsMaked;
	}

	public boolean containsRequestTo(User target) {
		for(Request r: getRequestsMaked()) {
			if(r.getUserTarget().equals(target))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", dni=" + dni + ", email=" + email + ", name=" + name + ", lastName=" + lastName
				+ ", role=" + role + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", requestsReceived=" + requestsReceived.size() + ", requestsMaked=" + requestsMaked.size() + 
				", friends=" + friendList.size() +"]";
	}

	public Set<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(Set<User> friendList) {
		this.friendList = friendList;
	}

	
	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}

	public Set<Publication> getPublications() {
		return publications;
	}


}
