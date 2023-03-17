package com.pichincha.camsacreditoauto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.ClientePatioEntity;

@Repository
public interface ClientePatioRepository extends JpaRepository<ClientePatioEntity, Integer>{

	@Query("SELECT c FROM ClientePatioEntity c WHERE c.id = :id")
	Optional<ClientePatioEntity> consultarClientePartioPorId(Integer id);
}
