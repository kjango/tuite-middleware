package server;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import model.BaseTO;
import base.EnumPropertyJms;
import base.EnumRemoteObject;
import base.JmsService;

public class JmsServerTopic extends JmsService {

	private RmiServer rmiServer;
	
	public JmsServerTopic(String factory, String topic, String username, RmiServer rmiServer){
    	this.username = username;
    	this.rmiServer = rmiServer;
   	
    	try {
    		Initialize(factory, topic, username);
    		System.out.println("Listening Topic for JMS: " + topic);
    	} catch (Exception ex){
    		System.err.println(ex.toString());
    	}
	}
	
    // Recebe as mensagens do tópico assinado
    public void onMessage(Message message) {
    	//Deve ser extendida e implementada
    	//this.rmiServer.onMessage(message, this);
    	try {
    		String destination = message.getStringProperty(EnumPropertyJms.USERDESTINATION.toString());
    		//String source = message.getStringProperty(EnumPropertyJms.USERSOURCE.toString());
    		//EnumRemoteObject ero = EnumRemoteObject.valueOf(message.getStringProperty(EnumPropertyJms.TOPIC.toString()));
    		
    		if (destination.equals(username)){
    			//System.out.println("Message Jms from user: " + source);
    			//BaseTO baseTO =  (BaseTO)((ObjectMessage)message).getObject();
    			
    			//rmiServer.sendMessage(baseTO, ero, source);
    		}
    	} catch (Exception ex){
    		System.err.println(ex.toString());
    	}
    	
    	
    }

    public void writeMessage(BaseTO baseTO, EnumRemoteObject ero, String username){
    	try {
    	
			ObjectMessage message = pubSession.createObjectMessage();
			
			message.setObject(baseTO);
	        message.setStringProperty(EnumPropertyJms.USERSOURCE.toString(), "SERVER");
	        message.setStringProperty(EnumPropertyJms.USERDESTINATION.toString(), username);
	        message.setStringProperty(EnumPropertyJms.TOPIC.toString(), ero.toString());
    	
	        publisher.publish(message);
    	} catch (Exception ex){
    		System.err.println(ex.toString());
    	}
    	
    }
    
    // Cria a mensagem de texto e a publica no tópico. Evento referente ao produtor
    //public void writeMessage(ObjectMessage message) throws JMSException {
        //Deve ser extendida e implemetnada
    	//this.publisher.publish(message);
    //}
	
}
