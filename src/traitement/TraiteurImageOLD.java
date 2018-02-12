/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import java.io.File;
import java.io.IOException;
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
    Image image2 = new Image(image1);
    boolean b = sont_identiques(image1, image2);
    pivoter90(image1);
    boolean c = sont_identiques(image1, image2);
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
}
