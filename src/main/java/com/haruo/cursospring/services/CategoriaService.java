package com.haruo.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.repositories.CategoriaRepository;
import com.haruo.cursospring.services.exceptions.DataIntegrityException;
import com.haruo.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository; 
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		
		return categoria.orElseThrow( () -> new ObjectNotFoundException (
					"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()
				)); 
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null); // Objeto a ser inserido, precisa ter o id nulo.
		return repository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId()); // Faz a busca pelo id, se caso não exister ele trata a excessão
		return repository.save(categoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
}
