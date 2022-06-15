package br.com.furafila.productapp.function;

import java.util.function.Function;

import br.com.furafila.productapp.builder.EstablishmentProductDTOBuilder;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;
import br.com.furafila.productapp.model.Product;
import br.com.furafila.productapp.model.ProductStock;

public class Product2EstablishmentProductDTOFunction implements Function<Product, EstablishmentProductDTO> {

	@Override
	public EstablishmentProductDTO apply(Product product) {

		EstablishmentProductTypeDTO establishmentProductTypeDTO = new EstablishmentProductTypeDTO();
		establishmentProductTypeDTO.setId(product.getProductType().getId());
		establishmentProductTypeDTO.setName(product.getProductType().getName());

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO.setId(product.getDimension().getId());
		establishmentProductDimensionDTO.setHeight(product.getDimension().getHeight());
		establishmentProductDimensionDTO.setWidth(product.getDimension().getWidth());
		establishmentProductDimensionDTO.setLength(product.getDimension().getLength());

		Long stockQuantity = product.getProductStocks().stream().findFirst().orElseGet(ProductStock::new)
				.getStockQuantity();

		return new EstablishmentProductDTOBuilder().productId(product.getId()).productName(product.getName())
				.unitPrice(product.getUnitPrice()).status(product.getStatus())
				.stockMinimumQuantity(product.getMinimumQuantity()).imageId(product.getImage().getId())
				.stockQuantity(stockQuantity).establishmentProductTypeDTO(establishmentProductTypeDTO)
				.establishmentProductDimensionDTO(establishmentProductDimensionDTO).build();
	}

}
