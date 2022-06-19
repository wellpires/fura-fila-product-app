package br.com.furafila.productapp.service;

import java.util.List;

import br.com.furafila.productapp.dto.EditProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.dto.EditProductUnitPriceDTO;

public interface ProductService {

	List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId);

	Long createProduct(NewProductDTO newProductDTO);

	void edit(EditProductDTO editProductDTO, Long productId);

	void toggleProductStatus(Long productId);

	void editUnitPrice(EditProductUnitPriceDTO newUnitPriceDTO, Long productId);

}
