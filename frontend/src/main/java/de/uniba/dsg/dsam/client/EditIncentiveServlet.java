package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;


public class EditIncentiveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EditIncentiveServlet.class.getName());
	
	@EJB
	private IncentiveManagement ivm;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if(req.getParameter("inc_id") != null) {
			try {
				int id = Integer.valueOf(req.getParameter("inc_id"));
				Incentive inc = ivm.getIncentive(id);
				logger.info("edit incentive with id : " + inc.getInc_id() + "  and name : " + inc.getInc_name());

				req.setAttribute("inc", inc);
				req.getRequestDispatcher("/edit.jsp").forward(req, res);
			} catch(NumberFormatException e) {
				logger.severe("Invalid data for incentive id: Must be int" + e);
			} 		
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			int id = Integer.valueOf(req.getParameter("inc_id"));
			String name = req.getParameter("inc_name").trim();

			ivm.update(id, name);
		} catch(NumberFormatException e) {
			logger.severe("Invalid data for id or number of employees: Must be int" + e);
		} 

		// redirect
		res.sendRedirect("/frontend/beverages/add_beverage/incentives");
	}	
}
