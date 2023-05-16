package br.com.furafila.productapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.ProductTypeDuplicityDTO;

public class ProductTypeDuplicityResponse {

	@JsonProperty("productType")
	private ProductTypeDuplicityDTO productTypeDuplicityDTO;

	public ProductTypeDuplicityResponse() {
	}

	public ProductTypeDuplicityResponse(ProductTypeDuplicityDTO productTypeDuplicityDTO) {
		this.productTypeDuplicityDTO = productTypeDuplicityDTO;
	}

	public ProductTypeDuplicityDTO getProductTypeDuplicityDTO() {
		return productTypeDuplicityDTO;
	}

	public void setProductTypeDuplicityDTO(ProductTypeDuplicityDTO productTypeDuplicityDTO) {
		this.productTypeDuplicityDTO = productTypeDuplicityDTO;
	}

}
