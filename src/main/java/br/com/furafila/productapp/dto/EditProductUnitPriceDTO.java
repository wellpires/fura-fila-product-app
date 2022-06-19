package br.com.furafila.productapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import br.com.furafila.productapp.util.Messages;
import br.com.furafila.productapp.validator.order.FirstOrder;
import br.com.furafila.productapp.validator.order.SecondOrder;

@GroupSequence({ EditProductUnitPriceDTO.class, FirstOrder.class, SecondOrder.class })
public class EditProductUnitPriceDTO {

	@NotNull(message = Messages.EDIT_PRICE_UNIT_IS_REQUIRED)
	@DecimalMin(value = "0.1", message = Messages.EDIT_PRICE_UNIT_IS_NOT_VALID)
	private Double unitPrice;

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
