package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entidad User que representa los usuarios de la aplicacion 
 * 
 * @author UO231379, UO239718
 * 
 */
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
	
	
	/**
	 * Constructor con parametros
	 * 
	 * @param dni del usuario
	 * @param email del usuario
	 * @param name del usuario
	 * @param lastName del usuario
	 */
	public User(String dni, String email, String name, String lastName) {
		super();
		this.dni = dni;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	/**
	 * Constructor sin parametros
	 */
	public User() {
	}
	

	/**
	 * Metodo que devuelve el valor del atributo id
	 * 
	 * @return id del usuario
	 */
	public long getId() {
		return id;
	}

	/**
	 * Metodo que modifica el valor del atributo id
	 * 
	 * @param id del usuario
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metodo que devuelve el valor del atributo dni
	 * 
	 * @return dni del usuario
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Metodo que modifica el valor del atributo dni
	 * 
	 * @param dni del usuario
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Metodo que devuelve el valor del atributo name
	 * 
	 * @return name - nombre del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo que modifica el valor del atributo name
	 * 
	 * @param name - nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metodo que devuelve el valor del atributo lastName
	 * 
	 * @return lastName - apellido del usuario
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Metodo que modifica el valor del atributo lastName
	 * 
	 * @param lastName - apellido del usuario
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Metodo que devuelve el valor del atributo password
	 * 
	 * @return password - contraseña del usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo que modifica el valor del atributo password
	 * 
	 * @param password - contraseña del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Metodo que devuelve el valor del atributo passwordConfirm
	 * 
	 * @return passwordConfirm confirmacion de la contraseña
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * Metodo que modifica el valor del atributo passwordConfirm
	 * 
	 * @param passwordConfirm confirmacion de la contrseña
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	/**
	 * Metodo que devuelve el valor del atributo role
	 * 
	 * @return role - rol del usuario
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Metodo que modifica el valor del atributo role
	 * 
	 * @param role - rol del usuario
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Metodo que devuelve el valor del atributo email
	 * 
	 * @return email del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que modifica el valor del atributo email
	 * 
	 * @param email del usuairo
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que devuelve el valor del atributo requestReceived
	 * 
	 * @return requestReceiver peticiones recibidas
	 */
	public Set<Request> getRequestsReceived() {
		return requestsReceived;
	}

	/**
	 * Metodo que modifica el valor del atributo requestsReceved
	 * 
	 * @param requestsReceived petiicones recibidas
	 */
	public void setRequestsReceived(Set<Request> requestsReceived) {
		this.requestsReceived = requestsReceived;
	}

	/**
	 * Metodo que devuelve el valor del atributo requestsMaked
	 * 
	 * @return requestsMaked peticiones realizadas
	 */
	public Set<Request> getRequestsMaked() {
		return requestsMaked;
	}

	/**
	 * Metodo que modifica el valor del atributo requestsMaked
	 * 
	 * @param requestsMaked peticiones realizadas
	 */
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

	/**
	 * Metodo toString 
	 */
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
