package com.pichincha.camsacreditoauto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;

@Repository
public interface EjecutivoRepository extends JpaRepository<EjecutivoEntity, Integer>{

	@Query("SELECT e FROM EjecutivoEntity e WHERE e.id = :id")
	Optional<EjecutivoEntity> consultarEjecutivoPorId(Integer id);
}
