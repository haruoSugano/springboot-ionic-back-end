package com.haruo.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haruo.cursospring.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Integer>{

}
