package br.com.furafila.productapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.productapp.response.EstablishmentProductResponse;

public interface ProductResource {

	ResponseEntity<EstablishmentProductResponse> listEstablishmentProducts(Long establishmentId);

}
