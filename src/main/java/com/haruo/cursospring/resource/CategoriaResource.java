package com.haruo.cursospring.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { 
		
		Categoria categoria = service.find(id);

		return ResponseEntity.ok().body(categoria); 
	}
	
	/**
	 * Inserindo uma categoria ao BD
	 * @param categoria
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) { // RequestBody -> converte objeto JSON em objeto java
		categoria = service.insert(categoria); // vai inserir este objeto no banco de dados
		// Fornecendo a URI 
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()  //localhost:8080/categorias (fromCurrentRequest() ) 
				.path("/{id}") // mostrando o caminho   localhost:8080/categoria/ path("/{id}")
				.buildAndExpand(categoria.getId())  // atribuir o valor ao path(id) -> buildAndExpand....
				.toUri(); // convertendo para uri
		return ResponseEntity.created(uri).build(); // created -> gera o código 201 -> recebendo o uri como argumento -> chamando a build() para gerar a resposta.
	}
	
	/**
	 * Atualizando uma categoria
	 * @param categoria
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id){
		categoria.setId(id);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Deletando uma categoria
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) { // Retornar uma resposta com corpo vazio(void)
		 service.delete(id);
		 return ResponseEntity.noContent().build();
	}
}
