package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS", schema="GUITARSHOP")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long userid;

	private String emailid;

	private String firstname;

	private String lastname;

	private String password;

	//bi-directional many-to-one association to LifeCorpOrder
	@OneToMany(mappedBy="user")
	private List<LifeCorpOrder> lifeCorpOrders;

	public User() {
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<LifeCorpOrder> getLifeCorpOrders() {
		return this.lifeCorpOrders;
	}

	public void setLifeCorpOrders(List<LifeCorpOrder> lifeCorpOrders) {
		this.lifeCorpOrders = lifeCorpOrders;
	}

	public LifeCorpOrder addLifeCorpOrder(LifeCorpOrder lifeCorpOrder) {
		getLifeCorpOrders().add(lifeCorpOrder);
		lifeCorpOrder.setUser(this);

		return lifeCorpOrder;
	}

	public LifeCorpOrder removeLifeCorpOrder(LifeCorpOrder lifeCorpOrder) {
		getLifeCorpOrders().remove(lifeCorpOrder);
		lifeCorpOrder.setUser(null);

		return lifeCorpOrder;
	}

}