package br.com.furafila.productapp.builder;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;

public class EstablishmentProductDTOBuilder {

	private Long productId;
	private String productName;
	private Double unitPrice;
	private Boolean status;
	private Long imageId;
	private Long stockminimumQuantity;
	private Long stockQuantity;
	private EstablishmentProductTypeDTO establishmentProductTypeDTO;
	private EstablishmentProductDimensionDTO establishmentProductDimensionDTO;

	public EstablishmentProductDTOBuilder productId(Long productId) {
		this.productId = productId;
		return this;
	}

	public EstablishmentProductDTOBuilder productName(String productName) {
		this.productName = productName;
		return this;
	}

	public EstablishmentProductDTOBuilder unitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		return this;
	}

	public EstablishmentProductDTOBuilder status(Boolean status) {
		this.status = status;
		return this;
	}

	public EstablishmentProductDTOBuilder imageId(Long imageId) {
		this.imageId = imageId;
		return this;
	}

	public EstablishmentProductDTOBuilder stockMinimumQuantity(Long stockminimumQuantity) {
		this.stockminimumQuantity = stockminimumQuantity;
		return this;
	}

	public EstablishmentProductDTOBuilder stockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
		return this;
	}

	public EstablishmentProductDTOBuilder establishmentProductTypeDTO(
			EstablishmentProductTypeDTO establishmentProductTypeDTO) {
		this.establishmentProductTypeDTO = establishmentProductTypeDTO;
		return this;
	}

	public EstablishmentProductDTOBuilder establishmentProductDimensionDTO(
			EstablishmentProductDimensionDTO establishmentProductDimensionDTO) {
		this.establishmentProductDimensionDTO = establishmentProductDimensionDTO;
		return this;
	}

	public EstablishmentProductDTO build() {
		EstablishmentProductDTO establishmentProductDTO = new EstablishmentProductDTO();
		establishmentProductDTO.setProductId(productId);
		establishmentProductDTO.setProductName(productName);
		establishmentProductDTO.setUnitPrice(unitPrice);
		establishmentProductDTO.setStatus(status);
		establishmentProductDTO.setImageId(imageId);
		establishmentProductDTO.setStockMinimumQuantity(stockminimumQuantity);
		establishmentProductDTO.setStockQuantity(stockQuantity);
		establishmentProductDTO.setEstablishmentProducTypeDTO(establishmentProductTypeDTO);
		establishmentProductDTO.setEstablishmentProductDimensionDTO(establishmentProductDimensionDTO);
		return establishmentProductDTO;
	}

}
