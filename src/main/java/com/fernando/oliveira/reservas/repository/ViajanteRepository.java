package com.fernando.oliveira.reservas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Viajante;

@Repository
public interface ViajanteRepository extends JpaRepository<Viajante, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Viajante obj ORDER BY obj.nome ASC")
	public List<Viajante> findAllOrderByNomeAsc();
}
