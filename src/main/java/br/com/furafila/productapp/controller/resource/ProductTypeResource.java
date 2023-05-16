package br.com.furafila.productapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.productapp.request.EditProductTypeRequest;
import br.com.furafila.productapp.response.NewProductTypeRequest;
import br.com.furafila.productapp.response.ProductTypeDuplicityResponse;
import br.com.furafila.productapp.response.ProductTypeResponse;

public interface ProductTypeResource {

	ResponseEntity<ProductTypeResponse> listProductTypes(Boolean activeOnly);

	ResponseEntity<Void> edit(EditProductTypeRequest editProductTypeRequest, Long productTypeId);

	ResponseEntity<Void> toggleStatus(Long productTypeId);

	ResponseEntity<Void> create(NewProductTypeRequest newProductTypeRequest);

	ResponseEntity<ProductTypeDuplicityResponse> checkProductTypeDuplicity(String productTypeName);

}
