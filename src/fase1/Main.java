package fase1;

import java.io.*;
import java.util.*;

/**
 * @author Adrian Murati Monje
 */

public class Main {

    
    public static void main(String[] args) throws IOException {
        
        List<String>aUrlEscritas = new ArrayList<>(); //This list will be common to all threads and contains the URLs written by all of them.
        
        //This for loop creates and starts each of the 3 threads.
        for(int i=0; i<3; i++){
            
            Thread hilo = new Thread(new Hilo(i,aUrlEscritas));
            hilo.start();
        }// for()
    }
    
}
