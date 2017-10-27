package productparser;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ServiceCeneoReader implements ServiceStrategy{
    
    public URL getURLForNextPage(URL url, int page_nr){                                        // function return url for next search page, from the given page
        String replaced = url.toString().replace(".htm", ";0020-30-0-0-" + page_nr + ".htm");  // create formula
        URL newURL = null;
        try{
            newURL = new URL(replaced);                                                        // check if URL is correct
        }catch(MalformedURLException ex){
            System.out.println("Malformed URL: "+newURL.toString());
        }
        return newURL;
    }
    
    @Override
    public ArrayList getItemsFromPageURL(URL url) throws IOException{                           
        ArrayList<Item> items = new ArrayList<>();                                              
        Document doc = Jsoup.connect(url.toString()).timeout(10000).get();                      // connect to given URL
        Elements elements = doc.select("div.category-item-box");                                // separate items by class divider
        for (Element e: elements){
            //System.out.println(e + "\n\n\n\n\n");
            String title = e.select("strong.category-item-box-name a").text();                  // read item name
            Elements priceElements = e.select("div.category-item-box-price a");
            String pricePrefix = priceElements.select("span.value").text();                     // read item price preffix and suffix
            String priceSuffix = priceElements.select("span.penny").text();
            String price = pricePrefix + priceSuffix;                                           // combine the item price
            String imageURL = "https:" + e.select("div.category-item-box-picture a").select("img").first().attr("data-original");  
            
            if (imageURL.equals("https:")){                                                     // some products have different image coding          
                imageURL = e.select("div.category-item-box-picture a").select("img").first().attr("abs:src");
            }
            
            //System.out.println(title + "\n" + price.replaceAll(",", ".") + "\n" + imageURL + "\n\n\n");
            
            Image img = ImageConverter.convertStringToImage(imageURL);
            Item newItem = new Item(title, Double.parseDouble(price.replaceAll(",", ".")) , ImageConverter.convertImageToBase64String(img));
            items.add(newItem);
        };
        
        return items;
    }
    
    @Override
    public ArrayList getItemsFromAllPages(URL pageURL) throws IOException{                      // function lists through all pages in category, merging lists of items
        ArrayList combined = new ArrayList();
        int page_nr=1;
        try{
            while(true){                                                                        // switch pages until there is no valid next page
                combined.addAll(getItemsFromPageURL(pageURL));
                pageURL = getURLForNextPage(pageURL, page_nr);
                page_nr++;                                                                            
            }
        }catch(HttpStatusException endingException){                                            // catch the end of the searching
            
        }
        return combined;
    }
}
