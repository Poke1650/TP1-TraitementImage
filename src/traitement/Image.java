/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import traitement.component.Pixel;
import java.io.File;
import java.io.IOException;
import traitement.component.MatricePixel;
import traitement.component.PixelMono;

/**
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0
 * Classe qui contient une matrice de pixel et qui stocke ses propriétés
 */
public class Image {    
    
  private MatricePixel pixels;  // Use Matrice<Pixel>
  private int maxValue;

  /**
   * Constructeur de la classe Image
   * @param pixels Matrice de pixels
   * @param maxValue Valeur maximale de la couleur de l'image
   */
  public Image (MatricePixel pixels, int maxValue) {
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
   * Établi une nouvelle matrice pour l'image
   * @param pixels Nouvelle matrice de pixel de l'image
   */
  private void setMatrice(MatricePixel pixels) {
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
    if(isInRange(x,y))
        return pixels.getValue(y,x);
    else
       
        throw new IndexOutOfBoundsException("Position du pixel recherché invalide");     
  }
  
  /**
   * @param x
   * @param y
   * @return si la position est dans les limites de la matrice
   */
  private boolean isInRange(int x, int y) {
      return (0 <= x && x <= getWidth() &&
              0 <= y && y <= getHeight());
  }
  
  /**
   * Obtien le type des pixels de l'image
   * @return la classe des pixels de l'image
   * @throws java.io.IOException L'image n'est pas correctement instanciée
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
   * @return Représentation de l'image en string
   */
  @Override
  public String toString() {
    return getClass().getName() + "[type=" + getType() + ",width=" + getWidth() + ",height=" + getHeight() + ",maxValue=" + getMaxValue() + ",pixels=" + pixels.toString() + "]";
   }
}
