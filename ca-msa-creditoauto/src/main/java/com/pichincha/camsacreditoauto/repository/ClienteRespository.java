package com.pichincha.camsacreditoauto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;

@Repository
public interface ClienteRespository extends JpaRepository<ClienteEntity, Integer> {

	@Query("SELECT c FROM ClienteEntity c WHERE c.id = :id")
	Optional<ClienteEntity> consultarClientePorId(Integer id);
}
