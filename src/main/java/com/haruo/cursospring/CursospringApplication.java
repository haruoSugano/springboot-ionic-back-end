package com.haruo.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.haruo.cursospring.domain.Categoria;
import com.haruo.cursospring.repositories.CategoriaRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner{ // CommandLineRunner, ao executar a aplicacao permite que, o metódo inicie com uma ação. 
	
	// Criando uma dependência:
	@Autowired
	private CategoriaRepository categoriaRepository; // É o responsável  por salvar as categorias

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	// Método criado pelo CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		Categoria categoria1 = new Categoria(null, "Informatica"); // BD gera ID automaticamente. por isso é null.
		Categoria categoria2 = new Categoria(null, "Escritorio");
		
		// Salvando categoria ao banco:
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2)); // Chamando categoriaRepository e uma função para salvar = saveAll() 
		// Arrays.asList() criar uma lista automatico.
	}
}
