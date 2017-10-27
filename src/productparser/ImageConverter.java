package productparser;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.bind.DatatypeConverter;

public class ImageConverter {                                                   // The class provides functions for image convertions
    
    public static void displayImage(Image img){                                 // Test function displaying image in new Frame
        JFrame testFrame = new JFrame();
        testFrame.setSize(500, 300);
        JLabel canvas = new JLabel();
        canvas.setSize(500, 300);
        testFrame.add(canvas);
        ImageIcon imgIcon = new ImageIcon(img);
        canvas.setIcon(imgIcon);
        testFrame.setVisible(true);
    }
    
    public static Image convertStringToImage(String url){                       // The functions returns an Image downloaded from the URL given in argument
        URL urlFromString = null;
        try{
            urlFromString = new URL(url);
        }catch(MalformedURLException ex){
            System.out.println("Malformed URL: "+ url.toString());
        }finally{
            return ImageConverter.convertURLToImage(urlFromString);
        }
    }
    
    public static Image convertURLToImage(URL url){                             // The functions returns an Image downloaded from the URL given in argument
        Image image = null;
        try{
            image = ImageIO.read(url);
        }catch(IOException ex){
            System.out.println("IOException while loading image");
        }finally{
            return image;
        }
    }
    
    public static String convertImageToBase64String(Image image) throws IOException{        // The function converts Image given in argument to Base64-encoded string
        String encoded = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", output);
        DatatypeConverter.printBase64Binary(output.toByteArray());
        encoded = DatatypeConverter.printBase64Binary(output.toByteArray());
        return encoded;
    }
    
    public static Image convertBase64StringToImage(String encoded){                         // Test function for decoding encoded Image
        byte[] bytes = encoded.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(Base64.getDecoder().decode(encoded));
        Image image;
        try{
            image = ImageIO.read(input);
        }catch(IOException ex){
            System.out.println("Catched IOException");
            return null;
        }
        return image;
    }
}
