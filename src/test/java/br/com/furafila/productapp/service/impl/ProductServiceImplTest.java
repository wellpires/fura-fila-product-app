package br.com.furafila.productapp.service.impl;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.furafila.productapp.dao.ProductDAO;
import br.com.furafila.productapp.dto.EditDimensionDTO;
import br.com.furafila.productapp.dto.EditProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;
import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.exception.ProductNotFoundException;
import br.com.furafila.productapp.model.Dimension;
import br.com.furafila.productapp.model.Product;
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

	@Mock
	private ProductDAO productoDAO;

	@Test
	void shouldListEstablishmentProducts() {

		EstablishmentProductDTO establishmentProduct1 = new EstablishmentProductDTO();
		establishmentProduct1.setImageId(10l);
		establishmentProduct1.setProductId(20l);
		establishmentProduct1.setProductName("Product Name 1");
		establishmentProduct1.setStatus(Boolean.TRUE);
		establishmentProduct1.setStockMinimumQuantity(9l);
		establishmentProduct1.setStockQuantity(90l);
		establishmentProduct1.setUnitPrice(9.2);

		EstablishmentProductTypeDTO establishmentProductTypeDTO1 = new EstablishmentProductTypeDTO();
		establishmentProductTypeDTO1.setId(30l);
		establishmentProductTypeDTO1.setName("Product Type 1");
		establishmentProduct1.setEstablishmentProductTypeDTO(establishmentProductTypeDTO1);

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO1 = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO1.setId(1l);
		establishmentProductDimensionDTO1.setHeight(2);
		establishmentProductDimensionDTO1.setLength(10);
		establishmentProductDimensionDTO1.setWidth(10);
		establishmentProduct1.setEstablishmentProductDimensionDTO(establishmentProductDimensionDTO1);

		EstablishmentProductDTO establishmentProduct2 = new EstablishmentProductDTO();
		establishmentProduct2.setImageId(20l);
		establishmentProduct2.setProductId(30l);
		establishmentProduct2.setProductName("Product Name 2");
		establishmentProduct2.setStatus(Boolean.FALSE);
		establishmentProduct2.setStockMinimumQuantity(90l);
		establishmentProduct2.setStockQuantity(67l);
		establishmentProduct2.setUnitPrice(2.9);

		EstablishmentProductTypeDTO establishmentProductTypeDTO2 = new EstablishmentProductTypeDTO();
		establishmentProductTypeDTO2.setId(80l);
		establishmentProductTypeDTO2.setName("Product Type 2");
		establishmentProduct2.setEstablishmentProductTypeDTO(establishmentProductTypeDTO2);

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO2 = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO2.setId(2l);
		establishmentProductDimensionDTO2.setHeight(87);
		establishmentProductDimensionDTO2.setLength(30);
		establishmentProductDimensionDTO2.setWidth(92);
		establishmentProduct2.setEstablishmentProductDimensionDTO(establishmentProductDimensionDTO2);

		when(productoDAO.listEstablishmentProducts(anyLong()))
				.thenReturn(Arrays.asList(establishmentProduct1, establishmentProduct2));

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

			assertThat(establishmentProductDTO.getEstablishmentProductTypeDTO().getId(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductTypeDTO().getName(), not(blankOrNullString()));

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
		newProductDTO.setName("Product Name 1");
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

	@Test
	public void shouldEdit() {

		Product product = new Product();
		Dimension dimension = new Dimension();
		dimension.setId(9l);
		product.setDimension(dimension);

		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

		EditProductDTO editProductDTO = new EditProductDTO();
		editProductDTO.setName("Product Name 1");
		editProductDTO.setMinimumStockQuantity(17l);
		editProductDTO.setProductTypeId(66l);

		EditDimensionDTO editDimensionDTO = new EditDimensionDTO();
		editDimensionDTO.setHeight(11);
		editDimensionDTO.setLength(22);
		editDimensionDTO.setWidth(33);
		editProductDTO.setEditDimensionDTO(editDimensionDTO);

		productService.edit(editProductDTO, 10l);

		verify(dimensionService, times(1)).edit(any(EditDimensionDTO.class), anyLong());
		verify(productRepository, times(1)).save(any(Product.class));

	}

	@Test
	public void shouldNotEditBecauseProductNotFound() {

		Product product = null;
		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

		EditProductDTO editProductDTO = new EditProductDTO();
		editProductDTO.setName("Product Name 1");
		editProductDTO.setMinimumStockQuantity(17l);
		editProductDTO.setProductTypeId(66l);

		EditDimensionDTO editDimensionDTO = new EditDimensionDTO();
		editDimensionDTO.setHeight(11);
		editDimensionDTO.setLength(22);
		editDimensionDTO.setWidth(33);
		editProductDTO.setEditDimensionDTO(editDimensionDTO);

		assertThrows(ProductNotFoundException.class, () -> {
			productService.edit(editProductDTO, 10l);
		});

		verify(dimensionService, never()).edit(any(EditDimensionDTO.class), anyLong());
		verify(productRepository, never()).save(any(Product.class));

	}

	@Test
	public void shouldToggleProductStatusToTrue() {

		Product product = new Product();
		product.setStatus(Boolean.TRUE);
		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

		productService.toggleProductStatus(10l);

		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(productRepository).save(productCaptor.capture());

		Product productKept = productCaptor.getValue();
		assertFalse(productKept.getStatus());

	}
	
	@Test
	public void shouldToggleProductStatusToFalse() {

		Product product = new Product();
		product.setStatus(Boolean.FALSE);
		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

		productService.toggleProductStatus(10l);

		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(productRepository).save(productCaptor.capture());

		Product productKept = productCaptor.getValue();
		assertTrue(productKept.getStatus());

	}

	@Test
	public void shouldNotToggleProductStatusBecauseNotFound() {

		Product product = null;
		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

		assertThrows(ProductNotFoundException.class, () -> {
			productService.toggleProductStatus(10l);
		});

		verify(productRepository, never()).save(any(Product.class));

	}

}
