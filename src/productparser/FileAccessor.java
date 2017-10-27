package productparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileAccessor {                                                         // Singleton class is responsible for accessing file
    private File file;
    private static FileAccessor instance = null;
    private FileOutputStream fileOutStream;

    private FileAccessor(String type) {
        try{
            file = new File("products" + type);
            fileOutStream = new FileOutputStream(file, false);
        }catch(FileNotFoundException ex){
            
        }
    }
    
    public static FileAccessor getInstance(String type){                            // Since the accessor dont know what is file type of served file
        if (instance == null){                                                      // it provides the method of setting it for the strategy
            return new FileAccessor(type);
        }else return instance;
    }
    
    public FileOutputStream getFileOutputStream(){                                  // Returns OutputStream of served file
        return fileOutStream;
    }
}
