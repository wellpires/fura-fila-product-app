package br.com.furafila.productapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstablishmentProductDTO {

	private Long productId;
	private String productName;
	private Double unitPrice;
	private Boolean status;
	private Long imageId;
	private Long stockMinimumQuantity;
	private Long stockQuantity;

	@JsonProperty("productType")
	private EstablishmentProductTypeDTO establishmentProducTypeDTO;

	@JsonProperty("dimension")
	private EstablishmentProductDimensionDTO establishmentProductDimensionDTO;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getStockMinimumQuantity() {
		return stockMinimumQuantity;
	}

	public void setStockMinimumQuantity(Long stockMinimumQuantity) {
		this.stockMinimumQuantity = stockMinimumQuantity;
	}

	public Long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public EstablishmentProductTypeDTO getEstablishmentProducTypeDTO() {
		return establishmentProducTypeDTO;
	}

	public void setEstablishmentProducTypeDTO(EstablishmentProductTypeDTO establishmentProducTypeDTO) {
		this.establishmentProducTypeDTO = establishmentProducTypeDTO;
	}

	public EstablishmentProductDimensionDTO getEstablishmentProductDimensionDTO() {
		return establishmentProductDimensionDTO;
	}

	public void setEstablishmentProductDimensionDTO(EstablishmentProductDimensionDTO establishmentProductDimensionDTO) {
		this.establishmentProductDimensionDTO = establishmentProductDimensionDTO;
	}

}
