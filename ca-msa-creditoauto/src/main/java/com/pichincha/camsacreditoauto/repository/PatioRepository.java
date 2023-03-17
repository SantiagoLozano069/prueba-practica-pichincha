package com.pichincha.camsacreditoauto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;

@Repository
public interface PatioRepository extends JpaRepository<PatioEntity, Integer> {
	
	@Query("SELECT p FROM PatioEntity p WHERE p.id = :id")
	Optional<PatioEntity> consultarPatioPorId(Integer id);

}
