package productparser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Item {
    @XmlElement
    private String name;
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
}
