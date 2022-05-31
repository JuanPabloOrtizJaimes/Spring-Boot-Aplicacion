package com.juanOrtizJaimes.springBootAplicacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juanOrtizJaimes.springBootAplicacion.dto.ChangePasswordForm;
import com.juanOrtizJaimes.springBootAplicacion.entity.User;
import com.juanOrtizJaimes.springBootAplicacion.exception.UsernameOrIdNotFound;
import com.juanOrtizJaimes.springBootAplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}
	
	private boolean checkUsernameAvaliable(User user) throws Exception {
		Optional<User> userFound=repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		
		if(user.getConfirmPassword()==null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm Password es obligatorio");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvaliable(user) && checkPasswordValid(user)) {
			String encodePassword=bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			user=repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws UsernameOrIdNotFound {
		return repository.findById(id).orElseThrow(()->new UsernameOrIdNotFound("El Id del usuario no existe"));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser=getUserById(fromUser.getId());
		mapUser(fromUser, toUser);		
		return repository.save(toUser);
	}
	
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		User user=getUserById(id);
		repository.delete(user);
	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user=getUserById(form.getId());
		
		
		if ( !isLoggedUserADMIN() && !user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception ("Current Password invalido.");
		}

		if( user.getPassword().equals(form.getNewPassword())) {
			throw new Exception ("Nuevo debe ser diferente al password actual.");
		}

		if( !form.getNewPassword().equals(form.getNewConfirmPassword())) {
			

			throw new Exception ("Nuevo Password y Current Password no coinciden.");
		}
		
		
		String encodePassword=bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setPassword(encodePassword);
		return repository.save(user);
		
	}
	
	public boolean isLoggedUserADMIN(){
		 return loggedUserHasRole("ROLE_ADMIN");
		}

		public boolean loggedUserHasRole(String role) {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 UserDetails loggedUser = null;
		 Object roles = null; 
		 if (principal instanceof UserDetails) {
		  loggedUser = (UserDetails) principal;
		 
		  roles = loggedUser.getAuthorities().stream()
		    .filter(x -> role.equals(x.getAuthority() ))      
		    .findFirst().orElse(null); //loggedUser = null;
		 }
		 return roles != null ?true :false;
		}

	
}
