package traitement;

import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.exceptions.UnsupportedFileFormatException;

/**
 *
 * @author Antoine Gagnon
 */
public class Main {

    public static void main(String[] args) {
        
        Image img = null;
        Image imgColor = null;
        try {
            img = ImageFactory.getImageFromFile("Sherbrooke_Frontenac_nuit.pgm");
            imgColor = ImageFactory.getImageFromFile("Sherbrooke_Frontenac_nuit.ppm");
        } catch (UnsupportedFileFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(img);
        System.out.println(imgColor);
    }
}
