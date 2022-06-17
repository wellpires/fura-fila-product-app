package br.com.furafila.productapp.service.impl;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.model.Dimension;
import br.com.furafila.productapp.model.Image;
import br.com.furafila.productapp.model.Product;
import br.com.furafila.productapp.model.ProductStock;
import br.com.furafila.productapp.model.ProductType;
import br.com.furafila.productapp.respository.ProductRepository;
import br.com.furafila.productapp.service.DimensionService;
import br.com.furafila.productapp.service.ProductService;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@InjectMocks
	private ProductService productService = new ProductServiceImpl();

	@Mock
	private ProductRepository productRepository;

	@Mock
	private DimensionService dimensionService;

	@Test
	void shouldListEstablishmentProducts() {

		Product product1 = new Product();
		product1.setId(1l);
		product1.setName("Name Test 1");
		product1.setUnitPrice(25.0);
		product1.setMinimumQuantity(10l);
		product1.setStatus(Boolean.TRUE);

		Image image1 = new Image();
		image1.setId(2l);
		product1.setImage(image1);

		ProductStock productStock1 = new ProductStock();
		productStock1.setStockQuantity(10l);
		product1.setProductStocks(Arrays.asList(productStock1));

		ProductType productType1 = new ProductType();
		productType1.setId(1l);
		productType1.setName("Product Type 1");
		product1.setProductType(productType1);

		Dimension dimension1 = new Dimension();
		dimension1.setId(4l);
		dimension1.setHeight(23);
		dimension1.setWidth(76);
		dimension1.setLength(98);
		product1.setDimension(dimension1);

		Product product2 = new Product();
		product2.setId(2l);
		product2.setName("Name Test 2");
		product2.setUnitPrice(50.0);
		product2.setMinimumQuantity(30l);
		product2.setStatus(Boolean.TRUE);

		Image image2 = new Image();
		image2.setId(3l);
		product2.setImage(image2);

		ProductStock productStock2 = new ProductStock();
		productStock2.setStockQuantity(19l);
		product2.setProductStocks(Arrays.asList(productStock2));

		ProductType productType2 = new ProductType();
		productType2.setId(7l);
		productType2.setName("Product Type 2");
		product2.setProductType(productType2);

		Dimension dimension2 = new Dimension();
		dimension2.setId(7l);
		dimension2.setHeight(32);
		dimension2.setWidth(98);
		dimension2.setLength(10);
		product2.setDimension(dimension2);

		when(productRepository.listEstablishmentProducts(anyLong())).thenReturn(Arrays.asList(product1, product2));

		List<EstablishmentProductDTO> listEstablishmentProductDTOs = productService.listEstablishmentProducts(10l);

		assertThat(listEstablishmentProductDTOs, hasSize(2));

		for (EstablishmentProductDTO establishmentProductDTO : listEstablishmentProductDTOs) {

			assertThat(establishmentProductDTO.getProductId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getProductName(), not(blankOrNullString()));
			assertThat(establishmentProductDTO.getUnitPrice(), allOf(not(nullValue()), not(zeroValue())));
			assertNotNull(establishmentProductDTO.getStatus());
			assertThat(establishmentProductDTO.getImageId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getStockMinimumQuantity(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getStockQuantity(), allOf(not(nullValue()), not(zeroValue())));

			assertThat(establishmentProductDTO.getEstablishmentProducTypeDTO().getId(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProducTypeDTO().getName(), not(blankOrNullString()));

			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getId(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getHeight(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getWidth(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getLength(),
					allOf(not(nullValue()), not(zeroValue())));

		}

	}

	@Test
	public void shouldCreateProduct() {

		Product product = new Product();
		product.setId(10l);
		when(productRepository.save(any())).thenReturn(product);

		when(dimensionService.create(any())).thenReturn(10l);

		NewProductDTO newProductDTO = new NewProductDTO();
		newProductDTO.setProductName("Product Name 1");
		newProductDTO.setImageId(1l);
		newProductDTO.setMinimumStockQuantity(10l);
		newProductDTO.setProductTypeId(12l);

		NewDimensionDTO newDimensionDTO = new NewDimensionDTO();
		newDimensionDTO.setHeight(10);
		newDimensionDTO.setLength(5);
		newDimensionDTO.setWidth(32);
		newProductDTO.setNewDimensionDTO(newDimensionDTO);

		productService.createProduct(newProductDTO);

		verify(productRepository, times(1)).save(any());

	}

}
