package de.uniba.dsg.dsam.backend.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement {
	private static final Logger logger = Logger.getLogger(IncentiveManagementBean.class.getName());

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;
	@Override
	public void create(Incentive incentive) {
		// TODO Auto-generated method stub
		IncentiveEntity ie = null;
	
		if(incentive instanceof PromotionalGift) {
    		ie= new PromotionalGiftEntity();
    		ie.setInc_name(incentive.getInc_name());
        	em.persist(ie);
    	}
		else if (incentive instanceof TrialPackage) {			
    		ie= new TrialPackageEntity();
    		ie.setInc_name(incentive.getInc_name());
        	em.persist(ie);

    	}
	}
	
	@Override
	public Beverage getBeverage(int bev_id) {
		
		 TypedQuery<BeverageEntity> query =  em.createQuery("SELECT b FROM BeverageEntity b WHERE b.bev_id = :custid", BeverageEntity.class);
		 			query.setParameter("custid", bev_id);
		 			BeverageEntity beverage = query.getSingleResult();
		        	Beverage dto = new Beverage();
		        	dto.setName(beverage.getName());
		        	dto.setManufacturer(beverage.getManufacturer());
		        	dto.setPrice(beverage.getPrice());
		        	dto.setQuantity(beverage.getQuantity());
		        
		 return dto;
	}

	
public List<Incentive> getAllIncentives() {
		
        List<Incentive> listIncentive = new ArrayList<Incentive>();

        TypedQuery<IncentiveEntity> query = em.createQuery(
            "SELECT i FROM IncentiveEntity i ORDER BY i.inc_id ", IncentiveEntity.class);
        List<IncentiveEntity> list = query.getResultList();
       for (IncentiveEntity i : list ) {
    	   if (i instanceof PromotionalGiftEntity) {
        	PromotionalGift promotionalGiftDto = new PromotionalGift();
        	promotionalGiftDto.setInc_id(i.getInc_id());
    		promotionalGiftDto.setInc_name(i.getInc_name());
    		listIncentive.add(promotionalGiftDto);
    	   }
    	   if ( i instanceof TrialPackageEntity) {
           	TrialPackage trialPackageDto = new TrialPackage();
       		trialPackageDto.setInc_name(i.getInc_name());
       		trialPackageDto.setInc_id(i.getInc_id());
       		listIncentive.add(trialPackageDto);
    	   }
        }
       for ( Incentive i : listIncentive) {
		logger.info("Inside get all function incentive , name : " + i.getInc_name() + "id : "+ i.getInc_id());
       }
        return listIncentive;
    }

@Override
public void delete(int inc_id) {
	// TODO Auto-generated method stub
	IncentiveEntity inc = select(inc_id);
	em.remove(inc);
	
}

private IncentiveEntity select(int inc_id) {
	IncentiveEntity i = em.find(IncentiveEntity.class, new Integer(inc_id));
	if(i == null)
		return null;
	else{
		return i;
	}
}

@Override
public Incentive getIncentive(int inc_id)  {
	IncentiveEntity i = em.find(IncentiveEntity.class, new Integer(inc_id));
	logger.info("Inside get incentive , name : " + i.getInc_name() + "   id : "+ i.getInc_id());

	if(i == null)
		throw new NotFoundException();
	else{
		return convert(i);
	}
}
private Incentive convert(IncentiveEntity inc) {
	
	Incentive dto = null;
	if ( inc instanceof PromotionalGiftEntity) 
		{
		dto = new PromotionalGift();
		dto.setInc_id(inc.getInc_id());
		dto.setInc_name(inc.getInc_name());
		}
	else if ( inc instanceof TrialPackageEntity) {
		dto = new TrialPackage();
		dto.setInc_id(inc.getInc_id());
		dto.setInc_name(inc.getInc_name());
	}
	logger.info("Inside convert incentiveEntity to incentiveDto , name : " + dto.getInc_name() + "   id : "+ dto.getInc_id());

	return dto;
}
	
@Override
public void update(int inc_id , String inc_name)  {
	IncentiveEntity inc = select(inc_id);
	
	inc.setInc_name(inc_name);
	logger.info("Done Incentive Update");
}


	 
}
