/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import static traitement.ImageFactory.getImageFromFile;
import traitement.component.MatricePixel;
import traitement.component.Pixel;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.io.writer.ImageWriter;

/**
 * Classe utilitaire du TP1 (temporaire)
 * 
 * @author Olivier Lemay Dostie, Antoine Gagnon et Francis Forest
 * @version 1.0 
 */
public class TraiteurImageOLD {

  /**
   * Méthode pour faire des tests
   *
   * @param args
   * @throws java.io.IOException
   * @throws traitement.exceptions.UnsupportedFileFormatException
   */
  public static void main(String[] args) 
          throws IOException, UnsupportedFileFormatException {
    File in = new File("Sherbrooke_Frontenac_nuit.pgm");
    File out = new File("Test.pgm");
    Image image1 = null;
    lire(image1, in);
    System.out.println(image1.toString());
    
    ecrire(out, image1);
    System.out.println(image1.toString());
    
    Image image2 = null;
    copier(image2, image1);
    System.out.println(image2.toString());
    
    System.out.println("Couleur prépondérante de l'image1 : " 
                    + couleur_preponderante(image1).toString());
    
    System.out.println("Sont-elles identiques ? : " 
                    + sont_identiques(image1, image2));
    
    eclaircir_noircir(image1, 8);
    System.out.println(image1.toString());
    
    extraire(image1, 4, 6, 40, 100);
    System.out.println(image1.toString());
    
    reduire(image1);
    System.out.println(image1.toString());
    
    pivoter90(image1);
    System.out.println(image1.toString());
    
    System.out.println("Est-ce que les images sont identiques ? : " 
                    + sont_identiques(image1, image2));
  }

  /**
   * Constructeur de la classe TraiteurImageOLD
   */
  private TraiteurImageOLD() {
    
  }
  
  
  /**
   * Fait la lecture d'un fichier image et l'instancie dans l'objet
   * 
   * @param image Image résultante de la lecture
   * @param file Chemin et nom du fichier à lire
   * @throws traitement.exceptions.UnsupportedFileFormatException
   */
  public static void lire(Image image, File file) 
          throws UnsupportedFileFormatException {
    image = getImageFromFile(file);
  }
  
  
  /**
   * Écrit dans un fichier les données d'une image
   * 
   * @param image Image à sauvegarder dans le fichier
   * @param file Chemin et nom du fichier de la sauvegarde
   * @throws IOException Image ou fichier non conforme
   */
  public static void ecrire(File file, Image image) throws IOException {
    ImageWriter iw = new ImageWriter(file);
    iw.write(image, file);
  }
  
  /**
   * Copie une image dans une autre
   * 
   * @param i1 Image résultante de la copie
   * @param i2 Image à copier
   */
  public static void copier(Image i1, Image i2) {
    i1 = new Image(i2);
  }
  
  /**
   * (En construction) Retourne la couleur prépondérante de l’image i
   *
   * @param q Image où on recherche sa couleur prépondérante
   * @return Le pixel dont sa couleur est la plus fréquente de l'image
   * From : https://stackoverflow.com/questions/8545590/ ...
   * find-the-most-popular-element-in-int-array
   */
  public static Pixel couleur_preponderante(Image q) {
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
    // Implanter la méthode ici
  }
  
  /**
   * Extraire un sous ensemble de l’image partir de du point p1,c1 jusqu’à p2,c2
   * 
   * @param i 
   * @param p1 
   * @param c1 
   * @param p2 
   * @param c2 
   */
  public static void extraire(Image i, int p1, int c1, int p2, int c2) {
    // Implanter la méthode ici
  }
  
  /**
   * Réduit l’image i1 pour produire l’image i2
   * en faisant la moyenne de la valeur des groupes de quatres pixels 
   * pour en former un seul. Les pixels formés constituent la nouvelle image
   * 
   * @param i 
   */
  public static void reduire(Image i) {
  // Implanter la méthode ici
  }
  
  /**
   * Compare deux imges pour déterminer leur égalitée
   *
   * @param i1 Première image à comparer
   * @param i2 Deuxième image à comparer
   * @return Leur état d'égalité
   */
  public static boolean sont_identiques(Image i1, Image i2) {
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
