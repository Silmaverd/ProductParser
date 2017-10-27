package productparser;

import java.util.ArrayList;

public interface FileFormatStrategy {
    public abstract void writeObjectToFile(Object o);                               // writes single object to file
    public abstract void writeListToFile(ArrayList<Object> list);                   // writes arraylist of objects to file
}
