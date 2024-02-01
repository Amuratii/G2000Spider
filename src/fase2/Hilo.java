package fase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adrian Murati Monje
 */

public class Hilo implements Runnable{
	
	List<String> aUrlEscritas; // This list is common for all threads and will store the URLs written by all of them
    List<String> aUrlHilo = new ArrayList<>(); // This list will store the URLs written by the thread
    int iNumHilo; // This int represents the thread number
    BufferedWriter writer; // This object will be used to write the HTML
    
    // Constructor of the class
    public Hilo(int numHilo, List<String>urlEscritas){
        
        this.iNumHilo = numHilo;
        this.aUrlEscritas = urlEscritas;
    }// public Hilo()

    // Thread's run method
	@Override
	public void run() {
		
		System.out.println("Hola");
	      
        try {
            BufferedReader reader = new BufferedReader(new FileReader("seeds2.txt")); // Reader object that will read URLs from the seeds2.txt file
            
            String sLinea;// This string will be used to read the lines from the file
            
            esperar(); // Invoke the wait method to synchronize the threads
            
            // This while loop will read lines until it returns null.
            while((sLinea = reader.readLine()) != null){
                
                // If the URL has not been written, it adds it to the threadUrls and writtenUrls lists
                if(!urlEncontrada(sLinea)){
                	
                	aUrlHilo.add(sLinea);
            		aUrlEscritas.add(sLinea);
                	
                	esperar();
             
                }
                
            }
            
            System.out.println("Hilo "+iNumHilo+" "+aUrlHilo); // This print shows the URLs associated with each thread
            
            // Close the reader
            reader.close(); 
           
            
            
            
        } catch (Exception ex) {
            
            System.out.println(ex);
        }// try catch()
        
        List<String>aHtmlUrls = new ArrayList<>(); // This list will store the different HTMLs of the URLs written by this thread
        
        // In this for loop, the different URLs are downloaded and the result is added to the htmlUrls list
        for(int i=0; i<aUrlHilo.size(); i++) {
        	
        	try {
        		
        		HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                         .uri(URI.create("https://"+aUrlHilo.get(i)))
                         .GET()
                         .build();
                   
                   
                   try {
                   HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
                   String html = httpResponse.body();
                   aHtmlUrls.add(html);
                 } catch (IOException | InterruptedException e) {
                   e.printStackTrace();
                 } 
        		
        		
        	}catch(Exception e) {
        		
        		
        	}// try catch()
        	
        	
        }// for()
        
        // This for loop writes the HTMLs, using the URL as the title of the text file.
        for(int i=0; i<aUrlHilo.size(); i++) {
        	
        	try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(aUrlHilo.get(i)+".txt"));
				writer.write(aHtmlUrls.get(i));
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // try catch()
        	
        } // for()
        
        
		
	} // public void run()
	
    // This method checks if the URL has already been found
	private synchronized boolean urlEncontrada(String url){
        
        return aUrlEscritas.contains(url);
    	
    }// private boolean urlEncontrada()
	
	private void esperar(){
        
        try {
        	Thread.sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
