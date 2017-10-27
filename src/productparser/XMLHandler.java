package productparser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLHandler implements FileFormatStrategy{
    
    @Override
    public void writeObjectToFile(Object o) {
        FileAccessor accessor = FileAccessor.getInstance();             
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(o, accessor.getFileOutputStream());
        } catch (JAXBException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void writeListToFile(ArrayList list) {
        for(Object o: list){
            writeObjectToFile(o);
        }
    }
    
}
