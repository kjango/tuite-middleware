package control;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import base.Compute;
import base.RmiStarter;

public class CtrlRMI extends RmiStarter {

    private Compute compute;
	
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
        	Registry registry = LocateRegistry.getRegistry("localhost");
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
