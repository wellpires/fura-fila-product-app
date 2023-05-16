package br.com.furafila.productapp.service.impl;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.dto.NewProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDuplicityDTO;
import br.com.furafila.productapp.exception.ProductTypeNotFoundException;
import br.com.furafila.productapp.model.ProductType;
import br.com.furafila.productapp.repository.ProductTypeRepository;
import br.com.furafila.productapp.service.ProductTypeService;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class ProductTypeServiceImplTest {

	@InjectMocks
	private ProductTypeService productTypeService = new ProductTypeServiceImpl();

	@Mock
	private ProductTypeRepository productTypeRepository;

	@Test
	public void shouldAllListProductTypes() {

		ProductType productType1 = new ProductType();
		productType1.setId(1l);
		productType1.setName("Product Name 1");
		productType1.setStatus(Boolean.TRUE);

		ProductType productType2 = new ProductType();
		productType2.setId(2l);
		productType2.setName("Product Name 2");
		productType2.setStatus(Boolean.TRUE);

		ProductType productType3 = new ProductType();
		productType3.setId(3l);
		productType3.setName("Product Name 3");
		productType3.setStatus(Boolean.FALSE);

		ProductType productType4 = new ProductType();
		productType4.setId(4l);
		productType4.setName("Product Name 4");
		productType4.setStatus(Boolean.FALSE);

		when(productTypeRepository.findAll())
				.thenReturn(Arrays.asList(productType1, productType2, productType3, productType4));

		List<ProductTypeDTO> activeProductTypes = productTypeService.listProductTypes(Boolean.FALSE);

		assertThat(activeProductTypes, not(empty()));

		int trueValue = 0;
		int falseValue = 0;
		for (ProductTypeDTO productTypeDTO : activeProductTypes) {

			assertThat(productTypeDTO.getId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(productTypeDTO.getName(), not(blankOrNullString()));
			assertNotNull(productTypeDTO.getStatus());

			if (productTypeDTO.getStatus()) {
				trueValue++;
			} else {
				falseValue++;
			}

		}
		assertThat(trueValue, equalTo(2));
		assertThat(falseValue, equalTo(2));

	}

	@Test
	public void shouldActiveListProductTypes() {

		ProductType productType1 = new ProductType();
		productType1.setId(1l);
		productType1.setName("Product Name 1");
		productType1.setStatus(Boolean.TRUE);

		ProductType productType2 = new ProductType();
		productType2.setId(2l);
		productType2.setName("Product Name 2");
		productType2.setStatus(Boolean.TRUE);

		ProductType productType3 = new ProductType();
		productType3.setId(3l);
		productType3.setName("Product Name 3");
		productType3.setStatus(Boolean.TRUE);

		ProductType productType4 = new ProductType();
		productType4.setId(4l);
		productType4.setName("Product Name 4");
		productType4.setStatus(Boolean.TRUE);

		when(productTypeRepository.findByStatusIsTrue())
				.thenReturn(Arrays.asList(productType1, productType2, productType3, productType4));

		List<ProductTypeDTO> activeProductTypes = productTypeService.listProductTypes(Boolean.TRUE);

		assertThat(activeProductTypes, not(empty()));

		int trueValue = 0;
		int falseValue = 0;
		for (ProductTypeDTO productTypeDTO : activeProductTypes) {

			assertThat(productTypeDTO.getId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(productTypeDTO.getName(), not(blankOrNullString()));
			assertNotNull(productTypeDTO.getStatus());

			if (productTypeDTO.getStatus()) {
				trueValue++;
			} else {
				falseValue++;
			}

		}
		assertThat(trueValue, equalTo(4));
		assertThat(falseValue, equalTo(0));

	}

	@Test
	public void shouldEdit() {

		String oldProductTypeName = "Old Product Type Name";
		String newProductTypeName = "New Product Type Name";

		ProductType productType = new ProductType();
		productType.setName(oldProductTypeName);
		when(productTypeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(productType));

		EditProductTypeDTO editProductTypeDTO = new EditProductTypeDTO();
		editProductTypeDTO.setName(newProductTypeName);
		productTypeService.edit(editProductTypeDTO, 12l);

		ArgumentCaptor<ProductType> productTypeCaptor = ArgumentCaptor.forClass(ProductType.class);
		verify(productTypeRepository).save(productTypeCaptor.capture());
		assertThat(productTypeCaptor.getValue().getName(), equalTo(newProductTypeName));

		verify(productTypeRepository, times(1)).save(any(ProductType.class));

	}

	@Test
	public void shouldNotEditBecauseProductTypeNotFound() {

		String oldProductTypeName = "Old Product Type Name";
		String newProductTypeName = "New Product Type Name";

		ProductType productType = new ProductType();
		productType.setName(oldProductTypeName);
		when(productTypeRepository.findById(anyLong())).thenThrow(new ProductTypeNotFoundException());

		EditProductTypeDTO editProductTypeDTO = new EditProductTypeDTO();
		editProductTypeDTO.setName(newProductTypeName);

		assertThrows(ProductTypeNotFoundException.class, () -> {
			productTypeService.edit(editProductTypeDTO, 12l);
		});

		verify(productTypeRepository, never()).save(any(ProductType.class));

	}

	@Test
	public void shouldToggleStatus() {

		ProductType productType = new ProductType();
		productType.setStatus(Boolean.TRUE);
		when(productTypeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(productType));

		productTypeService.toggleStatus(10l);

		ArgumentCaptor<ProductType> productTypeCaptor = ArgumentCaptor.forClass(ProductType.class);
		verify(productTypeRepository).save(productTypeCaptor.capture());
		assertThat(productTypeCaptor.getValue().getStatus(), equalTo(Boolean.FALSE));

		verify(productTypeRepository, times(1)).save(any(ProductType.class));

	}

	@Test
	public void shouldNotToggleStatusBecauseProductTypeNotFound() {

		when(productTypeRepository.findById(anyLong())).thenThrow(new ProductTypeNotFoundException());

		assertThrows(ProductTypeNotFoundException.class, () -> {
			productTypeService.toggleStatus(10l);
		});

		verify(productTypeRepository, never()).save(any(ProductType.class));

	}

	@Test
	public void shouldCreate() {

		String productTypeName = "Product Type Name";
		NewProductTypeDTO newProductTypeDTO = new NewProductTypeDTO();
		newProductTypeDTO.setName(productTypeName);

		productTypeService.create(newProductTypeDTO);

		ArgumentCaptor<ProductType> productTypeCaptor = ArgumentCaptor.forClass(ProductType.class);
		verify(productTypeRepository).save(productTypeCaptor.capture());
		assertThat(productTypeCaptor.getValue().getStatus(), equalTo(Boolean.TRUE));
		assertThat(productTypeCaptor.getValue().getName(), equalTo(productTypeName));

		verify(productTypeRepository, times(1)).save(any(ProductType.class));

	}

	@Test
	public void shouldCheckProductTypeDuplicity() {

		when(productTypeRepository.existsByNameIgnoreCase(anyString())).thenReturn(Boolean.TRUE);

		ProductTypeDuplicityDTO productTypeDuplicity = productTypeService
				.checkProductTypeDuplicity("Product Type Name");

		assertTrue(productTypeDuplicity.getIsExists());

	}

}
