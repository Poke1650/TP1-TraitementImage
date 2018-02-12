/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import traitement.component.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0
 * Classe qui contient une matrice de pixel et qui stocke ses propriétés
 */
public class Image implements Comparable {    
  
  private int width;  // Retirer et simplement utiliser pixels.width()?
  private int height;
  private MatricePixel pixels;  // Use Matrice<Pixel>
  private int maxValue;

  /**
   * Constructeur de la classe Image
   * @param pixels Matrice de pixels
   * @param maxValue Valeur maximale de la couleur de l'image
   * @throws java.io.IOException
   */
  public Image(MatricePixel pixels, int maxValue) throws IOException {
    this.setMatrice(pixels);
    this.setMaxValue(maxValue);
  }
  
  /**
   * Constructeur de la classe Image à partir du nom d'un fichier png
   * @param fileName Nom du fichier de l'image à sauvegarder dans la classe
   */
  public Image(String fileName) {
    File f = new File(fileName);
    // Use ImageReader.read(this, f);
  }
  
  /** (Optionnel -- pas encore implémenté)
   * Constructeur de la classe Image à partir de ses caractéristiques de base
   * @param type Type des pixels de l'image
   * @param width Largeur de l'imge en pixel
   * @param height Heuteur de l'image en pixel
   */
  public Image(Class<?> type, int width, int height) {
    /*if (type == PixelCouleur.class) {
    } else if (type == PixelMono.class) {
    }*/
  }
  
  /**
   * Constructeur de copie une image
   * @param i Image à opier
   * @throws java.io.IOException 
   */
  public Image(Image i) throws IOException {
    this.setMatrice(i.getMatrice());
    this.setMaxValue(i.getMaxValue());
  }
  
  /**
   * Main pour faire des tests
   * @param args 
   * @throws java.io.IOException 
   */
  public static void main(String[] args) throws IOException {
    Image test = new Image(new MatricePixel(), 255);
  }
  
  /**
   * Établi un nouveau nombre de colonne de pixel pour l'image
   * @param width Nombre de colonne de pixel de l'image
   * @throws java.io.IOException Nombre de colonne de pixel invalide
   */
  public void setWidth(int width) throws IOException {
    if (width < 0) 
      throw new IOException("Nombre de colonne de pixel invalide");
    this.width = width;
    //this.pixels.setWidth(width);
  }

  /**
   * Établi un nouveau nombre de ligne de pixel pour l'image
   * @param height Nombre de ligne de pixel de l'image
   * @throws java.io.IOException Nombre de ligne de pixel trop petit
   */
  public void setHeight(int height) throws IOException {
    if (0 >= height) 
      throw new IOException("Nombre de ligne de pixel invalide");
    this.height = height;
    //this.pixels.setHeight(height);
  }

  /**
   * Établi une nouvelle matrice pour l'image
   * @param pixels Nouvelle matrice de pixel de l'image
   */
  public void setMatrice(MatricePixel pixels) {
    this.pixels = pixels;
  }
  
  /**
   * Établi la valeur maximale des couleurs de l'image
   * @param maxValue Nouvelle valeur maximale des couleurs de l'image
   * @throws java.io.IOException Valeur incorrecte selon l'image
   */
  public void setMaxValue(int maxValue) throws IOException {
//    for (Pixel[] p : pixels.getMatrice())
//      for (Pixel elem : p)
//        if (p.getMax() > maxValue)
//          throw new IOException("La valeur maximale de la couleur " 
//                  + "n'est pas la plus grande valeur de la matrice");
    this.maxValue = maxValue;
  }

  /**
   * Obtien le nombre de colonne de pixel de l'image
   * @return Nombre de ligne de pixel de l'image
   */
  public int getWidth() {
    return pixels.getWidth();
  }

  /**
   * Obtien le nombre de ligne de pixel de l'image
   * @return Nombre de ligne de pixel de l'image
   */
  public int getHeight() {
    return pixels.getHeight();
  }

  /**
   * Obtien la matrice des pixels de l'image
   * @return La matrice des pixels de l'image
   */
  public MatricePixel getMatrice() {
    return pixels;
  }
  
  /**
   * Obtien la valeur maximale de la couleur de l'image
   * @return La valeur maximale de la couleur de l'image
   */
  public int getMaxValue() {
    return maxValue;
  }
  
  /**
   * Obtien le pixel à une position de l'image
   * @param x Position du pixel recherché à partir de la gauche de l'image
   * @param y Position du pixel recherché à partir du haut de l'image
   * @return Le pixel à la position [x][y] ou [y][x]
   * @throws java.io.IOException Position du pixel recherché invalide
   */
  public Pixel getPixel(int x, int y) throws IOException {
    if (0 > x | x < this.getWidth() | 0 > y | 0 < this.getHeight()) 
      throw new IOException("Position du pixel recherché invalide");
    return pixels.getValue(x, y);
  }
  
  /**
   * Obtien le type des pixels de l'image
   * @return la classe des pixels de l'image
   * @throws java.io.IOException L'image n'est pas correctement instanciée
   */
  public Class<?> getType() throws IOException {
    return getPixel(0, 0).getClass();
  }
  
  /**
   * Forme la chaine de caractère qui représente l'image dans un fichier
   * @return Représentation de l'image en string
   */
  @Override
  public String toString() {
    StringBuilder r = new StringBuilder();
    
    try {
      if (getType() == PixelCouleur.class) {
        r.append("P2\n");
      }
      else if (getType() == PixelMono.class) {
        r.append("P3\n");
      }
    } catch (IOException ex) {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    r.append(getWidth()).append(" ").append(getHeight()).append("\n").append(getMaxValue()).append("\n");
    
    int i = 0;
    for (Pixel[] u: pixels.getMatrice()) {
      for (Pixel elem: u) {
        r.append(elem.toString());
        if (++i >= 12) {
          i = 0;
          r.append("\n");
        }
        else {
          r.append(" ");
        }
      }
    }
    return r.toString();
  }

  /** (Permet de simplifier la méthode sont_identiques de TraitementImage. 
   *   Il y a option de déplacer la définition dans le corps de celle-ci)
   * Méthode pour comparer deux images à partir de l'interface Comparable
   * @param t Image à comparer
   * @return -1 si le type ou les dimmensions ne sont pas égales, 
   *          1 si le contenu des images n'est pas identique et 0 si pareil
   */
  @Override
  public int compareTo(Object t) {
    try {
      // Conditions de bases pas égales
      if (this.getType() == ((Image)t).getType()
              | this.getHeight() < ((Image)t).getHeight()
              | this.getWidth() < ((Image)t).getWidth())
        return -1;
      else {
        // Contenu de l'image n'est pas 
        for (int i = 0; i < this.getWidth(); i++) {
          for (int j = 0; j < this.getHeight(); i++) {
            if (!this.getPixel(i, j).equals(((Image)t).getPixel(i, j))) {
              return 1;
            }
          }
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
  }
}
