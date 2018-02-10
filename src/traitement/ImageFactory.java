package traitement;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.Util.FileUtil;
import traitement.component.Pixel;
import traitement.component.PixelMono;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.parser.PGMParser;

/**
 * Permet de crée un instance d'un image a partire d'un fichier directement.
 * @author Antoine Gagnon
 */
public class ImageFactory {
    
    //TODO: Change Object for Image
    public static Object getImageFromFile(String path) throws UnsupportedFileFormatException {
        return getImageFromFile(new File(path));
    }
    
    /**
     * Crée un instance d'image a partire d'un fichier
     * @param file le fichier PGM ou PPM
     * @return instance d'image, peut être ImageCouleur ou ImageMono
     * @throws UnsupportedFileFormatException si le fichier n'est pas de type PGM ou PPM
     */
    public static Object getImageFromFile(File file) throws UnsupportedFileFormatException {
        
        if(FileUtil.getFileExtension(file).equalsIgnoreCase("pgm")) { //Image mono
            PGMParser parser = new PGMParser();
            
            try {
                parser.read(file);
                
                StringBuilder imgStr = new StringBuilder();
                
                //TODO: changer pour retourner un Image et non une string
                //String pour test seulement
                imgStr.append(String.format("Header: %s\nH: %s W: %s\nMax: %s\n", parser.getHeader(), parser.getHeight(), parser.getWidth(), parser.getMaxValue()));
                
                for(Pixel[] line : parser.getPixelMatrix()){
                    for(Pixel px : line) {
                        imgStr.append(((PixelMono)px).getScale()).append(" ");
                    }
                    imgStr.append("\n");
                }
                
                return imgStr.toString();
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
