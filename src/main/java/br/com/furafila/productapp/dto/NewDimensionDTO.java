package br.com.furafila.productapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.furafila.productapp.util.Messages;
import br.com.furafila.productapp.validator.order.FirstOrder;
import br.com.furafila.productapp.validator.order.SecondOrder;

@GroupSequence({ NewDimensionDTO.class, FirstOrder.class, SecondOrder.class })
public class NewDimensionDTO {

	@NotNull(message = Messages.NEW_DIMENSION_HEIGHT_IS_REQUIRED)
	@Min(value = 1, message = Messages.NEW_DIMENSION_HEIGHT_IS_NOT_VALID)
	private Integer height;

	@NotNull(message = Messages.NEW_DIMENSION_WIDTH_IS_REQUIRED)
	@Min(value = 1, message = Messages.NEW_DIMENSION_WIDTH_IS_NOT_VALID)
	private Integer width;

	@NotNull(message = Messages.NEW_DIMENSION_LENGTH_IS_REQUIRED)
	@Min(value = 1, message = Messages.NEW_DIMENSION_LENGTH_IS_NOT_VALID)
	private Integer length;

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
