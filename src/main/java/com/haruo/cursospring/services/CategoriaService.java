package com.haruo.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.repositories.CategoriaRepository;
/**
 * Serviço que oferece consulta do tipo categoria
 * @author haruo
 *
 */
@Service
public class CategoriaService {

	@Autowired // servico acessa o repository
	private CategoriaRepository repository; // Chamar a operação que faz o acesso a dados
	/**
	 * Buscar categoria por id:
	 * @param id
	 * @return
	 */
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id); // O Objeto retorna o tipo optional do tipo que precisar.
		
		return categoria.orElse(null); // Se encontrado retorna o objeto, se não retorna nulo.
	}
}
