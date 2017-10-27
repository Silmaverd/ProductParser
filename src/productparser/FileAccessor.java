package productparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileAccessor {
    private File file;
    private static FileAccessor instance = null;

    private FileAccessor() {
        file = new File("products.xml");
    }
    
    public static FileAccessor getInstance(){
        if (instance == null){
            return new FileAccessor();
        }else return instance;
    }
    
    public FileOutputStream getFileOutputStream() throws FileNotFoundException{
        FileOutputStream fos = new FileOutputStream(file, true);
        return fos;
    }
    
    public File getFile(){
        return file;
    }
}
