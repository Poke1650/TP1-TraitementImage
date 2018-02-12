package traitement;

/**
 *
 * @author Francis
 * @version 1.0
 *
 * Une matrice est un tableau à 2 dimensions. Cette patrice contient des pixels,
 * utilisé pour lire, afficher et traiter des images.
 */
public class Matrice {

  /**
   * Largeur de la matrice
   */
  private int width;

  /**
   * Hauteur de la matrice
   */
  private int height;

  /**
   * Valeurs de la matrice
   */
  private int[][] matrice;

  /**
   * Constructeur sans paramètres. La hauteur et la largeut sont de 0
   */
  public Matrice() {

  }

  /**
   * Construit la matrice avec sa hauteur et sa largeur. Les valeurs de la
   * matrice sont à null
   *
   * @param height hauteur de la matrice
   * @param width largeur de la matrice
   */
  public Matrice(int height, int width) {

    this.width = width;
    this.height = height;

    matrice = new int[height][width];       //construit la matrice
  }

  /**
   * get la hauteur de la matrice
   *
   * @return hauteur de la matrice
   */
  public int getHeight() {
    return matrice.length;
  }

  /**
   * get la largeur de la première ligne de la matrice
   *
   * @return largeur de la matrice
   */
  public int getWidth() {
    return matrice[0].length;
  }

  /**
   * get la matrice complète pixel[][]
   *
   * @return matrice complète pixel[][]
   */
  public int[][] getMatrice() {
    return matrice;
  }

  /**
   * set la matrice au complet
   *
   * @param matrice matrice pixel[][]
   */
  public void setMatrice(int[][] matrice) {
    this.matrice = matrice;
  }

  /**
   * get la valeur selon l'indice i et j
   *
   * @param i ligne
   * @param j colonne
   * @return pixel de la case de la matrice
   */
  public int getValue(int i, int j) {
    return matrice[i][j];
  }

  /**
   * set la valeur de la matrice selon l'indice i et j
   *
   * @param i ligne
   * @param j colonne
   * @param value Pixel à appliquer dans la matrice
   */
  public void setValue(int i, int j, int value) {
    matrice[i][j] = value;
  }

  /**
   * Redimensionne la matrice avec une nouvelle hauteur et largeur. Recopie les
   * valeurs de la matrice originale dans la nonuvelle matrice lorsque les
   * dimensions le permettent.
   *
   * @param height
   * @param width
   */
  public void resize(int height, int width) {
    int[][] newMat = new int[height][width];                //nouvelle matrice

    for (int i = 0; i < getHeight() && i < height; i++) //jusqu'à la plus petite hauteur entre la nouvelle et l'ancienne matrice   
    {
      for (int j = 0; j < getWidth() && j < width; j++) //jusqu'à la plus petite largeur entre la nouvelle et l'ancienne matrice
      {
        newMat[i][j] = getValue(i, j);                   //copie la valeur de l'ancienne matrice dans la nouvelle matrice
      }
    }

    this.width = width;                                     //nouvelle largeur
    this.height = height;                                   //nouvelle hauteur
    matrice = newMat;                                       //copie la nouvelle matrice dans l'originale

  }

  /**
   * Imprime à l'écran les valeurs de la matrice
   */
  public void print() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(matrice[i][j] + " ");
      }
      System.out.println();
    }
  }
}
