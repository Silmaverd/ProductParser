package productparser;

import java.util.ArrayList;

public interface FileFormatStrategy {
    public abstract void writeObjectToFile(Object o);                               // Writes single object to file
    public abstract void writeListToFile(ArrayList<Object> list);                   // Writes arraylist of objects to file
}
