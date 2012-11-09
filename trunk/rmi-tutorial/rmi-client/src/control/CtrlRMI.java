package control;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import base.Compute;
import base.RmiStarter;
import base.Util;

public class CtrlRMI extends RmiStarter {

    private Compute compute;
    private String serverAddress;
	
    public CtrlRMI() {
		super(LoginTO.class);
		// TODO Auto-generated constructor stub
	}

	/**
     * Implementa��o do m�todo do RMIStarter para acesso aos m�todos remotos
     */
    @Override
    public void doCustomRmiHandling() {
        try {
        	Registry registry = LocateRegistry.getRegistry(Util.getServerAddress());
            compute = (Compute)registry.lookup(Compute.SERVICE_NAME);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

	public Compute getCompute() {
		return compute;
	}
	
	

}
