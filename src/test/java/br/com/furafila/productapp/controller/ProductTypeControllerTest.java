package br.com.furafila.productapp.controller;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.dto.NewProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDuplicityDTO;
import br.com.furafila.productapp.request.EditProductTypeRequest;
import br.com.furafila.productapp.response.NewProductTypeRequest;
import br.com.furafila.productapp.response.ProductTypeDuplicityResponse;
import br.com.furafila.productapp.response.ProductTypeResponse;
import br.com.furafila.productapp.service.ProductTypeService;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductTypeController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class ProductTypeControllerTest {

	private static final String PRODUCT_TYPE_PATH = "/product-types";
	private static final String EDIT_PRODUCT_TYPE_PATH = PRODUCT_TYPE_PATH.concat("/{productTypeId}");
	private static final String TOGGLE_PRODUCT_TYPE_STATUS_PATH = PRODUCT_TYPE_PATH.concat("/{productTypeId}");
	private static final String CHECK_PRODUCT_TYPE_DUPLICITY_PATH = PRODUCT_TYPE_PATH.concat("/{productTypeId}");

	@MockBean
	private ProductTypeService productTypeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private EditProductTypeRequest editProductTypeRequest;
	private NewProductTypeRequest newProductTypeRequest;

	@BeforeEach
	public void setup() throws StreamReadException, DatabindException, IOException {

		editProductTypeRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "EditProductTypeRequest.json").toFile(),
				EditProductTypeRequest.class);

		newProductTypeRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "NewProductTypeRequest.json").toFile(),
				NewProductTypeRequest.class);

	}

	@Test
	void shouldListProductTypes() throws Exception {

		ProductTypeDTO productTypeDTO1 = new ProductTypeDTO();
		productTypeDTO1.setId(1l);
		productTypeDTO1.setName("Product Name 1");
		productTypeDTO1.setStatus(Boolean.TRUE);

		ProductTypeDTO productTypeDTO2 = new ProductTypeDTO();
		productTypeDTO2.setId(2l);
		productTypeDTO2.setName("Product Name 2");
		productTypeDTO2.setStatus(Boolean.TRUE);

		ProductTypeDTO productTypeDTO3 = new ProductTypeDTO();
		productTypeDTO3.setId(3l);
		productTypeDTO3.setName("Product Name 3");
		productTypeDTO3.setStatus(Boolean.FALSE);

		ProductTypeDTO productTypeDTO4 = new ProductTypeDTO();
		productTypeDTO4.setId(4l);
		productTypeDTO4.setName("Product Name 4");
		productTypeDTO4.setStatus(Boolean.FALSE);

		when(productTypeService.listProductTypes(anyBoolean()))
				.thenReturn(Arrays.asList(productTypeDTO1, productTypeDTO2, productTypeDTO3, productTypeDTO4));

		MvcResult result = mockMvc.perform(get(PRODUCT_TYPE_PATH).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		ProductTypeResponse productTypeResponse = mapper.readValue(result.getResponse().getContentAsString(),
				ProductTypeResponse.class);

		assertNotNull(productTypeResponse);
		assertThat(productTypeResponse.getProductTypeDTOs(), not(empty()));

		int trueValue = 0;
		int falseValue = 0;
		for (ProductTypeDTO productTypeDTO : productTypeResponse.getProductTypeDTOs()) {

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
	void shouldListActiveOnlyProductTypes() throws Exception {

		ProductTypeDTO productTypeDTO1 = new ProductTypeDTO();
		productTypeDTO1.setId(1l);
		productTypeDTO1.setName("Product Name 1");
		productTypeDTO1.setStatus(Boolean.TRUE);

		ProductTypeDTO productTypeDTO2 = new ProductTypeDTO();
		productTypeDTO2.setId(2l);
		productTypeDTO2.setName("Product Name 2");
		productTypeDTO2.setStatus(Boolean.TRUE);

		ProductTypeDTO productTypeDTO3 = new ProductTypeDTO();
		productTypeDTO3.setId(3l);
		productTypeDTO3.setName("Product Name 3");
		productTypeDTO3.setStatus(Boolean.TRUE);

		ProductTypeDTO productTypeDTO4 = new ProductTypeDTO();
		productTypeDTO4.setId(4l);
		productTypeDTO4.setName("Product Name 4");
		productTypeDTO4.setStatus(Boolean.TRUE);

		when(productTypeService.listProductTypes(anyBoolean()))
				.thenReturn(Arrays.asList(productTypeDTO1, productTypeDTO2, productTypeDTO3, productTypeDTO4));

		String path = UriComponentsBuilder.fromPath(PRODUCT_TYPE_PATH)
				.queryParam("active-only", Boolean.TRUE.toString()).toUriString();

		MvcResult result = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		ProductTypeResponse productTypeResponse = mapper.readValue(result.getResponse().getContentAsString(),
				ProductTypeResponse.class);

		assertNotNull(productTypeResponse);
		assertThat(productTypeResponse.getProductTypeDTOs(), not(empty()));

		int trueValue = 0;
		int falseValue = 0;
		for (ProductTypeDTO productTypeDTO : productTypeResponse.getProductTypeDTOs()) {

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
		assertThat(falseValue, zeroValue());

	}

	@Test
	void shouldEdit() throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(EDIT_PRODUCT_TYPE_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(editProductTypeRequest))).andDo(print())
				.andExpect(status().isNoContent());

		ArgumentCaptor<EditProductTypeDTO> editProductTypeDTOCaptor = ArgumentCaptor.forClass(EditProductTypeDTO.class);
		ArgumentCaptor<Long> productTypeIdCaptor = ArgumentCaptor.forClass(Long.class);
		verify(productTypeService).edit(editProductTypeDTOCaptor.capture(), productTypeIdCaptor.capture());

		Long productTypeId = productTypeIdCaptor.getValue();
		assertThat(productTypeId, allOf(not(nullValue()), not(zeroValue())));

		EditProductTypeDTO editProductTypeDTO = editProductTypeDTOCaptor.getValue();
		assertNotNull(editProductTypeDTO);
		assertThat(editProductTypeDTO.getName(), not(blankOrNullString()));

	}

	@Test
	void shouldNotEditBecauseProductTypeInfoIsNull() throws Exception {

		editProductTypeRequest.setEditProductTypeDTO(null);

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(EDIT_PRODUCT_TYPE_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(editProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).edit(any(EditProductTypeDTO.class), anyLong());

	}

	@Test
	void shouldNotEditBecauseProductTypeNameIsNull() throws Exception {

		editProductTypeRequest.getEditProductTypeDTO().setName(null);

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(EDIT_PRODUCT_TYPE_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(editProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).edit(any(EditProductTypeDTO.class), anyLong());

	}

	@Test
	void shouldNotEditBecauseProductTypeNameIsNotValid() throws Exception {

		editProductTypeRequest.getEditProductTypeDTO().setName("a");

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(EDIT_PRODUCT_TYPE_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(editProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).edit(any(EditProductTypeDTO.class), anyLong());

	}

	@Test
	void shouldToggleStatus() throws JsonProcessingException, Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(TOGGLE_PRODUCT_TYPE_STATUS_PATH).buildAndExpand(param)
				.toUriString();

		mockMvc.perform(delete(path).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isNoContent());

		verify(productTypeService, times(1)).toggleStatus(anyLong());

	}

	@Test
	void shouldCreate() throws JsonProcessingException, Exception {
		mockMvc.perform(post(PRODUCT_TYPE_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductTypeRequest))).andDo(print())
				.andExpect(status().isNoContent());

		verify(productTypeService, times(1)).create(any(NewProductTypeDTO.class));

	}

	@Test
	void shouldNotCreateBecauseProductTypeInfoIsNull() throws JsonProcessingException, Exception {

		newProductTypeRequest.setNewProductTypeDTO(null);

		mockMvc.perform(post(PRODUCT_TYPE_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).create(any(NewProductTypeDTO.class));

	}

	@Test
	void shouldNotCreateBecauseProductTypeNameIsNull() throws JsonProcessingException, Exception {

		newProductTypeRequest.getNewProductTypeDTO().setName(null);

		mockMvc.perform(post(PRODUCT_TYPE_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).create(any(NewProductTypeDTO.class));

	}

	@Test
	void shouldNotCreateBecauseProductTypeNameIsNotValid() throws JsonProcessingException, Exception {

		newProductTypeRequest.getNewProductTypeDTO().setName("a");

		mockMvc.perform(post(PRODUCT_TYPE_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newProductTypeRequest))).andDo(print())
				.andExpect(status().isBadRequest());

		verify(productTypeService, never()).create(any(NewProductTypeDTO.class));

	}

	@Test
	void shouldCheckProductTypeDuplicity() throws Exception {

		ProductTypeDuplicityDTO productTypeDuplicityDTO = new ProductTypeDuplicityDTO();
		productTypeDuplicityDTO.setIsExists(Boolean.TRUE);
		when(productTypeService.checkProductTypeDuplicity(anyString())).thenReturn(productTypeDuplicityDTO);

		Map<String, Object> param = new HashMap<>();
		param.put("productTypeId", 12);
		String path = UriComponentsBuilder.fromPath(CHECK_PRODUCT_TYPE_DUPLICITY_PATH).buildAndExpand(param)
				.toUriString();

		MvcResult result = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		ProductTypeDuplicityResponse productTypeDuplicityResponse = mapper
				.readValue(result.getResponse().getContentAsString(), ProductTypeDuplicityResponse.class);

		assertNotNull(productTypeDuplicityResponse);
		assertNotNull(productTypeDuplicityResponse.getProductTypeDuplicityDTO());
		assertTrue(productTypeDuplicityResponse.getProductTypeDuplicityDTO().getIsExists());

	}

}
