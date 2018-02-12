/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import static traitement.ImageFactory.getImageFromFile;
import traitement.component.MatricePixel;
import traitement.component.Pixel;
import traitement.exceptions.UnsupportedFileFormatException;


/**
 * 
 * @author Olivier Lemay Dostie
 */
public class TraiteurImageOLD {
  
  /**
   * Méthode pour faire des tests
   * @param args 
   * @throws java.io.IOException 
   * @throws traitement.exceptions.UnsupportedFileFormatException 
   */
  public static void main(String[] args) throws IOException, UnsupportedFileFormatException {
    File f = new File("../Sherbrooke_Frontenac_nuit.pgm");
    Image image1 = new Image((Image) getImageFromFile(f));
    System.out.println(image1.toString());
    Image image2 = new Image(image1);
    System.out.println("Couleur prépondérante : " + couleur_preponderante(image1).toString());
    System.out.println("Sont-elles identiques : " + sont_identiques(image1, image2));
    pivoter90(image1);
    System.out.println("Sont-elles identiques : " + sont_identiques(image1, image2));
  }
  
  /**
   * Constructeur de la classe TraiteurImageOLD
   */
  private TraiteurImageOLD () {}
  
  /**
   * Compare deux imges pour déterminer leur égalitée
   * @param i1 Première image à comparer
   * @param i2 Deuxième image à comparer
   * @return Leur état d'égalité
   */
  public static boolean sont_identiques(Image i1, Image i2) {
    return (0 == i1.compareTo(i2));
  }
  
  /**
   * Fait pivoter une image vers la droite de 90 degrées
   * @param i Image à faire pivoter
   * @throws java.io.IOException
   * From: https://codereview.stackexchange.com/questions/40246/given-nn-matrix-rotate-it-by-90-degree-to-left-and-right-without-extra-memory
   */
  public static void pivoter90(Image i) throws IOException {
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
  
  /**
   * (En construction)
   * @param q Image où il faut trouver sa couleur prépondérante
   * @return 
   * From : https://stackoverflow.com/questions/8545590/find-the-most-popular-element-in-int-array
   */
  public static Pixel couleur_preponderante(Image q) {
    Pixel[][] m = q.getMatrice().getMatrice();
    if (m == null || m.length == 0 || m[0].length == 0)
      return null;

    Arrays.sort(m);

    Pixel previous = m[0][0];
    Pixel popular = m[0][0];
    int count = 1;
    int maxCount = 1;

    for (int i = 1; i < m.length; i++) {
      for (int j = 0; j < m[0].length; j++) {
        if (m[i][0] == previous) {
          count++;
        }
        else {
          if (count > maxCount) {
            popular = m[i-1][j-1];
            maxCount = count;
          }
          previous = m[i][j-1];
          count = 1;
        }
      }
    }

    return count > maxCount ? m[m.length-1][m[0].length-1] : popular;

//    PixelCouleur []p = new PixelCouleur[i.getWidth() * i.getHeight()];
//    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//    for (Pixel<[] p : i.getMatrice()) {
//      Integer count = map.get(i);
//      map.put(p, count != null ? count+1 : 0);
//    }
//    Integer popular = Collections.max(map.entrySet(),
//      new Comparator<Map.Entry<Integer, Integer>>() {
//      @Override
//      public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
//        return o1.getValue().compareTo(o2.getValue());
//      }
//    }).getKey();
//    return p;
  }
}
