package traitement.exceptions;

import java.io.File;

/**
 * Signale que un fichier n'est pas supporter
 * @author Antoine Gagnon
 */
public class UnsupportedFileFormatException extends Exception {

    /**
     * Construit un UnsupportedFileFormatException avec un message et un fichier
     * @param message
     * @param file 
     */
    public UnsupportedFileFormatException(String message, File file) {
        super(message + " file name: " + file.getName());
    }   
    
    /**
     * Construit un UnsupportedFileFormatException avec un message et un fichier
     * @param message
     * @param file 
     */
    public UnsupportedFileFormatException(String message, String file) {
        super(message + " file name: " + file);
    }  
    
    /**
     * Construit un UnsupportedFileFormatException avec un fichier
     * @param file 
     */
    public UnsupportedFileFormatException(File file) {
        super("file name: " + file.getName());
    }   
    
    /**
     * Construit un UnsupportedFileFormatException avec un fichier
     * @param file 
     */
    public UnsupportedFileFormatException(String file) {
        super("file name: " + file);
    }  
}
