package com.fernando.oliveira.reservas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Lancamento obj INNER JOIN obj.reserva res WHERE res.situacaoReserva <> 'CANCELADO' AND obj.situacaoPagamento = 'PENDENTE' ORDER BY obj.dataLancamento")
	List<Lancamento> findLancamentosPendentes();

}
