package productparser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public interface ServiceStrategy {
    public ArrayList getItemsFromPageURL(URL url) throws IOException;                          // function returns list of items from a given Ceneo page
    public ArrayList getItemsFromAllPages(URL url) throws IOException;                         // function lists through all pages in category, merging lists of items
}
