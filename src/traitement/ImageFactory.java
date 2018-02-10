package traitement;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import traitement.Util.FileUtil;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.parser.IImageParser;
import traitement.parser.PGMParser;
import traitement.parser.PPMParser;

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
        
        IImageParser parser = null;
        
        if(FileUtil.getFileExtension(file).equalsIgnoreCase("pgm")) { //Image mono
            parser = new PGMParser();            
        } else if(FileUtil.getFileExtension(file).equalsIgnoreCase("ppm")) { //Image couleur
            parser = new PPMParser();
        } else {
            throw new UnsupportedFileFormatException(file);
        }
        
        try {
            parser.read(file);
            return new Image(parser.getPixelMatrix(), parser.getMaxValue());     
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(ImageFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
