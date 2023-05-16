package br.com.furafila.productapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.furafila.productapp.util.Messages;
import br.com.furafila.productapp.validator.order.FirstOrder;
import br.com.furafila.productapp.validator.order.SecondOrder;

@GroupSequence({ EditProductTypeDTO.class, FirstOrder.class, SecondOrder.class })
public class EditProductTypeDTO {

	@NotBlank(message = Messages.EDIT_PRODUCT_TYPE_NAME_IS_REQUIRED, groups = FirstOrder.class)
	@Size(min = 4, max = 50, message = Messages.EDIT_PRODUCT_TYPE_NAME_IS_REQUIRED_LENGTH_IS_NOT_VALID, groups = SecondOrder.class)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
