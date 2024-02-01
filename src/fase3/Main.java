package fase3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Adrian Murati Monje
 */
public class Main {

	public static void main(String[] args) {
		
        // This string stores the html to be cleaned.
		String sHtml = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"es\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Página web sencilla</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<header>\r\n"
				+ "<h1>Página web sencilla</h1>\r\n"
				+ "</header>\r\n"
				+ "<main>\r\n"
				+ "<ol>\r\n"
				+ "<li><a href=\"https://www.google.com\">Google</a></li>\r\n"
				+ "<li><a href=\"https://www.facebook.com\">Facebook</a></li>\r\n"
				+ "<li><a href=\"https://www.twitter.com\">Twitter</a></li>\r\n"
				+ "</ol>\r\n"
				+ "<ul>\r\n"
				+ "<li>Lista desordenada</li>\r\n"
				+ "<li>Con dos elementos</li>\r\n"
				+ "<li>Y un enlace</li>\r\n"
				+ "<li><a href=\"https://www.wikipedia.org\">Wikipedia</a></li>\r\n"
				+ "</ul>\r\n"
				+ "<table>\r\n"
				+ "<thead>\r\n"
				+ "<tr>\r\n"
				+ "<th>Título</th>\r\n"
				+ "<th>Descripción</th>\r\n"
				+ "</tr>\r\n"
				+ "</thead>\r\n"
				+ "<tbody>\r\n"
				+ "<tr>\r\n"
				+ "<td>Elemento 1</td>\r\n"
				+ "<td>Descripción del elemento 1</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "<td>Elemento 2</td>\r\n"
				+ "<td>Descripción del elemento 2</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr>\r\n"
				+ "<td>Elemento 3</td>\r\n"
				+ "<td>Descripción del elemento 3</td>\r\n"
				+ "</tr>\r\n"
				+ "</tbody>\r\n"
				+ "</table>\r\n"
				+ "</main>\r\n"
				+ "<footer>\r\n"
				+ "<p>Página web creada por Bard</p>\r\n"
				+ "</footer>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
        // This string will receive the initial html and will be cleaned using different methods
		String sHtmlLimpio = quitarEtiquetas(sHtml);
		sHtmlLimpio = quitarSignosDePuntuacion(sHtmlLimpio);
		sHtmlLimpio = quitarPreposiciones_Conjunciones_Espacios(sHtmlLimpio);
		
		escribirHtmlLimpio(sHtmlLimpio); // Write the clean html using the writeCleanHtml method
		
		
	}
	
    // This method removes the html tags
	private static String quitarEtiquetas(String html) {
		
		boolean bEscribir = true; // This boolean will determine when the loop writes or not
		String sHtmlLimpio = ""; // This String will store the html without tags
		
		
		 /*
         * This for loop will write as long as the write boolean is true.
         * It will become false if it finds a "<" and will become true again when it finds a ">" which will prevent writing the tags.
         */
		for(int i=0; i<html.length(); i++) {
			
			if(html.charAt(i)=='<') {
				
				bEscribir = false;
				
			}
			
			if(bEscribir == true) {
				
				sHtmlLimpio += html.charAt(i);
			}
			
			if(html.charAt(i)=='>') {
				
				bEscribir = true;
			}
			
			
		}
		
		return sHtmlLimpio; // return the result
		
	}// private static String quitarEtiquetas()
	
	// This method removes punctuation marks.
	private static String quitarSignosDePuntuacion(String html) {
		
		String sResultado = html.replaceAll(",.!¡?¿';:()", ""); //Este String recibirá el html y le quitará los signos de puntuación usando el método replaceAll
		return sResultado; // return the result
	}// private static String quitarSignosDePuntuacion()
	
    // This method removes prepositions, conjunctions, and unnecessary spaces.
	private static String quitarPreposiciones_Conjunciones_Espacios(String html) {
		
		
		List<String> aPreposiciones = new ArrayList<>(); // This list will store the prepositions
		aPreposiciones.add("a");
        aPreposiciones.add("ante");
        aPreposiciones.add("bajo");
        aPreposiciones.add("con");
        aPreposiciones.add("de");
        aPreposiciones.add("desde");
        aPreposiciones.add("en");
        aPreposiciones.add("entre");
        aPreposiciones.add("hacia");
        aPreposiciones.add("hasta");
        aPreposiciones.add("para");
        aPreposiciones.add("por");
        aPreposiciones.add("según");
        aPreposiciones.add("sin");
        aPreposiciones.add("sobre");
        aPreposiciones.add("tras");
        
        ArrayList<String> aConjunciones = new ArrayList<>(); // This list will store the conjunctions
        aConjunciones.add("y");
        aConjunciones.add("o");
        aConjunciones.add("pero");
        aConjunciones.add("porque");
        aConjunciones.add("si");
        aConjunciones.add("cuando");
        aConjunciones.add("aunque");
		
        String sAux = html.toLowerCase(); // This auxiliary String will receive the html in lowercase.
        String sArrayAux[] = sAux.split("\\s"); // This auxiliary array will be filled using split taking any whitespace from the auxiliary String as reference.
        String resultado = "";  // This String will store the result
        
        // This for loop only writes when certain conditions are not met
        for(int i=0; i<sArrayAux.length; i++) {
        	
            // If the current position of the array contains prepositions or conjunctions, it will not write
        	if(aPreposiciones.contains(sArrayAux[i]) || aConjunciones.contains(sArrayAux[i])) {
        		
        		
        		
        	}else {
        		
                // Check if the current position has at least 1 character to remove whitespace.
        		if(sArrayAux[i].length()>0) {
        			resultado += sArrayAux[i];
            		resultado += " ";
        			
        		}// if(sArrayAux[i].length()>0)
        		
        		
        	}// else()
        	
        }// for()
       
        return resultado;
		
		
	}
	
    // This method writes the clean html in a new text file.
	private static void escribirHtmlLimpio(String html) {
		
		try {
			FileWriter writer = new FileWriter("htmlLimpio.txt");
			
			for(int i=0; i<html.length(); i++) {
				
				writer.write(html.charAt(i));
			}
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("El html ha sido limpiado con éxito");
		
		
	}

}
