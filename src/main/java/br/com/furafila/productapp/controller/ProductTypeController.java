package br.com.furafila.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.furafila.productapp.controller.resource.ProductTypeResource;
import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.request.EditProductTypeRequest;
import br.com.furafila.productapp.response.ProductTypeResponse;
import br.com.furafila.productapp.service.ProductTypeService;

@RestController
@RequestMapping("product-types")
public class ProductTypeController implements ProductTypeResource {

	@Autowired
	private ProductTypeService productTypeService;

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductTypeResponse> listProductTypes(
			@RequestParam(value = "active-only", defaultValue = "false") Boolean activeOnly) {
		List<ProductTypeDTO> productTypeDTOs = productTypeService.listProductTypes(activeOnly);
		return ResponseEntity.ok(new ProductTypeResponse(productTypeDTOs));
	}

	// TODO CHANGE TO PATCH
	@Override
	@PutMapping(path = "/{productTypeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@RequestBody EditProductTypeRequest editProductTypeRequest,
			@PathVariable("productTypeId") Long productTypeId) {

		productTypeService.edit(editProductTypeRequest.getEditProductTypeDTO(), productTypeId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping(path = "/{productTypeId}")
	public ResponseEntity<Void> toggleStatus(@PathVariable("productTypeId") Long productTypeId) {

		productTypeService.toggleStatus(productTypeId);

		return ResponseEntity.noContent().build();
	}

}
