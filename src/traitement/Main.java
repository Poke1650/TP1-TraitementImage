package traitement;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.io.writer.ImageWriter;

/**
 *
 * @author Antoine Gagnon
 */
public class Main {

    public static void main(String[] args) throws IOException {
        
        Image img = null;
        Image imgColor = null;
        try {
            img = ImageFactory.getImageFromFile("Sherbrooke_Frontenac_nuit.pgm");
            imgColor = ImageFactory.getImageFromFile("Sherbrooke_Frontenac_nuit.ppm");
        } catch (UnsupportedFileFormatException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageWriter iw = new ImageWriter(img, new File("test.pgm"));
        try {
            iw.write();
            iw.setFile(new File("test2.ppm"));
            iw.setImage(imgColor);
            iw.write();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(img);
        System.out.println(imgColor);
    }
}
