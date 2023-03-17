package com.pichincha.camsacreditoauto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pichincha.camsacreditoauto.domain.entity.CreditoEntity;

@Repository
public interface CreditoRepository extends JpaRepository<CreditoEntity, Integer> {
	
	@Query("SELECT c FROM CreditoEntity c WHERE c.vehiculo.id = :idVehiculo")
	Optional<CreditoEntity> consultarInformacionAsociadaVehiculo(Integer idVehiculo);
	
	@Query("SELECT c FROM CreditoEntity c WHERE c.patio.id = :idPatio")
	List<CreditoEntity> consultarInformacionAsociadaPatio(Integer idPatio);
	
	@Query("SELECT c FROM CreditoEntity c WHERE c.cliente.id = :idCliente OR c.patio.id = :idPatio")
	Optional<CreditoEntity> consultarInformacionAsociadaClientePatio(Integer idCliente, Integer idPatio);

	@Query("SELECT c FROM CreditoEntity c WHERE c.cliente.id = :idCliente")
	List<CreditoEntity> consultarCreditoPorCliente(Integer idCliente);
	
	@Query("SELECT c FROM CreditoEntity c WHERE c.vehiculo.id = :idVehiculo")
	Optional<CreditoEntity> consultarCreditoPorVehiculo(Integer idVehiculo);

}
