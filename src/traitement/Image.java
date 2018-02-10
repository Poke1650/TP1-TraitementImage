/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import java.io.IOException;

/**
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0
 * Classe qui contient une matrice de pixel et qui stocke ses propriétés
 */
public class Image {
  private int width;  // Retirer et simplement utiliser pixels.width()?
  private int height;
  private Object pixels;
  private int maxValue;

  /**
   * Constructeur de la classe Image
   * @param pixels Matrice de pixels
   * @param maxValue Valeur maximale de la couleur de l'image
   */
  Image (Object pixels, int maxValue) {
    this.setMatrice(pixels);
    this.setMaxValue(maxValue);
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
   * 
   * @param maxValue 
   */
  public void setMaxValue(int maxValue) {
    //for (Pixel p : pixels)
    //  if (p.getMax() > maxValue)
    //    throw new IOException("La valeur maximale de la couleur 
    //      n'est pas la plus grande valeur de la matrice");
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
  public Object getPixel(int x, int y) throws IOException {
    if (0 > x | x < this.getWidth() | 0 > y | 0 < this.getHeight()) 
      throw new IOException("Position du pixel recherché invalide");
    return pixels.getClass();
  }
}
