package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the LIFE_CORP_PRODUCTS database table.
 * 
 */
@Entity
@Table(name="LIFE_CORP_PRODUCTS", schema="GUITARSHOP")
@NamedQuery(name="LifeCorpProduct.findAll", query="SELECT l FROM LifeCorpProduct l")
public class LifeCorpProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private long productId;

	@Column(name="LIST_PRICE")
	private BigDecimal listPrice;

	private String product;

	//bi-directional many-to-one association to LifeCorpOrder
	@OneToMany(mappedBy="lifeCorpProduct")
	private List<LifeCorpOrder> lifeCorpOrders;

	public LifeCorpProduct() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public BigDecimal getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public List<LifeCorpOrder> getLifeCorpOrders() {
		return this.lifeCorpOrders;
	}

	public void setLifeCorpOrders(List<LifeCorpOrder> lifeCorpOrders) {
		this.lifeCorpOrders = lifeCorpOrders;
	}

	public LifeCorpOrder addLifeCorpOrder(LifeCorpOrder lifeCorpOrder) {
		getLifeCorpOrders().add(lifeCorpOrder);
		lifeCorpOrder.setLifeCorpProduct(this);

		return lifeCorpOrder;
	}

	public LifeCorpOrder removeLifeCorpOrder(LifeCorpOrder lifeCorpOrder) {
		getLifeCorpOrders().remove(lifeCorpOrder);
		lifeCorpOrder.setLifeCorpProduct(null);

		return lifeCorpOrder;
	}

}