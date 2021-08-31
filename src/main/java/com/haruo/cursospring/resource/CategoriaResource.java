package com.haruo.cursospring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired // Acessando o servico
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Na busca de categoria é possível de duas formas: pelo id ou por categorias (browser)
	public ResponseEntity<?> find(@PathVariable Integer id) { // Este método recebe o id, para o spring identificar/ é necessário adicionar @PathVariable para a indentificação
		// ResponseEntity: Encapsula e armazena várias informações de uma resposta HTTP para o serviço REST.
		// <?> significa que pode ser de qualquer tipo.
		
		Categoria categoria = service.buscar(id);
		/**
		 * Retornando em formato JSON
		 */
		return ResponseEntity.ok().body(categoria); // ResponseEntity.ok(): A resposta ocorreu com sucesso   .body(categoria): Esta resposta tem o corpo o objeto que buscou
	}
}
