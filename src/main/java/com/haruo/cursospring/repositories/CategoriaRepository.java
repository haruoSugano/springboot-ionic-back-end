package com.haruo.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haruo.cursospring.domain.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { 

}
