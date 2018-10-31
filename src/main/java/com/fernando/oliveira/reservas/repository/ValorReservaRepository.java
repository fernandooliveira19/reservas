package com.fernando.oliveira.reservas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.oliveira.reservas.domain.ValorReserva;

@Repository
public interface ValorReservaRepository extends JpaRepository<ValorReserva, Integer>{

}
