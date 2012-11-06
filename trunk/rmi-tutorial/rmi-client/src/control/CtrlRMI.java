package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import base.Compute;
import base.RmiStarter;

public class CtrlRMI extends RmiStarter {

    private Compute compute;
    private String serverAddress;
	
    public CtrlRMI() {
		super(LoginTO.class);
		// TODO Auto-generated constructor stub
	}

	/**
     * Implementação do método do RMIStarter para acesso aos métodos remotos
     */
    @Override
    public void doCustomRmiHandling() {
        try {
        	Registry registry = LocateRegistry.getRegistry(getServerAddress());
            compute = (Compute)registry.lookup(Compute.SERVICE_NAME);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

	public Compute getCompute() {
		return compute;
	}
	
	public String getServerAddress(){
		String serverAddress = null;// = "localhost";
		String line = null;
		String[] splt;
		File f = new File("config.txt");
		
		try {
			//se o config.txt n existe, eu crio ele
			if (f.createNewFile()){
				FileWriter fw = new FileWriter(f);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("SERVER_ADDRESS=localhost");
				pw.close();
				fw.close();
			}
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null){
				if ((line != null) && (!line.equals("")) && (line.length() > 0))
                {
                    splt = line.split(":");

                    //evitar uma possível linha em branco
                    if ((splt != null) && (splt[0].equals("SERVER_ADDRESS")))
                    {
                    	serverAddress = splt[1];
                    }
                    break;
                 }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverAddress;
	}

}
