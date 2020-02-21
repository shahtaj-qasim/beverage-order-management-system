package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Incentive")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="inc_type", discriminatorType=DiscriminatorType.STRING)
public abstract class IncentiveEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String inc_name;
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int inc_id;
    
    //@OneToMany(cascade=CascadeType.ALL)
    //@JoinColumn(name="inc_id",referencedColumnName="inc_id")
	@OneToMany(mappedBy="incentive", fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<BeverageEntity> beverages;
    
  

    public  IncentiveEntity(String inc_name) {
    this.inc_name = inc_name;
    }

	public String getInc_name() {
		return inc_name;
	}

	public List<BeverageEntity> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<BeverageEntity> beverages) {
		this.beverages = beverages;
	}

	public void setInc_name(String inc_name) {
		this.inc_name = inc_name;
	}

	public int getInc_id() {
		return inc_id;
	}

	public void setInc_id(int inc_id) {
		this.inc_id = inc_id;
	}

	public IncentiveEntity(String inc_name, int inc_id, List<BeverageEntity> beverages) {
		super();
		this.inc_name = inc_name;
		this.inc_id = inc_id;
		this.beverages = beverages;
	}

	public IncentiveEntity(String inc_name, int inc_id) {
		super();
		this.inc_name = inc_name;
		this.inc_id = inc_id;
	}
	
	  public  IncentiveEntity() {

	    }
	    
	
	
	
}


