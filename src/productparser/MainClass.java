package productparser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws MalformedURLException{
        URL address = null;    
        //address = new URL(args[0]);
        address = new URL("http://www.ceneo.pl/Gitary;017P1-1222239P2-169175.htm");
        //address = new URL("http://www.ceneo.pl/Filmy_Blu-ray;017Sensacyjny_P12-43274.htm");
        
        ProductParser parser = new ProductParser();
        ArrayList<Item> products = parser.ParseProducts(new ServiceCeneoReader(), address);
        
        for(Item item: products){
            System.out.println(item);
        }
    }
}
