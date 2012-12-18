package br.mb.tutorialActiveMQ.chat;

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


public class Chat implements MessageListener {
    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    private String username;

   
    /* Construtor usado para inicializar o cliente JMS do Chat */
    public Chat(String topicFactory, String topicName, String username) throws Exception {
    	
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
        try {
            // As mensagens criadas como TextMessage devem ser recebidas como TextMessage
        	
        	TextMessage textMessage = (TextMessage) message;
        	String text = textMessage.getText();
        	
        	System.out.println(DecodeObject(text).getText());
        	
        } catch (Exception jmse) {
            jmse.printStackTrace();
        }
    }

    public String EncodeObject(ObjTest obj){
    	
    	StringBuilder sb = new StringBuilder();
        try {
        	File file = File.createTempFile("temp", ".xml"); 
        	FileOutputStream os = new FileOutputStream(file);
        	XMLEncoder encoder = new XMLEncoder(os);
        	encoder.writeObject(obj);
        	encoder.close();
        	
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!= null){
                sb.append(line.trim());
            }
            br.close();
        	
        } catch (Exception e){
        	
        }
        return sb.toString();
    }
    
    public ObjTest DecodeObject(String str){
    	ObjTest objtest = new ObjTest();
    	
    	try {
    	File file = File.createTempFile("temp", ".xml");
    	FileWriter fileWriter = new FileWriter(file);
    	fileWriter.write(str);
    	fileWriter.close();
    	
    	FileInputStream fis = new FileInputStream(file);
    	XMLDecoder decoder = new XMLDecoder(fis);
    	objtest = (ObjTest)decoder.readObject();
  	
    	decoder.close();
    	} catch (Exception ex){
    		
    	}
    	
    	return objtest;
    }
    
    // Cria a mensagem de texto e a publica no t�pico. Evento referente ao produtor
    public void writeMessage(String text) throws JMSException {

   	
    	// Recebe um objeto da sessao para criar uma mensagem do tipo TextMessage
        TextMessage message = pubSession.createTextMessage();
        
        ObjTest mes = new ObjTest();
        mes.setText(text);
      
        // Seta no objeto a mensagem que ser� enviada
        //message.setText(username + ": " + text);
        message.setText(EncodeObject(mes));
        
        // Publica a mensagem no t�pico
        publisher.publish(message);
        
    }

    // Fecha a conex�o JMS
    public void close() throws JMSException {
        connection.close();
    }

    // Roda o Chat
    public static void main(String[] args) {
        try {
            //Habilita o envio de mensagens por linha de comando
            Scanner commandLine = new Scanner(System.in);

            System.out.print("Digite seu nome: ");
            String name = commandLine.nextLine();
            // Faz uma chamada ao construtor da classe para iniciar o chat
            Chat chat = new Chat("TopicCF", "topicChat", name);
            // Depois da conex�o criada, faz um loop para enviar mensagens
            while (true) {
                //captura a mensagem digitada no console
                String s = commandLine.nextLine();
                //para sair digite exit
                if (s.equalsIgnoreCase("exit")) {
                    //fecha a conex�o com o provedor
                    chat.close();
                    //sai do sistema
                    System.exit(0);
                } else {
                    //envia a mensagem digitada no console para o m�todo writeMessage que ir� publica-la
                    chat.writeMessage(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}