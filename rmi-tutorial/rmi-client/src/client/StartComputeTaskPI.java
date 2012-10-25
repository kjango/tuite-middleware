
package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import base.Compute;
import base.RmiStarter;


/**
 * get the RMI Compute service and send a task to compute PI to N digits
 * 
 * @author srasul
 * 
 */
public class StartComputeTaskPI
    extends RmiStarter {

    public StartComputeTaskPI() {
        super(PI.class);
    }

    @Override
    public void doCustomRmiHandling() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            Compute compute = (Compute)registry.lookup(Compute.SERVICE_NAME);
            
            String returned = compute.decirHola();
            System.out.println(returned);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new StartComputeTaskPI();
    }
}
