package br.com.furafila.productapp.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.ProductTypeDTO;

public class ProductTypeResponse {

	@JsonProperty("productTypes")
	private List<ProductTypeDTO> productTypeDTOs;

	public ProductTypeResponse() {
	}

	public ProductTypeResponse(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

	public List<ProductTypeDTO> getProductTypeDTOs() {
		return productTypeDTOs;
	}

	public void setProductTypeDTOs(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

}
