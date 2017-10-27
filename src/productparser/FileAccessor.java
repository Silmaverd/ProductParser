package productparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileAccessor {
    private File file;
    private static FileAccessor instance = null;
    private FileOutputStream fileOutStream;

    private FileAccessor() {
        try{
            file = new File("products.xml");
            fileOutStream = new FileOutputStream(file, true);
        }catch(FileNotFoundException ex){
            
        }
    }
    
    public static FileAccessor getInstance(){
        if (instance == null){
            return new FileAccessor();
        }else return instance;
    }
    
    public FileOutputStream getFileOutputStream(){
        return fileOutStream;
    }
    
    public File getFile(){
        return file;
    }
}
