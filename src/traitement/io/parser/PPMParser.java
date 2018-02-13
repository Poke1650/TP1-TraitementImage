/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement.io.parser;

import java.util.Scanner;

import traitement.component.MatricePixel;
import traitement.component.PixelCouleur;

/**
 * Implémentation d'un parser spécifique au fichier de type PGM
 *
 * @author Antoine Gagnon
 */
public class PPMParser extends PNMParser {

  /**
   * {@inheritDoc}
   */
  @Override
  public void readPixels(Scanner sc) {
    px = new MatricePixel(getHeight(), getWidth());

    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        px.setValue(i, j, new PixelCouleur(sc.nextInt(), sc.nextInt(), sc.nextInt()));
      }
    }
  }

}
