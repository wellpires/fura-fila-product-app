package br.com.furafila.productapp.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.furafila.productapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product p inner join p.productType pt inner join p.dimension d inner join p.image i inner join ProductStock ps on ps.productId = p.id inner join ps.stock s where pt.status = 1 and s.establishmentId = :establishmentId")
	List<Product> listEstablishmentProducts(@Param("establishmentId") Long establishmentId);

}
