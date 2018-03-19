package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

/**
 * Clase que accede al sistema de gestion de datos
 * 
 * @author UO231379, UO239718
 * 
 */
public interface PublicationsRepository
		extends CrudRepository<Publication, Long> {

	/**
	 * Metodo que accede a la base de datos para devolver una lista paginada de
	 * todos los usuarios de la aplicacion
	 * 
	 * @param pageable
	 * @return
	 */
	Page<Publication> findAll(Pageable pageable);

	/**
	 * Metodo de accede a la base de datos para encontrar todas las
	 * publicaciones de un usuairo dado
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 */
	@Query("SELECT p FROM Publication p WHERE p.user = ?1 ")
	Page<Publication> findAllByUser(User user, Pageable pageable);
}
