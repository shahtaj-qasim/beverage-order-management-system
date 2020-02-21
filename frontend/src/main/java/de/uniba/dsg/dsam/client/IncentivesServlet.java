package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;;

/**
 * Servlet implementation class IncentivesServlet
 */
public class IncentivesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private static final Logger logger = Logger.getLogger(IncentivesServlet.class.getName());
	
	@EJB
	private IncentiveManagement ivm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncentivesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		 // Display the list of incentives:
		logger.info("Inside get list function of incentive ");

        req.setAttribute("incentiveList", ivm.getAllIncentives());
		logger.info("Inside get list function of incentive " + ivm.getAllIncentives().toString());
        req.getRequestDispatcher("/manage_incentives.jsp").forward(req, res);

    	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		logger.info("Inside create function of incentive ");
         	try {
         		Incentive i = null;
         	
         		String name = (req.getParameter("name").trim());
	        	String type = (req.getParameter("type"));
	        	if ( type.equals("PromotionalGift")  ) {
         			i = new PromotionalGift();
	        		i.setInc_name(name);
	        	}
	        	else if( type.equals("TrialPackage")){
	        		i = new TrialPackage();
	        		i.setInc_name(name);
	        	}
	        	ivm.create(i);

	            req.setAttribute("incentiveList", ivm.getAllIncentives());
	            req.getRequestDispatcher("/add_beverage.jsp").forward(req, res);
	        	res.sendRedirect("/frontend/beverages/add_beverage");

        	}
        	catch(Exception e) {
        		logger.info(e.getMessage());
        	}
        }

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			int inc_id = Integer.valueOf(req.getParameter("inc_id"));
			
			ivm.delete(inc_id);
		} catch(NumberFormatException e) {
			logger.severe("Invalid data for incentive id : Must be int" + e);
		} 
	}

	
}
