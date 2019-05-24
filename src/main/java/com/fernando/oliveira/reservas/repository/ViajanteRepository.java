package com.fernando.oliveira.reservas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.oliveira.reservas.domain.Reserva;
import com.fernando.oliveira.reservas.domain.Viajante;

@Repository
public interface ViajanteRepository extends JpaRepository<Viajante, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Viajante obj ORDER BY obj.nome ASC")
	public List<Viajante> findAllOrderByNomeAsc();

//	public List<Viajante> search(Viajante viajante);

//	@Transactional(readOnly=true)
//	@Query("SELECT obj FROM Viajante obj WHERE obj.nome like %:nome% OR obj.email like %:email% ORDER BY obj.nome ASC")
//	List<Viajante> findViajantes(@Param("nome") String nome, @Param("email") String email);
	
	@Transactional(readOnly=true)
	List<Viajante> findByNomeContainingOrderByNome(String nome);
	
}
