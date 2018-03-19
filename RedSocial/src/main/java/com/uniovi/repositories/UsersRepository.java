package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

/**
 * Clase que accede al sistema de gestión de datos
 * 
 * @author UO231379, UO239718
 * 
 */
public interface UsersRepository extends CrudRepository<User, Long> {

	/**
	 * Metodo que accede a la base de datos para obtener la lista paginada de
	 * todos los usuarios de la aplicacion
	 * 
	 * @param pageable
	 * @return
	 */
	Page<User> findAll(Pageable pageable);

	/**
	 * Metodo que accede a la base de datos para encontrar un usuario por su
	 * email
	 * 
	 * @param email
	 *            del usuario
	 * @return el usuario
	 */
	User findByEmail(String email);

	/**
	 * Metodo que accede a la base de datos para obtener la lista paginada de
	 * usuairos cuyo nombre o email coincidan con la cedena introducida por el
	 * usuario
	 * 
	 * @param pageable
	 * @param seachtext
	 * @param enSesion
	 * @return
	 */
	@Query("SELECT r FROM User r WHERE r!=?2 AND (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1))")
	Page<User> searchByNameOrEmail(Pageable pageable, String seachtext,
			User enSesion);

	/**
	 * Metodo que accede a la base de datos para obtener la lista paginada de
	 * usuairos amigos de un usuario en sesion
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("select distinct f FROM User u, IN(u.friendList) f where u=?1")
	Page<User> searchFriendsForUser(Pageable pageable, User user);

	/**
	 * Metodo que accede a la base de datos para devolver una lista paginada de
	 * todos usuarios menos el usuario en sesion
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("select u from User u where u!=?1")
	Page<User> findAllButUser(Pageable pageable, User user);

}
