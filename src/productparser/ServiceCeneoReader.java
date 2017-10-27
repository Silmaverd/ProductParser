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

public class ServiceCeneoReader implements ServiceStrategy{                                    // The class is implementation of strategy for reading Ceneo data
    
    public URL getURLForNextPage(URL url, int page_nr){                                        // Function return url for next search page, from the given page
        String replaced = url.toString().replace(".htm", ";0020-30-0-0-" + page_nr + ".htm");  // Create formula
        URL newURL = null;
        try{
            newURL = new URL(replaced);                                                        // Check if URL is correct
        }catch(MalformedURLException ex){
            System.out.println("Malformed URL: "+newURL.toString());
        }
        return newURL;
    }
    
    public Item getItemFromTilesInterface(Element e) throws IOException{                    // Function returns an item read from ceneo tiles interface
        String title = e.select("strong.category-item-box-name a").text();                  // Read item name
        Elements priceElements = e.select("div.category-item-box-price a");
        String pricePrefix = priceElements.select("span.value").text();                     // Read item price preffix and suffix
        String priceSuffix = priceElements.select("span.penny").text();
        String price = pricePrefix + priceSuffix;                                           // Combine the item price
        String imageURL = "https:" + e.select("div.category-item-box-picture a").select("img").first().attr("data-original");  
            
        if (imageURL.equals("https:")){                                                     // Some products have different image coding          
            imageURL = e.select("div.category-item-box-picture a").select("img").first().attr("abs:src");
        }
            
        Image img = ImageConverter.convertStringToImage(imageURL);
        Item newItem = new Item(title, Double.parseDouble(price.replaceAll(",", ".")) , ImageConverter.convertImageToBase64String(img));
        System.out.println(newItem);
        return newItem;
    }
    
    public Item getItemFromListInterface(Element e) throws IOException{                     // Function returns an item read from ceneo list interface
        String title = e.select("div.cat-prod-row-price a").attr("title");
        Elements priceElements = e.select("div.cat-prod-row-price a");
        String pricePrefix = priceElements.select("span.value").text();                     // Read item price preffix and suffix
        String priceSuffix = priceElements.select("span.penny").text();
        String price = pricePrefix + priceSuffix;                                           // Combine the item price
        String imageURL = "https:" + e.select("div.add-to-favorite__container").select("span").first().attr("data-photourl");
        
        Image img = ImageConverter.convertStringToImage(imageURL);
        Item newItem = new Item(title, Double.parseDouble(price.replaceAll(",", ".")) , ImageConverter.convertImageToBase64String(img));
        System.out.println(newItem);
        return newItem;
    }
    
    @Override
    public ArrayList getItemsFromPageURL(URL url) throws IOException{                           
        ArrayList<Item> items = new ArrayList<>();                                              
        Document doc = Jsoup.connect(url.toString()).timeout(14000).get();                      // Connect to given URL
        Elements elementsTileInterface = doc.select("div.category-item-box");                   // Separate items by class divider
        Elements elementsListInterface = doc.select("div.cat-prod-row-price");
                                                                                                // Ceneo seems to contain two different types of interface for various product categories
        if (!elementsTileInterface.isEmpty()){                                                  // Determine an interface type of the category
            for(Element e: elementsTileInterface){                                              // Both types of interface have to be interpreted separatly
                Item item = getItemFromTilesInterface(e);
                items.add(item);
            }
        }else{
            for(Element e: elementsListInterface){
                Item item = getItemFromListInterface(e);
                items.add(item);
            }
        }
        
        return items;
    }
    
    public int getLastPageNr(Document doc){                                                     // Function returns number of last page in the search results
        Elements pagination = doc.select("div.pagination-top");
        String nrString = pagination.select("input").first().attr("data-pageCount");
        return Integer.parseInt(nrString);
    }
    
    @Override
    public ArrayList getItemsFromAllPages(URL pageURL) throws IOException{                      // Function lists through all pages in category, merging lists of items
        ArrayList combined = new ArrayList();
        Document doc = Jsoup.connect(pageURL.toString()).timeout(14000).get();                  // Connect to given URL
        int page_amount = getLastPageNr(doc);
        try{
            for (int page_nr = 0; page_nr < page_amount; page_nr++){                            // Switch pages until the last page is reached
                combined.addAll(getItemsFromPageURL(pageURL));
                pageURL = getURLForNextPage(pageURL, page_nr);
                System.out.println("Searching page "+Integer.toString(page_nr+1));
            }
        }catch(HttpStatusException endingException){                                            // ... or until page is not responding
            System.out.println("Page not responding");
        }
        return combined;
    }
}
