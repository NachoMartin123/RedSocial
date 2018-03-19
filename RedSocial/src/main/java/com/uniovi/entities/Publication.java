package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad Publication que representa las publicaciones de la aplicacion
 * 
 * @author UO231379, UO239718
 * 
 */
@Entity
@Table(name = "publication")
public class Publication {

	@Id
	@GeneratedValue
	private long id;
	private String titulo;
	private String texto;
	@Temporal(TemporalType.DATE)
	private Date fecha_Creacion;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * Constructor con parametros
	 * 
	 * @param titulo
	 *            de la publicacion
	 * @param texto
	 *            de la publicacion
	 * @param fecha_Creacion
	 *            de la publicacion
	 * @param user
	 *            que realiza la publicacion
	 */
	public Publication(String titulo, String texto, Date fecha_Creacion,
			User user) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha_Creacion = fecha_Creacion;
		this.user = user;
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param titulo
	 *            de la publicacion
	 * @param texto
	 *            de la publicacion
	 * @param fecha_Creacion
	 *            de la publicacion
	 */
	public Publication(String titulo, String texto, Date fecha_Creacion) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha_Creacion = fecha_Creacion;
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param user
	 *            propietario de la publicacion
	 */
	public Publication(User user) {
		this.user = user;
	}

	/**
	 * Constructor sin parametros
	 */
	public Publication() {

	}

	/**
	 * Metodo que devuelve el valor del atributo id
	 * 
	 * @return id de la publicacion
	 */
	public long getId() {
		return id;
	}

	/**
	 * Metodo que modifica el valor del atributo id
	 * 
	 * @param id
	 *            de la publicacion
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metodo que devuelve el valor del atributo titulo
	 * 
	 * @return titulo de la publicacion
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo que modifica el valor del atributo titulo
	 * 
	 * @param titulo
	 *            de la publicacion
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Metodo que devuelve el valor del atributo texto
	 * 
	 * @return texto de la publicacion
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Metodo que modifica el valor del atributo texto
	 * 
	 * @param texto
	 *            de la aplicacion
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Metodo que devuelve el valor del atributo fechaCreacion
	 * 
	 * @return fecha creacion de la publicacion
	 */
	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	/**
	 * Metodo que modifica el valor del atributo fechaCreacio
	 * 
	 * @param fecha_Creacion
	 *            de la publicacion
	 */
	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
	}

	/**
	 * Metodo que devuelve el valor del atributo user
	 * 
	 * @return user - usuario propietario de la publicacion
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
