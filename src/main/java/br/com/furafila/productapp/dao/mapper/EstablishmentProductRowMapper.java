package br.com.furafila.productapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.furafila.productapp.builder.EstablishmentProductDTOBuilder;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;

public class EstablishmentProductRowMapper implements RowMapper<EstablishmentProductDTO> {

	@Override
	public EstablishmentProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		EstablishmentProductTypeDTO establishmentProductTypeDTO = new EstablishmentProductTypeDTO();
		establishmentProductTypeDTO.setId(rs.getLong("id_tipo_produto"));
		establishmentProductTypeDTO.setName(rs.getString("tipo_produto_desc"));

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO.setId(rs.getLong("id_dimensao"));
		establishmentProductDimensionDTO.setHeight(rs.getInt("altura"));
		establishmentProductDimensionDTO.setWidth(rs.getInt("largura"));
		establishmentProductDimensionDTO.setLength(rs.getInt("profundidade"));

		return new EstablishmentProductDTOBuilder().productId(rs.getLong("id_produto"))
				.productName(rs.getString("produto_desc")).unitPrice(rs.getDouble("valor_unitario"))
				.status(rs.getBoolean("status")).stockMinimumQuantity(rs.getLong("qtdMinima"))
				.imageId(rs.getLong("id_imagem")).stockQuantity(rs.getLong("qtdestoque"))
				.establishmentProductTypeDTO(establishmentProductTypeDTO)
				.establishmentProductDimensionDTO(establishmentProductDimensionDTO).build();

	}

}
