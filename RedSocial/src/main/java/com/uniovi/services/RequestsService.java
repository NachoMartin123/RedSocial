package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;
import com.uniovi.repositories.RequestsRepository;

@Service
public class RequestsService {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private RequestsRepository requestsRepository;
	
	public Page<Request> getRequests(Pageable pageable){
		Page<Request> requests = requestsRepository.findAll(pageable);
		return requests;
	}

	
	
//	public Request getMark(Long id){
////		Cada vez que visitamos los detalles de una nota /getMark(Long id) vamos a almacenar la
////		información de la nota en sesión
//		Set<Request> consultedList = (Set<Request>) httpSession.getAttribute("consultedList");
//		if ( consultedList == null ) {
//			consultedList = new HashSet<Request>();
//		}
//		Request requestObtained = requestsRepository.findById(id);
//		consultedList.add(requestObtained);
//		httpSession.setAttribute("consultedList", consultedList);//la volvemos a guardar en sesion
//		return requestObtained;
//	}
//	
	public void addRequest(Request request){
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		requestsRepository.save(request);
	}
	
	public void deleteRequest(Long id){
		requestsRepository.deleteById(id);
	}
	
	public boolean existsRequest(User target, User maker) {
		if(requestsRepository.getRequest(target, maker)==null)
			return false;
		return true;
	}



	public void setRequestAccepted(boolean accepted, Long id) {
		requestsRepository.setAccepted(accepted, id);
		
	}

	public Page<Request> getRequestsForUser(Pageable pageable, User userEnSesion) {
		Page<Request> requests = requestsRepository.findRequestForTarget(pageable, userEnSesion);
		return requests;
	}
	
	public Request findRequestByTargetAndMaker(User target, User maker) {
		return requestsRepository.findByTargetAndMaker(target, maker);
	}
	
	public Request findRequestById(Long id) {
		return requestsRepository.findById(id).orElse(null);
	}
	
	
}
