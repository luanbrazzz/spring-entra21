package com.banco.avaliacao.repository;

import com.banco.avaliacao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
