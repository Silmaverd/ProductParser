package productparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileAccessor {                                                         // Singleton class is responsible for accessing file
    private File file;
    private static FileAccessor instance = null;
    private FileOutputStream fileOutStream;

    private FileAccessor() {
        try{
            file = new File("products");
            fileOutStream = new FileOutputStream(file, true);
        }catch(FileNotFoundException ex){
            
        }
    }
    
    public static FileAccessor getInstance(){
        if (instance == null){
            return new FileAccessor();
        }else return instance;
    }
    
    public FileOutputStream getFileOutputStream(){                                  // Returns OutputStream of served file
        return fileOutStream;
    }
    
    public void setFileType(String type){                                           // Since the accessor dont know what is file type of served file
        File newF = new File(file.getName() + type);                                // it provides the method of setting it for the strategy
        file.renameTo(newF);
        newF.delete();
    }
}
