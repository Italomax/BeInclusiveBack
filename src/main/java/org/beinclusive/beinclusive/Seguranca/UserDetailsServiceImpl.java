package org.beinclusive.beinclusive.Seguranca;

import java.util.Optional;

import org.beinclusive.beinclusive.model.Usuario;
import org.beinclusive.beinclusive.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> user = usuarioRepository.findByEmail(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}
	

}
