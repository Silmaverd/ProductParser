package productparser;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageConverter {
    
    public static void displayImage(Image img){                                 // test function displaying image in new Frame
        JFrame testFrame = new JFrame();
        testFrame.setSize(500, 300);
        JLabel canvas = new JLabel();
        canvas.setSize(500, 300);
        testFrame.add(canvas);
        ImageIcon imgIcon = new ImageIcon(img);
        canvas.setIcon(imgIcon);
        testFrame.setVisible(true);
    }
    
    public static Image convertStringToImage(String url){
        URL urlFromString = null;
        try{
            urlFromString = new URL(url);
        }catch(MalformedURLException ex){
            System.out.println("Malformed URL: "+ url.toString());
        }finally{
            return ImageConverter.convertURLToImage(urlFromString);
        }
    }
    
    public static Image convertURLToImage(URL url){
        Image image = null;
        try{
            image = ImageIO.read(url);
        }catch(IOException ex){
            System.out.println("IOException while loading image");
        }finally{
            return image;
        }
    }
    
    public static Base64 convertImageToBase64(Image image){
        
        return null;
    }
}
