package com.examen.pedidos.repository;

import com.examen.pedidos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//hereda métodos JpaRepository(findAll,save,findById..
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

