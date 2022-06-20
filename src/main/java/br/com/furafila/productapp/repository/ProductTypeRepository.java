package br.com.furafila.productapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.furafila.productapp.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	List<ProductType> findByStatusIsTrue();

}
