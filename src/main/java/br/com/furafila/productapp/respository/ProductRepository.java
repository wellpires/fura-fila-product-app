package br.com.furafila.productapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.furafila.productapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
