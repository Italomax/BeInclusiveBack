package org.beinclusive.beinclusive.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.beinclusive.beinclusive.model.Usuario;
import org.beinclusive.beinclusive.model.UsuarioLogin;
import org.beinclusive.beinclusive.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService 
{

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if (repository.findByEmail(usuario.getEmail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(repository.save(usuario));
	}

	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) 
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		
		if (usuario.isPresent())
		{
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())); 
			{
			
			String auth = user.get().getEmail() + ":" + user.get().getSenha();
			
			byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			
			String authHeader = "Basic " + new String(encodeAuth);
			
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome());
			
			return user;
		
		}
	
	}
	
	return null;
			
   }
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);

		return senhaEncoder;
	}
 
}
