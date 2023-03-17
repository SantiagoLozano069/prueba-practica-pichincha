package com.pichincha.camsacreditoauto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Integer> {

	@Query("SELECT v FROM VehiculoEntity v WHERE v.id = :id")
	Optional<VehiculoEntity> consultarVehiculoPorId(Integer id);
	
	@Query("SELECT v FROM VehiculoEntity v WHERE v.placa = :placa")
	Optional<VehiculoEntity> consultarVehiculoPorPlaca(String placa);
	
	@Query("SELECT v FROM VehiculoEntity v WHERE v.marca.id = :marca")
	List<VehiculoEntity> consultarVehiculoPorMarca(Integer marca);
	
	@Query("SELECT v FROM VehiculoEntity v WHERE v.modelo = :modelo")
	List<VehiculoEntity> consultarVehiculoPorModelo(String modelo);
}
