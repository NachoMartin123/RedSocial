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

	public Publication(String titulo, String texto,Date fecha_Creacion, User user) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha_Creacion = fecha_Creacion;
		this.user = user;
	}

	public Publication(String titulo, String texto, Date fecha_Creacion) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha_Creacion = fecha_Creacion;
	}
	
	public Publication(User user) {
		this.user = user;
	}
	
	public Publication() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
