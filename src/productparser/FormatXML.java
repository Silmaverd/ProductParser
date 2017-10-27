package productparser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class FormatXML implements FileFormatStrategy{                                   // The class is strategy for saving data to file
    FileAccessor accessor;
    
    public FormatXML(){                                                                 // Constructor gets the instance of file accessor
        accessor = FileAccessor.getInstance(".xml");                                    // and sets the file type
    }
    
    @Override
    public void writeObjectToFile(Object o) {                                           // Function saves single object to an XML file             
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);              // Saving to XML uses the JAXB library
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(o, accessor.getFileOutputStream());
        } catch (JAXBException ex) {
            Logger.getLogger(FormatXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void writeListToFile(ArrayList list) {                                       // Function writes while ArrayList to an XML File              
        try{
            for(Object o: list){
                writeObjectToFile(o);
            }
        }catch(NullPointerException ex){                                                // In case the list was damaged break the loop
            System.out.println("Error while reading product list");                     // i.e. due to error while reading the data
        }
    }
    
}
