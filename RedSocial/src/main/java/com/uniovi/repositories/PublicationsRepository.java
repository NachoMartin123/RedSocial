package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

public interface PublicationsRepository extends CrudRepository<Publication,Long>{

	Page<Publication> findAll(Pageable pageable);
	
	@Query("SELECT p FROM Publication p WHERE p.user = ?1 ")
	Page<Publication> findAllByUser(User user,Pageable pageable);
}
