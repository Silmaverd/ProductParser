package productparser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ProductParser {                                                        // The class is responsible for supporting given strategy

    public ArrayList ParseProducts(ServiceStrategy strategy, URL address){
        ArrayList products = null;
        try{
            products = strategy.getItemsFromAllPages(address);
        }catch(IOException ex){
            
        }
        return products;
    }
    
}
