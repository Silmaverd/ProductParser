package productparser;

import java.awt.Image;
import java.util.Base64;

public class Item {
    private String name;
    private double price;
    private Image image;

    public Item(String name, double price, Image image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
     
    public Base64 convertImageToBase64(){
        
        return null;
    }
}
