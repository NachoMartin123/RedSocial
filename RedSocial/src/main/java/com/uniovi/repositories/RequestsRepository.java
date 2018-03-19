package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;

public interface RequestsRepository extends CrudRepository<Request, Long> {

	@Query("SELECT r FROM Request r WHERE r.userMaker = ?2 ORDER BY r.id ASC ")
	Page<Request> findByUserRequester(Pageable pageable, Long userMaker);

	@Query("SELECT r FROM Request r WHERE r.userTarget=?1 AND r.userMaker=?2")
	Request getRequest(User userTarget, User userMaker);

	Page<Request> findAll(Pageable pageable);

	@Modifying
	@Transactional
	@Query("UPDATE Request SET accepted=?1 WHERE id=?2")
	void setAccepted(boolean accepted, Long id);

	@Query("SELECT r from Request r WHERE r.userTarget =?1")
	Page<Request> findRequestForTarget(Pageable pageable, User userTarget);

	@Query("SELECT r FROM Request r WHERE r.userTarget=?1 AND r.userMaker=?2")
	Request findByTargetAndMaker(User target, User maker);
}
