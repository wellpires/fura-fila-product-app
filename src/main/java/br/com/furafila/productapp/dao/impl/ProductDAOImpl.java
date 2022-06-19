package br.com.furafila.productapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.furafila.productapp.dao.ProductDAO;
import br.com.furafila.productapp.dao.mapper.EstablishmentProductRowMapper;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;

@Component
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId) {

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("establishmentId", establishmentId);

		StringBuilder query = new StringBuilder();
		query.append("SELECT");
		query.append("	p.id_produto,");
		query.append("	p.produto_desc,");
		query.append("	p.qtdMinima,");
		query.append("	p.valor_unitario,");
		query.append("	p.status,");
		query.append("	tp.id_tipo_produto,");
		query.append("	tp.tipo_produto_desc,");
		query.append("	d.id_dimensao,");
		query.append("	d.altura,");
		query.append("	d.largura,");
		query.append("	d.profundidade,");
		query.append("	i.id_imagem,");
		query.append("	ep.qtdestoque ");
		query.append("FROM PRODUTO p ");
		query.append("INNER JOIN TIPO_PRODUTO tp ON p.id_tipo_produto_fk = tp.id_tipo_produto ");
		query.append("INNER JOIN DIMENSAO d ON p.id_dimensao_fk = d.id_dimensao ");
		query.append("INNER JOIN IMAGEM i on p.id_imagem_fk = i.id_imagem ");
		query.append("INNER JOIN ESTOQUE_PRODUTOS ep on ep.id_produto_fk = p.id_produto ");
		query.append("inner join estoque e on e.id_estoque = ep.id_estoque_fk ");
		query.append("WHERE ");
		query.append(" 	tp.status = 1 and");
		query.append("	e.id_estabelecimento_fk = :establishmentId");

		return jdbcTemplate.query(query.toString(), parameterSource, new EstablishmentProductRowMapper());
	}

}
