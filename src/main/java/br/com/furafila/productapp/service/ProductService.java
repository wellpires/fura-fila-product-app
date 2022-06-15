package br.com.furafila.productapp.service;

import java.util.List;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;

public interface ProductService {

	List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId);

}
