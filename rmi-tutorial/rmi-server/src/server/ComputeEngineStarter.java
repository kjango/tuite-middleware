
package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import base.Compute;
import base.RmiStarter;


/**
 * start the server component. this exposes the an implementation of the Compute interface as a service over RMI
 * 
 * @author srasul
 * 
 */
public class ComputeEngineStarter
    extends RmiStarter {
    
    public ComputeEngineStarter() {
        super(Compute.class);
    }

    @Override
    public void doCustomRmiHandling() {
        try {
            Compute engine = new ComputeEngine();
            Compute engineStub = (Compute)UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(Compute.SERVICE_NAME, engineStub);            
            
            System.out.println("RMI Server is ready for use...");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ComputeEngineStarter();
    }
}
