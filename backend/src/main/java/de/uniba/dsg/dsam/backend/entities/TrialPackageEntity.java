package de.uniba.dsg.dsam.backend.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="TrialPackage")
public class TrialPackageEntity extends IncentiveEntity {


	private static final long serialVersionUID = 1L;

	public TrialPackageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrialPackageEntity(String inc_name, int inc_id, List<BeverageEntity> beverages) {
		super(inc_name, inc_id, beverages);
		// TODO Auto-generated constructor stub
	}

	public TrialPackageEntity(String inc_name, int inc_id) {
		super(inc_name, inc_id);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
