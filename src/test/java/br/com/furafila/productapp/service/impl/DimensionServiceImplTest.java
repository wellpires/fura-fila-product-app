package br.com.furafila.productapp.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.model.Dimension;
import br.com.furafila.productapp.repository.DimensionRepository;
import br.com.furafila.productapp.service.DimensionService;
import br.com.furafila.productapp.util.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
@ExtendWith(MockitoExtension.class)
public class DimensionServiceImplTest {

	@InjectMocks
	private DimensionService dimensionService = new DimensionServiceImpl();

	@Mock
	private DimensionRepository dimensionRepository;

	@Test
	public void shouldCreate() {

		Long dimensionId = 10l;
		Dimension dimension = new Dimension();
		dimension.setId(dimensionId);
		when(dimensionRepository.save(any())).thenReturn(dimension);

		NewDimensionDTO newDimensionDTO = new NewDimensionDTO();
		newDimensionDTO.setHeight(10);
		newDimensionDTO.setWidth(30);
		newDimensionDTO.setLength(15);
		Long dimensionIdCreated = dimensionService.create(newDimensionDTO);

		assertThat(dimensionIdCreated, equalTo(dimensionId));

	}

}
