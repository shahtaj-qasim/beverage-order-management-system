package de.uniba.dsg.dsam.client;


import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import de.uniba.dsg.dsam.model.CustomerOrder;

@Stateless
public class QueueSender {
	  private static final Logger logger = Logger.getLogger(QueueSender.class.getName());

	    @Resource(mappedName = "BeverageStoreCF")
	    private ConnectionFactory factory;

	    @Resource(mappedName = "BeverageStoreQueue")
	    private Queue target;

	    public void sendMessage(CustomerOrder order) {
	        try (Connection connection = factory.createConnection()) {
	            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            MessageProducer producer = session.createProducer(target);
	            ObjectMessage message = session.createObjectMessage(order);
	            producer.send(message);
	            logger.info("msg sender");
	        } catch (JMSException ex) {
	            logger.severe("Could not send message to Queue" + ex);
	        }

	    }
}
