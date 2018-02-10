/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import traitement.component.Pixel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0
 * Classe qui contient une matrice de pixel et qui stocke ses propriétés
 */
public class Image {
<<<<<<< HEAD:src/image/Image.java
    
    
  private int width;  // Retirer et simplement utiliser pixels.width()?
=======
  private int width;  // Retirer et simplement utiliser pixels.getWidth()?
>>>>>>> master:src/traitement/Image.java
  private int height;
  private Object pixels;  // Use Matrice<Pixel>
  private int maxValue;

  /**
   * Constructeur de la classe Image
   * @param pixels Matrice de pixels
   * @param maxValue Valeur maximale de la couleur de l'image
   */
  public Image (Object pixels, int maxValue) {
    this.setMatrice(pixels);
    this.setMaxValue(maxValue);
  }
  
  /**
   * Constructeur de la classe Image à partir du nom d'un fichier png
   * @param fileName Nom du fichier de l'image à sauvegarder dans la classe
   */
  public Image (String fileName) {
    File f = new File(fileName);
    // Use ImageReader.read(this, f);
  }
  
  /**
   * Main pour faire des tests
   * @param args 
   */
  public static void main(String[] args) {
    Image test = new Image("", 255);
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
  public void setMatrice(Object pixels) {
    this.pixels = pixels;
  }
  
  /**
   * Établi la valeur maximale des couleurs de l'image
   * @param maxValue Nouvelle valeur maximale des couleurs de l'image
   */
  public void setMaxValue(int maxValue) {
    //for (Pixel p : pixels)
    //  if (p.getMax() > maxValue)
    //    throw new IOException("La valeur maximale de la couleur " +
    //      "n'est pas la plus grande valeur de la matrice");
    this.maxValue = maxValue;
  }

  /**
   * Obtien le nombre de colonne de pixel de l'image
   * @return Nombre de ligne de pixel de l'image
   */
  public int getWidth() {
    return width; // Use pixels.getWidth();
  }

  /**
   * Obtien le nombre de ligne de pixel de l'image
   * @return Nombre de ligne de pixel de l'image
   */
  public int getHeight() {
    return height;  // Use pixels.getHeight();
  }

  /**
   * Obtien la matrice des pixels de l'image
   * @return La matrice des pixels de l'image
   */
  public Object getMatrice() {
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
    return null; // Use pixels.getPixel(x, y);
  }
  
  /**
   * Obtien le type des pixels de l'image
   * @return la classe des pixels de l'image
   * @throws java.io.IOException L'image n'est pas correctement instanciée
   */
  public Object getType() throws IOException {
    return getPixel(0, 0).getClass();
  }
  
  /**
   * Forme la chaine de caractère qui représente l'image dans un fichier
   * @return Représentation de l'image en string
   */
  @Override
  public String toString() {
    String r = "";
    
    try {
      if (getType() == "") {
        r += "P2\n";
      }
      else if (getType() == "") {
        r += "P3\n";
      }
    } catch (IOException ex) {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    r += getWidth() + " " + getHeight() + "\n" + getMaxValue() + "\n";
    
    int i = 0;
    /*for (Pixel p : pixels) {
      r += p.toString();
      if (++i >= 12) {
        i = 0;
        r += "\n";
      }
      else {
        r += " ";
      }
    }*/
    return r;
  }
}
