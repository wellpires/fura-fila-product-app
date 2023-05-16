package br.com.furafila.productapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.furafila.productapp.util.Messages;

public class NewProductTypeDTO {

	@NotBlank(message = Messages.NEW_PRODUCT_TYPE_NAME_IS_REQUIRED)
	@Size(min = 2, max = 20, message = Messages.NEW_PRODUCT_TYPE_NAME_IS_NOT_VALID)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
