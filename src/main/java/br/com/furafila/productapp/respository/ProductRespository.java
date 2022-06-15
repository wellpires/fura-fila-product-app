package br.com.furafila.productapp.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.furafila.productapp.model.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {

	@Query("from Product p inner join p.productType pt inner join p.dimension d inner join p.image i inner join ProductStock ps on ps.product.id = p.id inner join ps.stock s where pt.status = 1 and s.establishmentId = :establishmentId")
	List<Product> listEstablishmentProducts(@Param("establishmentId") Long establishmentId);

//	select 	
//	p.id_produto,
//	p.produto_desc,
//	p.qtdMinima,
//	p.valor_unitario,
//	p.status,
//	tp.id_tipo_produto,
//	tp.tipo_produto_desc,
//	d.id_dimensao,
//	d.altura,
//	d.largura,
//	d.profundidade,
//	i.id_imagem ,
//	ep.qtdestoque 
//from 
//	produto p 
//inner join 
//	tipo_produto tp on tp.id_tipo_produto = p.id_tipo_produto_fk
//inner join
//	dimensao d on d.id_dimensao = p.id_dimensao_fk 
//inner join 
//	imagem i on i.id_imagem = p.id_imagem_fk 
//inner join 
//	estoque_produtos ep on ep.id_produto_fk = p.id_produto
//inner join
//	estoque e on e.id_estoque = ep.id_estoque_fk 
//where 
//	tp.status = 1
//and 
//	e.id_estabelecimento_FK = 1;

}
