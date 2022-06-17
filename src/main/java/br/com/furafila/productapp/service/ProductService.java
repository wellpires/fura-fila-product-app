package br.com.furafila.productapp.service;

import java.util.List;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.NewProductDTO;

public interface ProductService {

	List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId);

	Long createProduct(NewProductDTO newProductDTO);

}
