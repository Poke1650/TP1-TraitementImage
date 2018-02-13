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

  public static void main(String[] args) {

    Image img = null;
    Image imgColor = null;
    Image imgTest = null;
    TraiteurImageFR traiteur = new TraiteurImageFR();
    
    try 
    {
      img = ImageFactory.getImageFromFile("images/input/Sherbrooke_Frontenac_nuit.pgm");
      imgColor = ImageFactory.getImageFromFile("images/input/Sherbrooke_Frontenac_nuit.ppm");
      imgTest = ImageFactory.getImageFromFile("images/input/test.pgm");
      
    } catch (UnsupportedFileFormatException ex) 
    {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    //imgTest = traiteur.extraire(imgTest,2, 2, 4, 4);
    //img = traiteur.reduire(img);
    //imgColor = traiteur.reduire(imgColor);
    img = traiteur.extraire(img, 0, 0, 100, 100);
    //imgColor = traiteur.extraire(imgColor, 0, 0, 100, 100);

    ImageWriter iw = new ImageWriter(img, new File("images/output/mono.pgm"));
    try {
      iw.write();
      iw.setFile(new File("images/output/test.pgm"));
      iw.setImage(imgTest);
      iw.write();
      iw.setFile(new File("images/output/couleur.ppm"));
      iw.setImage(imgColor);
      iw.write();
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(img);
    System.out.println(imgColor);
    System.out.println(imgTest);
  }
}