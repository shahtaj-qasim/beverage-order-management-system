package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import javax.persistence.ManyToMany;


import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.model.Incentive;

@Entity
@Table(name="Beverage")
public class BeverageEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer bev_id;

	private String manufacturer;
    private String name;
    private int quantity;

    private int initQuantity;

	public int getInitQuantity() {
		return initQuantity;
	}

	public void setInitQuantity(int initQuantity) {
		this.initQuantity = initQuantity;
	}
	private double price;

    //private Incentive inc_id;
		@ManyToOne(optional=true)
		private IncentiveEntity incentive;




	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "beverage_order", joinColumns = @JoinColumn(name = "bev_id", referencedColumnName = "bev_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
    private List<CustomerOrderEntity> orders
    ;

	public String getManufacturer() {
		return manufacturer;
	}

	public List<CustomerOrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrderEntity> orders) {
		this.orders = orders;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public Integer getBev_id() {
		return bev_id;
	}

	public void setBev_id(Integer bev_id) {
		this.bev_id = bev_id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	 public IncentiveEntity getIncentive() {
			return incentive;
		}



		public void setIncentive(IncentiveEntity incentive) {
			this.incentive = incentive;
		}

	public BeverageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

    public void setName(String name) {
    	this.name= name;
    }
    // String Representation:
    //@Override
    public String getName() {
    	return this.name;
    }
}
