package com.banco.avaliacao.repository;

import com.banco.avaliacao.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaBancaria, Long> {
}
