package productparser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {                                                             // Class represents a single item
    @XmlElement                                                                 // Contains its name, price and Base64-encoded Image
    private String name;                                                        // Annotations are required for JAXB to parse instance of object to XML
    @XmlElement
    private double price;
    @XmlElement
    public String base64EncodedImage;

    public Item(){
        
    }
    
    public Item(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.base64EncodedImage = image;
    }
    
    @Override
    public String toString(){
        return name+"\n"+price+"\n";
    }
}
