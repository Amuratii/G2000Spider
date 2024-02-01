package fase4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                + "<title>Simple Web Page</title>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "<header>\r\n"
                + "<h1>Simple Web Page</h1>\r\n"
                + "</header>\r\n"
                + "<main>\r\n"
                + "<ol>\r\n"
                + "<li><a href=\"https://www.google.com\">Google</a></li>\r\n"
                + "<li><a href=\"https://www.facebook.com\">Facebook</a></li>\r\n"
                + "<li><a href=\"https://www.twitter.com\">Twitter</a></li>\r\n"
                + "</ol>\r\n"
                + "<ul>\r\n"
                + "<li>Unordered list</li>\r\n"
                + "<li>With two elements</li>\r\n"
                + "<li>And a link</li>\r\n"
                + "<li><a href=\"https://www.wikipedia.org\">Wikipedia</a></li>\r\n"
                + "</ul>\r\n"
                + "<table>\r\n"
                + "<thead>\r\n"
                + "<tr>\r\n"
                + "<th>Title</th>\r\n"
                + "<th>Description</th>\r\n"
                + "</tr>\r\n"
                + "</thead>\r\n"
                + "<tbody>\r\n"
                + "<tr>\r\n"
                + "<td>Item 1</td>\r\n"
                + "<td>Description of item 1</td>\r\n"
                + "</tr>\r\n"
                + "<tr>\r\n"
                + "<td>Item 2</td>\r\n"
                + "<td>Description of item 2</td>\r\n"
                + "</tr>\r\n"
                + "<tr>\r\n"
                + "<td>Item 3</td>\r\n"
                + "<td>Description of item 3</td>\r\n"
                + "</tr>\r\n"
                + "</tbody>\r\n"
                + "</table>\r\n"
                + "</main>\r\n"
                + "<footer>\r\n"
                + "<p>end of WebPage</p>\r\n"
                + "</footer>\r\n"
                + "</body>\r\n"
                + "</html>";
		
        // For the following code, I have used the external library Jsoup, which allows working with HTML
		
		Document documento = Jsoup.parse(sHtml); // This Document object will receive the parsed html, or in other words, analyzed
		Elements links = documento.select("a[href]"); // This Elements object is a list that selects all <a> elements with the href attribute
		
        // This for loop extracts the content of the attributes from the previously generated list and displays it on the screen.
		for(int i=0; i<links.size(); i++) {
			
			System.out.println(links.get(i).attr("href"));
		}

	}

}
