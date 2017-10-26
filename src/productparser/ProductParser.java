package productparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductParser {

    public static void main(String[] args) throws IOException{
        URL address;
        try{
            address = new URL(args[0]);
            ServiceCeneoReader scr = new ServiceCeneoReader();
            scr.getItemsFromURL(address);
        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        
    }
    
}
