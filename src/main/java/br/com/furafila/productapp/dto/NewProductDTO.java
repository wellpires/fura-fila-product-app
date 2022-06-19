package br.com.furafila.productapp.dto;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.util.Messages;
import br.com.furafila.productapp.validator.order.FirstOrder;
import br.com.furafila.productapp.validator.order.SecondOrder;

@GroupSequence({ NewProductDTO.class, FirstOrder.class, SecondOrder.class })
public class NewProductDTO {

	@NotBlank(message = Messages.NEW_PRODUCT_NAME_IS_REQUIRED, groups = FirstOrder.class)
	@Size(min = 4, max = 50, message = Messages.NEW_PRODUCT_NAME_LENGTH_IS_NOT_VALID, groups = SecondOrder.class)
	private String name;

	@NotNull(message = Messages.NEW_MINIMUM_STOCK_QUANTITY_IS_REQUIRED, groups = FirstOrder.class)
	@Min(value = 1, message = Messages.NEW_MINIMUM_STOCK_QUANTITY_IS_NOT_VALID, groups = SecondOrder.class)
	private Long minimumStockQuantity;

	@NotNull(message = Messages.NEW_PRODUCT_TYPE_ID_IS_REQUIRED, groups = FirstOrder.class)
	@Min(value = 1, message = Messages.NEW_PRODUCT_TYPE_ID_IS_NOT_VALID, groups = SecondOrder.class)
	private Long productTypeId;

	@NotNull(message = Messages.NEW_IMAGE_ID_IS_REQUIRED, groups = FirstOrder.class)
	@Min(value = 1, message = Messages.NEW_IMAGE_ID_IS_NOT_VALID, groups = SecondOrder.class)
	private Long imageId;

	@JsonProperty("dimension")
	@Valid
	@NotNull(message = Messages.NEW_DIMENSION_INFORMATION_IS_REQUIRED, groups = FirstOrder.class)
	private NewDimensionDTO newDimensionDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMinimumStockQuantity() {
		return minimumStockQuantity;
	}

	public void setMinimumStockQuantity(Long minimumStockQuantity) {
		this.minimumStockQuantity = minimumStockQuantity;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public NewDimensionDTO getNewDimensionDTO() {
		return newDimensionDTO;
	}

	public void setNewDimensionDTO(NewDimensionDTO newDimensionDTO) {
		this.newDimensionDTO = newDimensionDTO;
	}

}
