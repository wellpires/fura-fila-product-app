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

@GroupSequence({ EditProductDTO.class, FirstOrder.class, SecondOrder.class })
public class EditProductDTO {

	@NotBlank(message = Messages.EDIT_PRODUCT_NAME_REQUIRED, groups = FirstOrder.class)
	@Size(min = 2, max = 50, message = Messages.EDIT_PRODUCT_NAME_NOT_VALID, groups = SecondOrder.class)
	private String name;

	@NotNull(message = Messages.EDIT_PRODUCT_STOCK_MINIMUM_QUANTITY_IS_REQUIRED, groups = FirstOrder.class)
	@Min(value = 1, message = Messages.EDIT_PRODUCT_STOCK_MINIMUM_QUANTITY_IS_NOT_VALID, groups = SecondOrder.class)
	private Long minimumStockQuantity;

	@NotNull(message = Messages.EDIT_PRODUCT_TYPE_IS_REQUIRED, groups = FirstOrder.class)
	@Min(value = 1, message = Messages.EDIT_PRODUCT_TYPE_IS_NOT_VALID, groups = SecondOrder.class)
	private Long productTypeId;

	@JsonProperty("dimension")
	@NotNull(message = Messages.EDIT_PRODUCT_DIMENSION_INFORMATION_REQUIRED, groups = FirstOrder.class)
	@Valid
	private EditDimensionDTO editDimensionDTO;

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

	public EditDimensionDTO getEditDimensionDTO() {
		return editDimensionDTO;
	}

	public void setEditDimensionDTO(EditDimensionDTO editDimensionDTO) {
		this.editDimensionDTO = editDimensionDTO;
	}

}
