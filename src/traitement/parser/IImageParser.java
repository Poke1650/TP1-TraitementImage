package traitement.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import traitement.component.MatricePixel;

/**
 * Interface implémenté par les lecteurs d'image
 * @author Antoine Gagnon
 */
public interface IImageParser {
    
    /**
     * Lire et parse le fichier
     * @param path le chemin vers le fichier
     * @throws FileNotFoundException le fichier n'a pas été trouvé
     * @throws ParseException si le fichier est mal formé et ne peux pas être parser correctement
     */
    public void read(String path) throws FileNotFoundException, ParseException;
    
    /**
     * Lire et parse le fichier
     * @param file le fichier à lire
     * @throws FileNotFoundException le fichier n'a pas été trouvé
     * @throws ParseException si le fichier est mal formé et ne peux pas être parser correctement
     */
    public void read(File file) throws FileNotFoundException, ParseException;
    
    /**
     * @return l'entête
     */
    public String getHeader();
    
    /**
     * @return la hauteur de l'image
     */
    public int getHeight();
    
    /**
     * @return la largeur de l'image
     */
    public int getWidth();
    
    /**
     * @return la valeur maximal qu'un pixel peux avoir
     */
    public int getMaxValue();
    
    /**
     * @return un matrix de pixel représentant l'image
     */
    public MatricePixel getPixelMatrix();
    
    /**
     * @return tout les metadata de l'image
     */
    public Map getMetadata();
    
    /**
     * Charge les pixels en mémoire
     * @param sc 
     */
    public void readPixels(Scanner sc);
}
