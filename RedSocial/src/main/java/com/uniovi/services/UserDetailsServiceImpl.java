package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * Servicio basado en UserDetailsService que permite gestionar la autenticacion
 * y acceso de usuarios de la aplicacion
 * 
 * @author UO231379, UO239718
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;

	/**
	 * Metodo que obtienen el usaurio de la aplicion y crea un userDetails con
	 * su email y contraseña
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);
	}
}
