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

@Service
public class PublicationsService {
	
	@Autowired 
	private PublicationsRepository publicationsRepository;
	
	@PostConstruct
	public void init() {
		
	}

	public void addPublication(Publication publication) {
		publicationsRepository.save(publication);
	}
	
	public Page<Publication> getPublicationsForUser(Pageable pageable, User user) {
		Page<Publication> publications = new PageImpl<Publication>(new LinkedList<Publication>());
		publications = publicationsRepository.findAllByUser(user,pageable);
		
		return publications;
	}
	
	public Page<Publication> getPublications(Pageable pageable) {
		Page<Publication> publications = publicationsRepository.findAll(pageable);
		return publications; 
	}
}
