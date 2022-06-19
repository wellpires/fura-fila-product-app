package br.com.furafila.productapp.dao.impl;

import static br.com.furafila.productapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.furafila.productapp.dao.mapper.EstablishmentProductRowMapper;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtendWith(MockitoExtension.class)
public class ProductDAOImplTest {

	@InjectMocks
	private ProductDAOImpl productDAO;

	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void shouldListEstablishmentProducts() {

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

		when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class),
				any(EstablishmentProductRowMapper.class)))
						.thenReturn(Arrays.asList(establishmentProduct1, establishmentProduct2));

		List<EstablishmentProductDTO> establishmentProducts = productDAO.listEstablishmentProducts(10l);

		assertThat(establishmentProducts, hasSize(2));

		for (EstablishmentProductDTO establishmentProductDTO : establishmentProducts) {

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

}
