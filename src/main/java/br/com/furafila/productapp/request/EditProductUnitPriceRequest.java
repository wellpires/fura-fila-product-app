package br.com.furafila.productapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.EditProductUnitPriceDTO;
import br.com.furafila.productapp.util.Messages;

public class EditProductUnitPriceRequest {

	@JsonProperty("product")
	@NotNull(message = Messages.EDIT_PRICE_UNIT_INFORMATION_IS_REQUIRED)
	@Valid
	private EditProductUnitPriceDTO newUnitPriceDTO;

	public EditProductUnitPriceDTO getNewUnitPriceDTO() {
		return newUnitPriceDTO;
	}

	public void setNewUnitPriceDTO(EditProductUnitPriceDTO newUnitPriceDTO) {
		this.newUnitPriceDTO = newUnitPriceDTO;
	}

}
