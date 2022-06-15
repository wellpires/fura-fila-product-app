package br.com.furafila.productapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "ESTOQUE_PRODUTOS")
@Entity
public class ProductStock implements Serializable {

	private static final long serialVersionUID = 7542360502845761303L;

	@Id
	@Column(name = "id_estoque_produto", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "qtdestoque", columnDefinition = "int4")
	private Long stockQuantity;

	@ManyToOne
	@JoinColumn(name = "id_produto_FK", columnDefinition = "int4")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_estoque_fk", columnDefinition = "int4")
	private Stock stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
