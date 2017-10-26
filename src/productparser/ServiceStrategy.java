package productparser;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface ServiceStrategy {
    
}

class ServiceCeneoReader implements ServiceStrategy{
    
    
    public LinkedList getItemsFromURL(URL url) throws IOException{
        LinkedList<Item> items = new LinkedList<>();                                            // results list
        Document doc = Jsoup.connect(url.toString()).timeout(10000).get();                      // connect to given URL
        Elements elements = doc.select("div.category-item-box");                                // separate items by class divider
        elements.forEach((e) -> {
            //System.out.println(e + "\n\n\n\n\n");
            String title = e.select("strong.category-item-box-name a").text();
            System.out.println(title);
            Elements priceElements = e.select("strong.category-item-box-price a");
            
        });
        
        return items;
    }
}
