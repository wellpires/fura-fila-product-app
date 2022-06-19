package br.com.furafila.productapp.dao;

import java.util.List;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;

public interface ProductDAO {

	List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId);

}
