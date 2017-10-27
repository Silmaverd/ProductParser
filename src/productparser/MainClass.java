package productparser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws IOException{
        URL address = null;    
        address = new URL(args[0]);
        
        ProductParser parser = new ProductParser();                                             // Create object of website parser
        ArrayList<Item> products = parser.ParseProducts(new ServiceCeneoReader(), address);     // Load a strategy to parser; determines what service going to be parsed
        
        FileManager.writeListToFile(products, new FormatXML());                                 // Save results to file; loads a strategy determining type of file
    }
}
