package fase2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Murati Monje
 */

public class Main {

	public static void main(String[] args) {
		
		List<String>urlEscritas = new ArrayList<>();
        
        
        for(int i=0; i<2; i++){
            
            Thread hilo = new Thread(new Hilo(i,urlEscritas));
            hilo.start();
        }

	}

}
