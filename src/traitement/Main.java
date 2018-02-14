package traitement;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import traitement.exceptions.UnsupportedFileFormatException;
import traitement.io.writer.ImageWriter;

/**
 *
 * @author Antoine Gagnon
 */
public class Main {

  public static void main(String[] args) {

    int passed = 0, failed = 0;

    Image imageColor = null, imageMono = null;
    try {
      //Test de lecture
      imageColor = ImageFactory.getImageFromFile("images/input/couleur.ppm");
      imageMono = ImageFactory.getImageFromFile(new File("images/input/mono.pgm"));
      passed++;
    } catch (UnsupportedFileFormatException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      failed++;
    }

    System.out.println("Images lues:");
    System.out.println("Couleur: " + imageColor);
    System.out.println("Mono: " + imageMono);

    System.out.println("--------");

    System.out.println("Écriture des images non touché");
    ImageWriter iw = new ImageWriter(imageMono, new File("images/output/mono_untouched.pgm"));
    try {
      iw.write();
      iw.write(imageColor, new File("images/output/color_untouched.ppm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("Fait, voir images/output/");

    System.out.println("--------");

    System.out.println("Création d'une copie de imageColor et écriture sur le disque");
    Image copieCouleur = new Image(imageColor);
    Image copieMono = new Image(imageMono);
    try {
      iw.write(copieCouleur, new File("images/output/copieCouleur.ppm"));
      iw.write(copieMono, new File("images/output/copieMono.pgm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println("Fait, voir images/output/");
    System.out.println("--------");

    try {
      System.out.println("Couleur preponderante v2: " + TraiteurImage.couleurPreponderanteV2(copieMono));
      System.out.println("Couleur preponderante: " + TraiteurImage.couleurPreponderante(copieCouleur));
      passed++;
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Couleur préponderante", e);
      failed++;
    }

    System.out.println("--------");
    System.out.println("Création d'un image noirci et clair, voir images/output/");

    Image imgNoirci = new Image(imageColor);
    Image imgClair = new Image(imageMono);

    for (int i = 0; i < 100; i++) {
      TraiteurImage.eclaircir_noircir(imgNoirci, -1);
      TraiteurImage.eclaircir_noircir(imgClair, 1);
    }

    try {
      iw.write(imgNoirci, new File("images/output/noirci.ppm"));
      iw.write(imgClair, new File("images/output/clair.pgm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    Image extrait = new Image(imageColor);
    try {
      extrait = TraiteurImage.extraire(imageColor, 0, 0, 50, 50);
      passed++;
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Extraction", e);
      failed++;
    }

    try {
      iw.write(extrait, new File("images/output/extrait.ppm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    Image reduction = new Image(imageColor);
    try {
      reduction = TraiteurImage.reduire(reduction);
      passed++;
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Reduction", e);
      failed++;
    }

    try {
      iw.write(reduction, new File("images/output/reduit.ppm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println("-----------\nTest d'égalité");
    System.out.println("imageColor et copie d'un imageColor est pareille? " + TraiteurImage.sontIdentiques(imageColor, new Image(imageColor)));
    System.out.println("imageColor et imageMono est pareille? " + TraiteurImage.sontIdentiques(imageColor, imageMono));

    Image pivot = new Image(imageColor);
    try {
      pivot = TraiteurImage.pivoter90(imageColor);
      passed++;
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Pivoter", e);
      failed++;
    }

    try {
      iw.write(pivot, new File("images/output/pivot.ppm"));
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println("\n\n--------------------------------\nRESULT: failed = " + failed + " passed = " + passed + " total = " + (failed + passed));
  }
}
