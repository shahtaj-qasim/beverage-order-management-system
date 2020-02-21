package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

/**
 * Servlet implementation class QueueFillerServlet
 */
public class QueueFillerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static final Logger logger = Logger.getLogger(QueueFillerServlet.class.getName());
	
	
	@EJB
	BeverageManagement bvm;
	
	@EJB
	QueueSender sender = new QueueSender();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueueFillerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		 
        req.setAttribute("beverageList", bvm.getAllBeverages());
        req.getRequestDispatcher("/add_order.jsp").forward(req, res);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		CustomerOrder order = new CustomerOrder();
		
		Date date = new Date();
		order.setIssueDate(date);
	
		
		for(int i = 0; i<Integer.parseInt(request.getParameter("length")); i++) {
			
		   Beverage beverage = new Beverage();
		   String reqString =request.getParameter(Integer.toString(i));
		   String[] part = reqString.split("(?<=\\D)(?=\\d)");
		   System.out.println(part[0]);
		   System.out.println(part[1]);
		   int soldQuant = Integer.parseInt(part[1]);
		   String name = part[0];
		   beverage.setName(name);
		   beverage.setQuantity(soldQuant);
		   order.getOrderItems().add(beverage);	
			
		   logger.info(request.getParameter(Integer.toString(i)));
		}
	
		
		sender.sendMessage(order);
	    // redirect
	    response.sendRedirect("/frontend/neworder");
	}

}
