package control;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import model.BaseTO;
import model.LoginTO;
import model.User;
import base.EnumPropertyJms;
import base.EnumRemoteObject;
import base.JmsService;
import base.RemoteNotify;
import base.Util;

public class JmsClientTopic extends JmsService {

	private RemoteNotify ctrlGen;
	
    public JmsClientTopic(BaseTO baseTO, RemoteNotify ctrlGen){
    	this.ctrlGen = ctrlGen;
    	
    	try {
    		
    	if (ctrlGen.getClass().equals(CtrlLogin.class)){
    		Initialize("TopicCF", Util.TOPICLOGIN, ((LoginTO)baseTO).getUserLogin());    	
    	}
    	if (ctrlGen.getClass().equals(CtrlTuite.class)){
    		Initialize("TopicCF", Util.TOPICTUITE, ((User)baseTO).getLoginName());    
    	}
    	if (ctrlGen.getClass().equals(CtrlUser.class)){
    		Initialize("TopicCF", Util.TOPICFOLLOW,  ((User)baseTO).getLoginName());    		
    	}
    		
    	} catch (Exception ex){
    		System.err.println(ex.toString());
    	}
    	
    }
    
    // Recebe as mensagens do tópico assinado
    public void onMessage(Message message) {
        try {
            // As mensagens criadas como TextMessage devem ser recebidas como TextMessage
        	//TextMessage textMessage = (TextMessage) message;
        	//ObjectMessage objectMessage = (ObjectMessage)message;
        	
        	//String xmlReturn = textMessage.getText();
        	String destination = message.getStringProperty(EnumPropertyJms.USERDESTINATION.toString());
        	
        	if (destination.equals(username)){
        		ctrlGen.update(username, username);
        	}
        } catch (Exception jmse) {
            jmse.printStackTrace();
        }
        
    }

	public void sendMessage(BaseTO baseTO, EnumRemoteObject ero, String username){
		
		try{
			//TextMessage message = pubSession.createTextMessage();
	        
			ObjectMessage message = pubSession.createObjectMessage();
	        // Seta no objeto a mensagem que será enviada
			message.setObject(baseTO);
	        message.setStringProperty(EnumPropertyJms.USERSOURCE.toString(), username);
	        message.setStringProperty(EnumPropertyJms.USERDESTINATION.toString(), "SERVER");
	        message.setStringProperty(EnumPropertyJms.TOPIC.toString(), ero.toString());
	        
	        // Publica a mensagem no tópico
	        publisher.publish(message);
	    	
	    	} catch (Exception ex){
	    		System.err.println(ex.toString());
	    	}
		
	}
    

    
}