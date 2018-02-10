package traitement;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.Util.FileUtil;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.parser.PGMParser;

/**
 * Permet de crée un instance d'un image a partire d'un fichier directement.
 * @author Antoine Gagnon
 */
public class ImageFactory {
    
    public static Image getImageFromFile(String path) throws UnsupportedFileFormatException {
        return getImageFromFile(new File(path));
    }
    
    /**
     * Crée un instance d'image a partire d'un fichier
     * @param file le fichier PGM ou PPM
     * @return instance d'image, peut être ImageCouleur ou ImageMono
     * @throws UnsupportedFileFormatException si le fichier n'est pas de type PGM ou PPM
     */
    public static Image getImageFromFile(File file) throws UnsupportedFileFormatException {
        
        if(FileUtil.getFileExtension(file).equalsIgnoreCase("pgm")) { //Image mono
            
            PGMParser parser = new PGMParser();
            try {
                parser.read(file);
                return new Image(parser.getPixelMatrix(), parser.getMaxValue());     
            } catch (FileNotFoundException | ParseException ex) {
                Logger.getLogger(ImageFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if(FileUtil.getFileExtension(file).equalsIgnoreCase("ppm")) { //Image couleur
            
        } else {
            throw new UnsupportedFileFormatException(file);
        }
        return null;
    }
    
}
