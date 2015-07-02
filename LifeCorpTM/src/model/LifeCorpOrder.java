package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LIFE_CORP_ORDERS database table.
 * 
 */
@Entity
@Table(name="LIFE_CORP_ORDERS", schema="GUITARSHOP")
@NamedQuery(name="LifeCorpOrder.findAll", query="SELECT l FROM LifeCorpOrder l")
public class LifeCorpOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="ORDER_ID")
	private long orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	private BigDecimal quantity;

	//bi-directional many-to-one association to LifeCorpProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private LifeCorpProduct lifeCorpProduct;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public LifeCorpOrder() {
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public LifeCorpProduct getLifeCorpProduct() {
		return this.lifeCorpProduct;
	}

	public void setLifeCorpProduct(LifeCorpProduct lifeCorpProduct) {
		this.lifeCorpProduct = lifeCorpProduct;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}