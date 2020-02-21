package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.uniba.dsg.dsam.model.Beverage;

@Entity
@Table(name = "CustomerOrder")
public class CustomerOrderEntity implements Serializable  {
	
	private Date issue_date;
    
    
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Integer order_id;
    
    public List<BeverageEntity> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<BeverageEntity> beverages) {
		this.beverages = beverages;
	}

	@ManyToMany(mappedBy = "orders")
    private List<BeverageEntity> beverages = new ArrayList<>();
	public Date getIssue_date() {
		return issue_date;
	}



	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}


	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	public void add(BeverageEntity bev) {
		this.getBeverages().add(bev);
		bev.getOrders().add(this);
	}

}
