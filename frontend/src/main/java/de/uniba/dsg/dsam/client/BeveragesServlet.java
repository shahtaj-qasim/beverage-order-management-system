package de.uniba.dsg.dsam.client;
import java.io.*;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

public class BeveragesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(BeveragesServlet.class.getName());

	@EJB
	BeverageManagement bvm;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        req.setAttribute("beverageList", bvm.getAllBeverages());
        req.getRequestDispatcher("/main.jsp").forward(req, res);
        //req.getSession().setAttribute("forOrders", bvm.getAllBeverages());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		logger.info("Inside create function of beverage ");
         	try {
               // req.setAttribute("incentiveList", bvm.getAllIncentives());

         		Beverage b = new Beverage();
	        	b.setName(req.getParameter("name").trim());
	        	b.setManufacturer(req.getParameter("manufacturer").trim());
	        	b.setQuantity(Integer.valueOf(req.getParameter("quantity")));
	        	b.setPrice(Integer.valueOf(req.getParameter("price")));
	        	//String incentive_id = (req.getParameter("inc_id").trim());


	        	//logger.info(  "value of incentive id assigned: " + incentive_id);
	        	//if ( incentive_id != null) {

	        	//int inc_id = Integer.parseInt(incentive_id);
	        	//logger.info(  "value of incentive id not null and converted to int : " + inc_id);
	        	//bvm.create(b , inc_id);
	        	bvm.create(b);

	        	res.sendRedirect("/frontend/beverages");

        	}
        	catch(Exception e) {
        		logger.info(e.getMessage());
        	}
        }

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

	}
}
