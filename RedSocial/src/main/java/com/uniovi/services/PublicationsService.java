package com.uniovi.services;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.repositories.PublicationsRepository;

/**
 * Clase que define la lógica de negocio de la aplicacion relacionada con las
 * publicaciones de los usuairos
 * 
 * @author UO231379, UO239718
 * 
 */
@Service
public class PublicationsService {

	@Autowired
	private PublicationsRepository publicationsRepository;

	@PostConstruct
	public void init() {

	}

	/**
	 * Metodo que permite añadir una plublicacion haciendo uso del repositorio
	 * publicationsRepository
	 * 
	 * @param publication
	 */
	public void addPublication(Publication publication) {
		publicationsRepository.save(publication);
	}

	/**
	 * Metodo que obtiene una lista paginada publicaciones de un usario haciendo
	 * uso del repositorio publicationsRepository
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	public Page<Publication> getPublicationsForUser(Pageable pageable,
			User user) {
		Page<Publication> publications = new PageImpl<Publication>(
				new LinkedList<Publication>());
		publications = publicationsRepository.findAllByUser(user, pageable);

		return publications;
	}

	/**
	 * Metodo que devuelve la lista paginada de todas las publicaciones haciendo
	 * uso del repositorio publicationsRepository
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<Publication> getPublications(Pageable pageable) {
		Page<Publication> publications = publicationsRepository
				.findAll(pageable);
		return publications;
	}
}
