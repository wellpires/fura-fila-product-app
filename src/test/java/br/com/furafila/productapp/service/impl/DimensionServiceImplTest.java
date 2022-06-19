package br.com.furafila.productapp.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.furafila.productapp.dto.EditDimensionDTO;
import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.exception.DimensionNotFoundException;
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

	@Test
	public void shouldEdit() {

		Dimension dimension = new Dimension();
		when(dimensionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(dimension));

		EditDimensionDTO editDimensionDTO = new EditDimensionDTO();
		editDimensionDTO.setHeight(11);
		editDimensionDTO.setLength(22);
		editDimensionDTO.setWidth(33);
		dimensionService.edit(editDimensionDTO, 80l);

		verify(dimensionRepository, times(1)).save(any(Dimension.class));

		ArgumentCaptor<Dimension> dimensionCaptor = ArgumentCaptor.forClass(Dimension.class);
		verify(dimensionRepository).save(dimensionCaptor.capture());
		Dimension dimensionCaught = dimensionCaptor.getValue();

		assertThat(dimensionCaught.getHeight(), equalTo(editDimensionDTO.getHeight()));
		assertThat(dimensionCaught.getWidth(), equalTo(editDimensionDTO.getWidth()));
		assertThat(dimensionCaught.getLength(), equalTo(editDimensionDTO.getLength()));

	}

	@Test
	public void shouldNotEditBecauseNotFound() {

		Dimension dimension = null;
		when(dimensionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(dimension));

		EditDimensionDTO editDimensionDTO = new EditDimensionDTO();

		assertThrows(DimensionNotFoundException.class, () -> {
			dimensionService.edit(editDimensionDTO, 80l);
		});

		verify(dimensionRepository, never()).save(any(Dimension.class));

	}

}
