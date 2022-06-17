package br.com.furafila.productapp.controller;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.request.NewProductRequest;
import br.com.furafila.productapp.response.EstablishmentProductResponse;
import br.com.furafila.productapp.response.NewProductResponse;
import br.com.furafila.productapp.service.ProductService;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class ProductControllerTest {

	private static final String PRODUCT_PATH = "/products";
	private static final String LIST_ESTABLISHMENT_PRODUCTS_PATH = PRODUCT_PATH
			.concat("/establishments/{establishmentId}");

	@MockBean
	private ProductService productService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private NewProductRequest newProductRequest;

	@BeforeEach
	public void setup() throws StreamReadException, DatabindException, IOException {

		newProductRequest = mapper.readValue(Paths.get("src", "test", "resources", "NewProductRequest.json").toFile(),
				NewProductRequest.class);

	}

	@Test
	public void shouldListEstablishmentProducts() throws Exception {

		EstablishmentProductDTO establishmentProductDTO1 = new EstablishmentProductDTO();
		establishmentProductDTO1.setProductId(321l);
		establishmentProductDTO1.setProductName("Product Name 2");
		establishmentProductDTO1.setUnitPrice(22.10);
		establishmentProductDTO1.setStatus(Boolean.FALSE);
		establishmentProductDTO1.setImageId(32l);
		establishmentProductDTO1.setStockMinimumQuantity(1l);
		establishmentProductDTO1.setStockQuantity(9l);

		EstablishmentProductTypeDTO establishmentProducTypeDTO1 = new EstablishmentProductTypeDTO();
		establishmentProducTypeDTO1.setId(1l);
		establishmentProducTypeDTO1.setName("product type 1");
		establishmentProductDTO1.setEstablishmentProducTypeDTO(establishmentProducTypeDTO1);

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO1 = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO1.setId(1l);
		establishmentProductDimensionDTO1.setHeight(321);
		establishmentProductDimensionDTO1.setLength(123);
		establishmentProductDimensionDTO1.setWidth(45);
		establishmentProductDTO1.setEstablishmentProductDimensionDTO(establishmentProductDimensionDTO1);

		EstablishmentProductDTO establishmentProductDTO2 = new EstablishmentProductDTO();
		establishmentProductDTO2.setProductId(123l);
		establishmentProductDTO2.setProductName("Product Name 1");
		establishmentProductDTO2.setUnitPrice(12.2);
		establishmentProductDTO2.setStatus(Boolean.TRUE);
		establishmentProductDTO2.setImageId(23l);
		establishmentProductDTO2.setStockMinimumQuantity(10l);
		establishmentProductDTO2.setStockQuantity(90l);

		EstablishmentProductTypeDTO establishmentProducTypeDTO2 = new EstablishmentProductTypeDTO();
		establishmentProducTypeDTO2.setId(1l);
		establishmentProducTypeDTO2.setName("product type 2_1");
		establishmentProductDTO2.setEstablishmentProducTypeDTO(establishmentProducTypeDTO2);

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO2 = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO2.setId(1l);
		establishmentProductDimensionDTO2.setHeight(123);
		establishmentProductDimensionDTO2.setLength(321);
		establishmentProductDimensionDTO2.setWidth(54);
		establishmentProductDTO2.setEstablishmentProductDimensionDTO(establishmentProductDimensionDTO2);

		List<EstablishmentProductDTO> establishmentProductsDTOs = Arrays.asList(establishmentProductDTO1,
				establishmentProductDTO2);
		when(productService.listEstablishmentProducts(anyLong())).thenReturn(establishmentProductsDTOs);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 12);

		String path = UriComponentsBuilder.fromPath(LIST_ESTABLISHMENT_PRODUCTS_PATH).buildAndExpand(param)
				.toUriString();

		MvcResult result = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		EstablishmentProductResponse establishmentProductResponse = mapper
				.readValue(result.getResponse().getContentAsString(), EstablishmentProductResponse.class);
		List<EstablishmentProductDTO> establishmentProductDTOs = establishmentProductResponse
				.getEstablishmentProductDTOs();

		assertNotNull(establishmentProductResponse);
		assertThat(establishmentProductDTOs, hasSize(2));

		for (EstablishmentProductDTO establishmentProductDTO : establishmentProductDTOs) {
			assertThat(establishmentProductDTO.getProductId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getProductName(), not(blankOrNullString()));
			assertThat(establishmentProductDTO.getUnitPrice(), allOf(not(nullValue()), not(zeroValue())));
			assertNotNull(establishmentProductDTO.getStatus());
			assertThat(establishmentProductDTO.getImageId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getStockMinimumQuantity(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getStockQuantity(), allOf(not(nullValue()), not(zeroValue())));

			assertNotNull(establishmentProductDTO.getEstablishmentProductDimensionDTO());
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getId(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getHeight(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getLength(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProductDimensionDTO().getWidth(),
					allOf(not(nullValue()), not(zeroValue())));

			assertNotNull(establishmentProductDTO.getEstablishmentProducTypeDTO());
			assertThat(establishmentProductDTO.getEstablishmentProducTypeDTO().getId(),
					allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentProductDTO.getEstablishmentProducTypeDTO().getName(), not(blankOrNullString()));

		}

	}

	@Test
	public void shouldCreateProduct() throws Exception {

		when(productService.createProduct(any(NewProductDTO.class))).thenReturn(10l);

		MvcResult result = mockMvc
				.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(newProductRequest)))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		NewProductResponse newProductResponse = mapper.readValue(result.getResponse().getContentAsString(),
				NewProductResponse.class);

		verify(productService, times(1)).createProduct(any());

		assertThat(newProductResponse.getId(), equalTo(10l));

	}

	@Test
	public void shouldNotCreateProductBecauseProductInfoIsNull() throws Exception {

		newProductRequest.setNewProductDTO(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseProductNameIsNull() throws Exception {

		newProductRequest.getNewProductDTO().setProductName(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseProductNameIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().setProductName("1");

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseMinimumStockQuantityIsNull() throws Exception {

		newProductRequest.getNewProductDTO().setMinimumStockQuantity(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseMinimumStockQuantityIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().setMinimumStockQuantity(0l);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseProductTypeIdIsNull() throws Exception {

		newProductRequest.getNewProductDTO().setProductTypeId(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseProductTypeIdIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().setProductTypeId(0l);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseImageIdIsNull() throws Exception {

		newProductRequest.getNewProductDTO().setImageId(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseImageIdIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().setImageId(0l);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseDimensionInfoIsNull() throws Exception {

		newProductRequest.getNewProductDTO().setNewDimensionDTO(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseHeightIsNull() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setHeight(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseHeightIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setHeight(0);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseWidthIsNull() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setWidth(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseWidthIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setWidth(0);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseLengthIsNull() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setLength(null);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

	@Test
	public void shouldNotCreateProductBecauseLengthIsNotValid() throws Exception {

		newProductRequest.getNewProductDTO().getNewDimensionDTO().setLength(0);

		mockMvc.perform(post(PRODUCT_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductRequest))).andExpect(status().isBadRequest())
				.andDo(print()).andReturn();

		verify(productService, never()).createProduct(any());

	}

}
