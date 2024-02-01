package fase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adrian Murati Monje
 */

public class Hilo implements Runnable{
    
    List<String> aUrlEscritas; //This list is common to all threads, to keep track of the written URLs.
    List<String> aUrlHilo = new ArrayList<>(); //This list will contain the URLs written by this thread.
    int iNumHilo; //This int represents the thread number.
    String aArchivosEscribir[] = {"thread1.txt", "thread2.txt", "thread3.txt"}; //This array will be used to determine the name of the writing file based on the thread
    
    
    //Constructor of the class to which the thread number and the common list are passed
    public Hilo(int numHilo, List<String>urlEscritas){
        
        this.iNumHilo = numHilo;
        this.aUrlEscritas = urlEscritas;
    }//public Hilo()

    //Metodo run del hilo
    @Override
    public void run() {
        
        System.out.println("Hola");
      
        
        try {
    	    // Create BufferedReader and BufferedWriter objects to read and write URLs
            BufferedReader reader = new BufferedReader(new FileReader("seeds.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(aArchivosEscribir[iNumHilo]));
            
            String sLinea; // This string variable will store the lines of the BufferedReader
            
            esperar(); //Invoke the waitFor method before entering the loop to synchronize the threads
            
            /* This while loop will read new lines from the seeds file until the line is null or the thread has already written 5 URLs.
             * The latter is for convenience when checking for repeated elements, it could be removed and it would work perfectly fine.
             */
            while((sLinea = reader.readLine()) != null && aUrlHilo.size()<5){
                
            // In this if statement, the urlFound method is called to check if the URL has already been written. If it is not found, it is added to the urlThread and urlWritten lists.
            if(!urlEncontrada(sLinea)){
                	
                	aUrlHilo.add(sLinea);
            		aUrlEscritas.add(sLinea);
                	
                	esperar();
             
                }
                
            } //while()
            
            System.out.println("Hilo "+iNumHilo+" "+aUrlHilo);// This print shows the URLs associated with each thread
            
            // This for loop writes the URLs associated with the thread in the corresponding txt file
            for(int i=0; i<aUrlHilo.size(); i++) {
            	
            	escribirURL(aUrlHilo.get(i),writer);
            } //for()
            
            // Close the reader and the writer.
            reader.close();
            writer.close();
            
        } catch (Exception ex) {
            
            System.out.println(ex);
        } // try catch()
        
    }//public void run()
    
    // This method checks if the URL has already been found
    private synchronized boolean urlEncontrada(String url){
        
    	
        return aUrlEscritas.contains(url);
    	
    }// private boolean urlEncontrada()
    
    //Este método escribe las URL en un txt
    private synchronized void escribirURL(String url, BufferedWriter writer) throws IOException{
        
        
        writer.write(url);
        writer.newLine();
        
    }// private void escribirURL
    
    // This method writes the URL in a txt file
    private void esperar(){
        
        try {
        	Thread.sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }// try catch()
    }// private void esperar()
    
}
