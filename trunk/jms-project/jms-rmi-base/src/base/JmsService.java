package base;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;


public class JmsService implements MessageListener {
    protected TopicSession pubSession;
    protected TopicPublisher publisher;
    protected TopicConnection connection;
    protected String username;
    protected String topicName;
   
    /* Construtor usado para inicializar o cliente JMS do Chat */
    protected void Initialize(String topicFactory, String topicName, String username) throws Exception {
    	this.topicName = topicName;
    	// Obt�m os dados da conex�o JNDI atrav�s do arquivo jndi.properties
        InitialContext ctx = new InitialContext();
        
        // O cliente utiliza o TopicConnectionFactory para criar um objeto do tipo TopicConnection com o provedor JMS
        TopicConnectionFactory conFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
        
        // Utiliza o TopicConnectionFactory para criar a conex�o com o provedor JMS
        TopicConnection connection = conFactory.createTopicConnection();
        
        //Cria os topicos necessarios
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);
        
        // Utiliza o TopicConnection para criar a sess�o para o produtor
        // Atributo false -> uso ou n�o de transa��es(tratar uma s�rie de envios/recebimentos como unidade at�mica e
        // control�-la via commit e rollback)
        // Atributo AUTO_ACKNOWLEDGE -> Exige confirma��o autom�tica ap�s recebimento correto
        TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Utiliza o TopicConnection para criar a sess�o para o consumidor
        TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Pesquisa o destino do t�pico via JNDI
        Topic chatTopic = (Topic) ctx.lookup(topicName);
        
        // Cria o t�pico JMS do produtor das mensagens atrav�s da sess�o e o nome do t�pico
        TopicPublisher publisher = pubSession.createPublisher(chatTopic);
        
        // Cria(Assina) o t�pico JMS do consumidor das mensagens atrav�s da sess�o e o nome do t�pico
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);
        
        // Escuta o t�pico para receber as mensagens atrav�s do m�todo onMessage()
        subscriber.setMessageListener(this);
        
        // Inicializa as variaveis do Chat
        this.connection = connection;
        this.pubSession = pubSession;
        this.publisher = publisher;
        this.username = username;
        
        // Inicia a conex�o JMS, permite que mensagens sejam entregues
        connection.start();
    }

    // Recebe as mensagens do t�pico assinado
    public void onMessage(Message message) {
    	//Deve ser extendida e implementada
    }

    // Cria a mensagem de texto e a publica no t�pico. Evento referente ao produtor
    public void writeMessage(String text) throws JMSException {
        //Deve ser extendida e implemetnada
    }

    //public void writeMessage(TextMessage message) throws JMSException {
    	//for topic
    //}
    
    //public void doMessage(Message message, JmsService jmsTopic) {
    	//for server
    //}
    
    // Fecha a conex�o JMS
    public void close() throws JMSException {
        connection.close();
    }
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
   
    
    
}