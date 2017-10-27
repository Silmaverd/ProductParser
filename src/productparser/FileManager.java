package productparser;

import java.util.ArrayList;

public class FileManager {                                                              
    public static void writeObjectToFile(Object o, FileFormatStrategy strategy){
        strategy.writeObjectToFile(o);
    }
    
    public static void writeListToFile(ArrayList list, FileFormatStrategy strategy){
        strategy.writeListToFile(list);
    }
}
