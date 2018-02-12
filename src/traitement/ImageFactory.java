package traitement;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import traitement.Util.FileUtil;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.io.parser.IImageParser;
import traitement.io.parser.PGMParser;
import traitement.io.parser.PPMParser;

/**
 * Permet de crée un instance d'un image a partire d'un fichier directement.
 *
 * @author Antoine Gagnon
 */
public class ImageFactory {

  /**
   * Crée un instance d'image a partire d'un fichier
   *
   * @param path le fichier PGM ou PPM
   * @return instance d'image, peut être ImageCouleur ou ImageMono
   * @throws UnsupportedFileFormatException si le fichier n'est pas de type PGM
   * ou PPM
   */
  public static Image getImageFromFile(String path) throws UnsupportedFileFormatException {
    return getImageFromFile(new File(path));
  }

  /**
   * Crée un instance d'image a partire d'un fichier
   *
   * @param file le fichier PGM ou PPM
   * @return instance d'un image construit à partire d'un fichier
   * @throws UnsupportedFileFormatException si le fichier n'est pas de type PGM
   * ou PPM
   */
  public static Image getImageFromFile(File file) throws UnsupportedFileFormatException {

    IImageParser parser = null;

    //Instancie le parser approprié selon le type de fichier
    if (FileUtil.getFileExtension(file).equalsIgnoreCase("pgm")) { //Image mono
      parser = new PGMParser();
    } else if (FileUtil.getFileExtension(file).equalsIgnoreCase("ppm")) { //Image couleur
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
