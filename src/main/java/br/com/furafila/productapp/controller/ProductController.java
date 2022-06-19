package br.com.furafila.productapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.furafila.productapp.controller.resource.ProductResource;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.request.EditProductRequest;
import br.com.furafila.productapp.request.NewProductRequest;
import br.com.furafila.productapp.request.EditProductUnitPriceRequest;
import br.com.furafila.productapp.response.EstablishmentProductResponse;
import br.com.furafila.productapp.response.NewProductResponse;
import br.com.furafila.productapp.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController implements ProductResource {

	@Autowired
	private ProductService productService;

	@Override
	@GetMapping(path = "/establishments/{establishmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstablishmentProductResponse> listEstablishmentProducts(
			@PathVariable("establishmentId") Long establishmentId) {
		List<EstablishmentProductDTO> establishmentProductDTOs = productService
				.listEstablishmentProducts(establishmentId);
		return ResponseEntity.ok(new EstablishmentProductResponse(establishmentProductDTOs));
	}

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NewProductResponse> createProduct(@RequestBody @Valid NewProductRequest newProductRequest) {

		Long productId = productService.createProduct(newProductRequest.getNewProductDTO());

		return ResponseEntity.ok(new NewProductResponse(productId));
	}

	@Override
	@PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@RequestBody @Valid EditProductRequest editProductRequest,
			@PathVariable("productId") Long productId) {

		productService.edit(editProductRequest.getEditProductDTO(), productId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<Void> toogleProductStatus(@PathVariable("productId") Long productId) {

		productService.toggleProductStatus(productId);

		return ResponseEntity.noContent().build();
	}

	// TODO CHANGE TO PATCH MAPPING
	@Override
	@PutMapping(path = "/{productId}/unit-price", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editUnitPrice(@RequestBody EditProductUnitPriceRequest newProductUnitPriceRequest,
			@PathVariable("productId") Long productId) {

		productService.editUnitPrice(newProductUnitPriceRequest.getNewUnitPriceDTO(), productId);

		return ResponseEntity.noContent().build();
	}

}
