
package client;

import java.io.Serializable;
import java.math.BigDecimal;

import base.Task;


public class PI
    implements Task<BigDecimal>, Serializable {

    private static final long serialVersionUID = 3942967283733335029L;

       private final int digits;

    public PI(int digits) {
        this.digits = digits;
    }

    public BigDecimal execute() {

    	return new BigDecimal(1);
    }

   
    
}
