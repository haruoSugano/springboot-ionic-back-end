package com.haruo.cursospring.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.repositories.CategoriaRepository;
import com.haruo.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository; 
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		
		return categoria.orElseThrow( () -> new ObjectNotFoundException (
					"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()
				)); 
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null); // Objeto a ser inserido, precisa ter o id nulo.
		return repository.save(categoria);
	}
}
