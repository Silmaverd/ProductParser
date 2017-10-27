package productparser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public interface ServiceStrategy {
    public ArrayList getItemsFromPageURL(URL url) throws IOException;                          // Function returns list of items from a given Ceneo page
    public ArrayList getItemsFromAllPages(URL url) throws IOException;                         // Function lists through all pages in category, merging lists of items
}
