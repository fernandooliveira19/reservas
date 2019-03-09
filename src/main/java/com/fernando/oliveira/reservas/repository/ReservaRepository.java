package com.fernando.oliveira.reservas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj WHERE obj.situacaoReserva NOT IN ('FINALIZADO','CANCELADO') ORDER BY obj.dataEntrada")
	List<Reserva> findProximasReservas();
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Reserva obj ORDER BY obj.dataEntrada asc")
	List<Reserva> findAllOrderByDataEntradaAsc();

}
