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
    	// Obtém os dados da conexão JNDI através do arquivo jndi.properties
        InitialContext ctx = new InitialContext();
        
        // O cliente utiliza o TopicConnectionFactory para criar um objeto do tipo TopicConnection com o provedor JMS
        TopicConnectionFactory conFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
        
        // Utiliza o TopicConnectionFactory para criar a conexão com o provedor JMS
        TopicConnection connection = conFactory.createTopicConnection();
        
        //Cria os topicos necessarios
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);
        
        // Utiliza o TopicConnection para criar a sessão para o produtor
        // Atributo false -> uso ou não de transações(tratar uma série de envios/recebimentos como unidade atômica e
        // controlá-la via commit e rollback)
        // Atributo AUTO_ACKNOWLEDGE -> Exige confirmação automática após recebimento correto
        TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Utiliza o TopicConnection para criar a sessão para o consumidor
        TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Pesquisa o destino do tópico via JNDI
        Topic chatTopic = (Topic) ctx.lookup(topicName);
        
        // Cria o tópico JMS do produtor das mensagens através da sessão e o nome do tópico
        TopicPublisher publisher = pubSession.createPublisher(chatTopic);
        
        // Cria(Assina) o tópico JMS do consumidor das mensagens através da sessão e o nome do tópico
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);
        
        // Escuta o tópico para receber as mensagens através do método onMessage()
        subscriber.setMessageListener(this);
        
        // Inicializa as variaveis do Chat
        this.connection = connection;
        this.pubSession = pubSession;
        this.publisher = publisher;
        this.username = username;
        
        // Inicia a conexão JMS, permite que mensagens sejam entregues
        connection.start();
    }

    // Recebe as mensagens do tópico assinado
    public void onMessage(Message message) {
    	//Deve ser extendida e implementada
    }

    // Cria a mensagem de texto e a publica no tópico. Evento referente ao produtor
    public void writeMessage(String text) throws JMSException {
        //Deve ser extendida e implemetnada
    }

    //public void writeMessage(TextMessage message) throws JMSException {
    	//for topic
    //}
    
    //public void doMessage(Message message, JmsService jmsTopic) {
    	//for server
    //}
    
    // Fecha a conexão JMS
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