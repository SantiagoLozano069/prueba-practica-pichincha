package com.pichincha.camsacreditoauto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer>{

	@Query("SELECT m FROM MarcaEntity m WHERE m.id = :id")
	Optional<MarcaEntity> consultarMarcaPorId(Integer id);
}
