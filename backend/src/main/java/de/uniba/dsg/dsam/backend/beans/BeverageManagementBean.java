package de.uniba.dsg.dsam.backend.beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.NotFoundException;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

@Stateless
@Remote(BeverageManagement.class)
public class BeverageManagementBean implements BeverageManagement {
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;
    @Override
    public void create(Beverage beverage) {
    	BeverageEntity bev = new BeverageEntity();
    	bev.setName(beverage.getName());
    	bev.setManufacturer(beverage.getManufacturer());
    	bev.setInitQuantity(beverage.getQuantity());
    	bev.setQuantity(beverage.getQuantity());
    	bev.setPrice(beverage.getPrice());
    	
    	em.persist(bev);
    }
    
    
    
 @Override
	public void create(Beverage beverage, int inc_id) {
	 BeverageEntity bev = new BeverageEntity();
 	bev.setName(beverage.getName());
 	bev.setManufacturer(beverage.getManufacturer());
 	bev.setQuantity(beverage.getQuantity());
 	bev.setPrice(beverage.getPrice());
 	Incentive incentive = getIncentive(inc_id);
 	if ( incentive instanceof PromotionalGift) {
bev.setIncentive(new PromotionalGiftEntity()); 
bev.getIncentive().setInc_id(incentive.getInc_id());
	bev.getIncentive().setInc_name(incentive.getInc_name());
} else if ( incentive  instanceof TrialPackage) {
	bev.setIncentive(new TrialPackageEntity()); 
	bev.getIncentive().setInc_id(incentive.getInc_id());
		bev.getIncentive().setInc_name(incentive.getInc_name());
}
 	
 	
 	em.persist(bev);		
	}



	// Retrieves all the guests:
    public List<Beverage> getAllBeverages() {
        TypedQuery<BeverageEntity> query = em.createQuery(
            "SELECT b FROM BeverageEntity b ORDER BY b.bev_id", BeverageEntity.class);
        List<Beverage> listBeverage = new ArrayList<Beverage>();
        List<BeverageEntity> list = query.getResultList();
        for (BeverageEntity b : list ) {
        	Beverage dto = new Beverage();
        	dto.setName(b.getName());
        	dto.setManufacturer(b.getManufacturer());
        	dto.setPrice(b.getPrice());
        	dto.setInitQuantity(b.getInitQuantity());
        	dto.setQuantity(b.getQuantity());
        	if ( b.getIncentive() != null ) {
        	dto.getIncentive().setInc_id(b.getIncentive().getInc_id());
        	dto.getIncentive().setInc_name(b.getIncentive().getInc_name());
        	}
        	listBeverage.add(dto);
        }
        return listBeverage;
    }
    
    @Override
    public Incentive getIncentive(int inc_id)  {
    	IncentiveEntity i = em.find(IncentiveEntity.class, new Integer(inc_id));

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

    	return dto;
    }
    
    @Override
public List<Incentive> getAllIncentives() {
		
        List<Incentive> listIncentive = new ArrayList<Incentive>();

        TypedQuery<IncentiveEntity> query = em.createQuery(
            "SELECT i FROM IncentiveEntity i ORDER BY i.inc_id ", IncentiveEntity.class);
        List<IncentiveEntity> list = query.getResultList();
       for (IncentiveEntity i : list ) {
    	   if (i instanceof PromotionalGiftEntity) {
        	PromotionalGift promotionalGiftDto = new PromotionalGift();
    		promotionalGiftDto.setInc_name(i.getInc_name());
    		listIncentive.add(promotionalGiftDto);
    	   }
    	   if ( i instanceof TrialPackageEntity) {
           	TrialPackage trialPackageDto = new TrialPackage();
       		trialPackageDto.setInc_name(i.getInc_name());
       		listIncentive.add(trialPackageDto);
    	   }
        }
        return listIncentive;
    }
}
