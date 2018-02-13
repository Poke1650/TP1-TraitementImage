/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;


import java.util.ArrayList;
import java.util.Arrays;
import traitement.component.MatricePixel;
import traitement.component.Pixel;
import traitement.component.PixelCouleur;
import traitement.component.PixelMono;

/**
 * Classe utilitaire du TP1 (temporaire)
 * 
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0 
 */
public class TraiteurImage {

  /**
   * Constructeur de la classe TraiteurImageOLD
   */
  private TraiteurImage() {
    
  }
 
  /**
   * (En construction) Retourne la couleur prépondérante de l’image i
   *
   * @param q Image où on recherche sa couleur prépondérante
   * @return Le pixel dont sa couleur est la plus fréquente de l'image
   * From : https://stackoverflow.com/questions/8545590/ ...
   * find-the-most-popular-element-in-int-array
   */
  public static Pixel couleurPreponderante(Image q) {
    Pixel[][] m = q.getMatrice().getMatrice();
    if (m == null || m.length == 0 || m[0].length == 0) {
      return null;
    }

    Arrays.sort(m);

    Pixel previous = m[0][0];
    Pixel popular = m[0][0];
    int count = 1;
    int maxCount = 1;

    for (int i = 1; i < m.length; i++) {
      for (int j = 0; j < m[0].length; j++) {
        if (m[i][0] == previous) {
          count++;
        } else {
          if (count > maxCount) {
            popular = m[i - 1][j - 1];
            maxCount = count;
          }
          previous = m[i][j - 1];
          count = 1;
        }
      }
    }

    return count > maxCount ? m[m.length - 1][m[0].length - 1] : popular;

    /*
    PixelCouleur []p = new PixelCouleur[i.getWidth() * i.getHeight()];
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (Pixel<[] p : i.getMatrice()) {
      Integer count = map.get(i);
      map.put(p, count != null ? count+1 : 0);
    }
    Integer popular = Collections.max(map.entrySet(),
      new Comparator<Map.Entry<Integer, Integer>>() {
      @Override
      public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
        return o1.getValue().compareTo(o2.getValue());
      }
    }).getKey();
    return p;*/
  }
  
  /**
   * Modifie la valeur de chaque pixel de l’image d’une valeur spécifiée, 
   * si v est positif, l’image devient plus noire, 
   * si v est négatif, l’image devient plus claire
   * 
   * @param i 
   * @param v 
   */
  public static void eclaircir_noircir(Image i, int v) {
    for (Pixel[] col : i.getMatrice().getMatrice()) {
      for (Pixel px : col) {
        
        if(px instanceof PixelMono) {
          PixelMono p = (PixelMono) px;
          if(v < 0 && p.getScale() > 0) {
            p.setScale(p.getScale() - 1);
          } else if (v > 0 && p.getScale() < i.getMaxValue()) {
            p.setScale(p.getScale() + 1);
          }
        }
        
        if(px instanceof PixelCouleur) {
          PixelCouleur p = (PixelCouleur) px;
          if(v < 0 && (p.getBlue() > 0 && p.getRed() > 0 && p.getGreen() > 0)) {
            p.setRed(p.getRed() - 1);
            p.setBlue(p.getBlue() - 1);
            p.setGreen(p.getGreen() - 1);
          } else if (v > 0 && (p.getBlue() < i.getMaxValue() && p.getRed() < i.getMaxValue() && p.getGreen() < i.getMaxValue())) {
            p.setRed(p.getRed() + 1);
            p.setBlue(p.getBlue() + 1);
            p.setGreen(p.getGreen() + 1);
          }
        }
      }
    }
  }
  
  /**
   * Extait une partie d'une image selon des coordonnées passées en paramètres. L'image est extraite
   * à partir du point (p1,c1) et du point (p2,c2). Retourne la nouvelle image.
   * @param i     image à rogner
   * @param p1    coordonée x du premier point
   * @param c1    coordonée y du premier point
   * @param p2    coordonée x du deuxième point
   * @param c2    coordonée y du deuxième point
   * @return      Retourne la nouvelle image rognée (Image)
   */
  public static Image extraire(Image i, int p1, int c1, int p2, int c2)
  {
      if (p1 >= p2 || c1 >= c2) 
      {
          throw new IllegalArgumentException("les points ne sont pas valides : " + p1 + " " + c1 + " " + p2 + " " + c2);
      }

      int newHeight = c2 - c1;            //hauteur de la nouvelle image rognée
      int newWidth = p2 - p1;             //largeur de la nouvelle image rognée

      MatricePixel newMat = new MatricePixel(newHeight, newWidth);    //matrice de pixels de la nouvelle image

      for (int j = c1; j < c2; j++)       //selon la largeur de la nouvelle image
      {
          for (int k = p1; k < p2; k++)   //selon la hauteur de la nouvelle image
          {
              newMat.setValue(j - newHeight, k - newWidth, i.getPixel(k, j)); //copie la valeur de la vielle image dans la nouvelle
          }
      }

      Image newImage = new Image(newMat, i.getMaxValue());

      return newImage;
  }
  
  /**
   * Réduit l’image i1 pour produire l’image i2
   * en faisant la moyenne de la valeur des groupes de quatres pixels 
   * pour en former un seul. Les pixels formés constituent la nouvelle image
   * 
   * @param i 
   */
  public static Image reduire(Image i) {
    int newHeight = i.getHeight() / 2;          //hauteur de la nouvelle image
    int newWidth = i.getWidth() / 2;            //largeur de la nouvelle image

    MatricePixel newMat = new MatricePixel(newHeight, newWidth);    //matrice de pixels de la nouvelle image

    int posI = 0;                               //position I du pixel à placer dans la nouvelle matrice
    int posJ = 0;                               //position J du pixel à placer dans la nouvelle matrice

    for (int j = 0; j < i.getHeight(); j+=2)    //bonds de 2 en hauteur pour l'image originale
    {
        for (int k = 0; k < i.getWidth(); k+=2) //bonds de 2 en largeur pour l'image originale
        {   
            ArrayList<Pixel> pixels = new ArrayList();      //ajoute les pixels dans un array afin de faire la moyenne de leur valeur
            pixels.add(i.getPixel(k, j));
            pixels.add(i.getPixel(k, j + 1));
            pixels.add(i.getPixel(k + 1, j));
            pixels.add(i.getPixel(k + 1, j + 1));

            if (i.getType().equals("P3"))                   //image couleur
            {
                newMat.setValue(posI, posJ, getAveragePixel(pixels, "P3"));
            }
            else                                            //image noir et blanc
            {
                newMat.setValue(posI, posJ, getAveragePixel(pixels, "P2"));
            }

            posJ++;
        }
        posJ = 0;
        posI++;
    }

    Image newImage = new Image(newMat, i.getMaxValue());

    return newImage;
  }
  
      /**
     * Get la valeur moyenne des pixels en paramètres. Retourne un pixel avec cette nouvelle valeur.
     * Peut gérer un pixel RGB et un pixel noir et blanc.
     * @param pixels    ArrayList de pixels, c'est avec leur valeurs que nous effectuons la moyenne
     * @param type      type du pixel, soit P3 (RGB) ou P2(noir et blanc)
     * @return  retourne un pixel avec les moyennes des pixels en paramètre
     */
    private static Pixel getAveragePixel(ArrayList<Pixel> pixels, String type)
    {
      if (type.equals("P3"))          //pixel RGB
      {
          int r = 0;          //rouge
          int g = 0;          //vert
          int b = 0;          //bleu

          for (Pixel p : pixels)  //pour chaque pixel de l'array, on additionne sa valeur de RGB
          {
              r += ((PixelCouleur)p).getRed();
              g += ((PixelCouleur)p).getGreen();
              b += ((PixelCouleur)p).getBlue();
          }

          if (r > 0) r /= pixels.size();      //moyenne, si pas 0
          if (g > 0) g /= pixels.size();
          if (b > 0) b /= pixels.size();


          PixelCouleur newP = new PixelCouleur(r, g, b);
          return newP;

      }
      else                                    //noir et blanc
      {
          int scale = 0;                      //niveau de gris
          for (Pixel p : pixels)              //pour chaque pixel de l'array, on additionne son niveau de gris
          {
              scale += ((PixelMono)p).getScale();
          }

          if (scale != 0) scale /= pixels.size(); //moyenne

          PixelMono newP = new PixelMono(scale);
          return newP;
      }
    }
  
  /**
   * Compare deux imges pour déterminer leur égalitée
   *
   * @param i1 Première image à comparer
   * @param i2 Deuxième image à comparer
   * @return Leur état d'égalité
   */
  public static boolean sontIdentiques(Image i1, Image i2) {
    return (0 == i1.compareTo(i2));
  }

  /**
   * Fait pivoter une image vers la droite de 90 degrées
   *
   * @param i Image à faire pivoter
   * @throws java.io.IOException From:
   * From: https://codereview.stackexchange.com/questions/40246/given-nn- ...
   * matrix-rotate-it-by-90-degree-to-left-and-right-without-extra-memory
   */
  public static void pivoter90(Image i) {
    MatricePixel s = i.getMatrice();
    //s.resize(i.getWidth(), i.getHeight());
    int e = i.getWidth() - 1; // ou sinon Height
    int c = e / 2;
    int b = e % 2;
    for (int r = c; r >= 0; r--) {
      for (int d = c - r; d < c + r + b; d++) {
        Pixel t = s.getValue(c - r, d);
        s.setValue(c - r, d, s.getValue(d, e - c + r));
        s.setValue(d, e - c + r, s.getValue(e - c + r, e - d));
        s.setValue(e - c + r, e - d, s.getValue(e - d, c - r));
        s.setValue(e - d, c - r, t);
      }
    }
    i.setMatrice(s);

    //Image temp = new Image(i1.getType(), i1.getWidth(), i1.getHeight());
    //Image temp = new Image(i.getMatrice(), i.getMaxValue());
    /*for (int x = 0; x < i.getWidth() / 2; x++) {
      for (int y = 0; y < i.getHeight() / 2; y++) {
      }
    }*/
  }
}
