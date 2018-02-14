/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import traitement.component.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.component.MatricePixel;
import traitement.component.PixelMono;

/**
 * Classe qui contient une matrice de pixel et qui stocke ses propriétés
 *
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0
 */
public class Image implements Comparable<Image> {

  /**
   * Matrice qui contient les pixels dans l'image
   */
  private MatricePixel pixels;

  /**
   * Valeur maximal qu'un pixel peux avoir
   */
  private int maxValue;

  /**
   * Constructeur de la classe Image
   *
   * @param pixels Matrice de pixels
   * @param maxValue Valeur maximale de la couleur de l'image
   */
  public Image(MatricePixel pixels, int maxValue) {
    this.setMatrice(pixels);
    this.setMaxValue(maxValue);
  }

  /**
   * Constructeur de la classe Image à partir du nom d'un fichier png
   *
   * @param fileName Nom du fichier de l'image à sauvegarder dans la classe
   */
  public Image(String fileName) {
    File f = new File(fileName);
    // Use ImageReader.read(this, f);
  }

  /**
   * Constructeur de copie une image
   *
   * @param i Image à opier
   */
  public Image(Image i) {
    this.setMatrice(i.getMatrice());
    this.setMaxValue(i.getMaxValue());
  }

  /**
   * Établi une nouvelle matrice pour l'image
   *
   * @param pixels Nouvelle matrice de pixel de l'image
   */
  public void setMatrice(MatricePixel pixels) {
    this.pixels = pixels;
  }

  /**
   * Établi la valeur maximale des couleurs de l'image
   *
   * @param maxValue Nouvelle valeur maximale des couleurs de l'image
   */
  public void setMaxValue(int maxValue) throws IndexOutOfBoundsException {
    this.maxValue = maxValue;
  }

  /**
   * Obtien le nombre de colonne de pixel de l'image
   *
   * @return Nombre de ligne de pixel de l'image
   */
  public int getWidth() {
    return pixels.getWidth();
  }

  /**
   * Obtien le nombre de ligne de pixel de l'image
   *
   * @return Nombre de ligne de pixel de l'image
   */
  public int getHeight() {
    return pixels.getHeight();
  }

  /**
   * Obtien la matrice des pixels de l'image
   *
   * @return La matrice des pixels de l'image
   */
  public MatricePixel getMatrice() {
    return pixels;
  }

  /**
   * Obtien la valeur maximale de la couleur de l'image
   *
   * @return La valeur maximale de la couleur de l'image
   */
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Obtien le pixel à une position de l'image
   *
   * @param x Position du pixel recherché à partir de la gauche de l'image
   * @param y Position du pixel recherché à partir du haut de l'image
   * @return Le pixel à la position [x][y] ou [y][x]
   */
  public Pixel getPixel(int x, int y) {
    if (isInRange(x, y)) {
      return pixels.getValue(y, x);
    } else {
      throw new IndexOutOfBoundsException("Position du pixel recherché invalide");
    }
  }

  /**
   * Vérifie si la position du pixel recherché se trouve à l'intérieure de
   * l'image
   *
   * @param x Position du pixel recherché à partir de la gauche de l'image
   * @param y Position du pixel recherché à partir du haut de l'image
   * @return si la position est dans les limites de la matrice
   */
  private boolean isInRange(int x, int y) {
    return (0 <= x && x <= getWidth()
            && 0 <= y && y <= getHeight());
  }

  /**
   * Obtien le type des pixels de l'image
   *
   * @return la classe des pixels de l'image
   */
  public String getType() {
    try {
      return getPixel(0, 0) instanceof PixelMono ? "P2" : "P3";
    } catch (Exception e) {
      return "ERROR";
    }
  }

  /**
   * Forme la chaine de caractère qui représente l'image dans un fichier
   *
   * @return Représentation de l'image en string
   */
  @Override
  public String toString() {
    if (this == null) {
      return "";
    }
    return getClass().getName() + "[type=" + getType() + ",width=" + getWidth()
            + ",height=" + getHeight() + ",maxValue=" + getMaxValue()
            + ",pixels=" + pixels.toString() + "]";
  }

  /**
   * Méthode pour comparer deux images à partir de l'interface Comparable
   *
   * @param t Image à comparer
   * @return -1 si le type ou les dimmensions ne sont pas égales, 1 si le
   * contenu des images n'est pas identique et 0 si pareil
   */
  @Override
  public int compareTo(Image t) {
    try {

      Pixel[][] px = t.getMatrice().getMatrice();

      for (int i = 0; i < getHeight() && i < t.getHeight(); i++) {
        for (int j = 0; j < getWidth() && j < t.getWidth(); j++) {
          if (t.getPixel(j, i).compareTo(this.getPixel(j, i)) != 0) {
            return -1;
          }
        }
      }

    } catch (Exception ex) {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
  }
}
