package com.fernando.oliveira.reservas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.oliveira.reservas.domain.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

}
