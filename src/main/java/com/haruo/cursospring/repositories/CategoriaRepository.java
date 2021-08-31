package com.haruo.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haruo.cursospring.domain.Categoria;
/**
 * É responsável por acessar o banco de dados, para realizar consultas na tabela categoria
 * busca, salvar, alterar
 * @author haruo
 *
 */
@Repository // Responsável para realizar a consulta                     tipo  atributo identificador
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { // JpaRepository: capaz de acessar, atráves dos dados que passar.

}
