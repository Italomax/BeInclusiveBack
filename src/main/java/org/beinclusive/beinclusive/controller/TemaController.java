package org.beinclusive.beinclusive.controller;

import java.util.List;

import javax.validation.Valid;

import org.beinclusive.beinclusive.model.Tema;
import org.beinclusive.beinclusive.repository.TemaRepository;
import org.beinclusive.beinclusive.utils.EnumAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping("/temas")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
    public ResponseEntity<List<Tema>> getAll(){
	    return ResponseEntity.ok(repository.findAll());	   
    }
	@GetMapping ("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable(value = "id") long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	 
	}
	@GetMapping("/tema/{avaliacao}")
	public ResponseEntity<List<Tema>> getByAvaliacao(@PathVariable EnumAvaliacao avaliacao){
		return ResponseEntity.ok(repository.findAllByAvaliacao(avaliacao));
	}
	@PostMapping
	public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema)); 
	}
	@PutMapping
	public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) 
	{
		repository.deleteById(id);
	}
}
