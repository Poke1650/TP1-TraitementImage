package traitement.io.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import traitement.Image;

/**
 * Objet qui permet d'écrire un fichier supporté dans un fichier
 *
 * @author Antoine Gagnon
 */
public class ImageWriter {

  /**
   * L'image à écrire
   */
  private Image image = null;

  /**
   * Le fichier à écrire
   */
  private File file = null;

  /**
   * Construit un ImageWriter et set l'image et le fichier pour écriture
   *
   * @param image image à écrire
   * @param file fichier a utiliser pour écriture
   */
  public ImageWriter(Image image, File file) {
    this.image = image;
    this.file = file;
  }

  /**
   * Construit un ImageWriter et set l'image pour écriture
   *
   * @param image image à écrire
   */
  public ImageWriter(Image image) {
    this.image = image;
  }

  /**
   * Construit un ImageWriter et set le fichier pour écriture
   *
   * @param file fichier a utiliser pour l'écriture
   */
  public ImageWriter(File file) {
    this.file = file;
  }

  /**
   * Set l'image qui doit être écrite dans le fichier
   *
   * @param image iamge à écrire
   */
  public void setImage(Image image) {
    this.image = image;
  }

  /**
   * Set le fichier à utiliser pour l'écriture
   *
   * @param file le fichier à utiliser
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * Set le fichier à utiliser pour l'écriture
   *
   * @param path chemin vers le fichier à utiliser
   */
  public void setFile(String path) {
    this.file = new File(path);
  }

  /**
   * Écrit l'image sur le fichier
   *
   * @param image l'image à écrire
   * @param file le fichier à utiliser
   * @throws IOException
   */
  public void write(Image image, File file) throws IOException {
    setImage(image);
    setFile(file);
    write();
  }

  /**
   * Écrit l'image sur le fichier
   *
   * @throws IOException
   */
  public void write() throws IOException {
    FileWriter fr = new FileWriter(file);
    PrintWriter pw = new PrintWriter(fr);

    printHeader(pw);
    printPixels(pw);
  }

  /**
   * Écrit tout les pixels dans sur le fichier
   *
   * @param pw PrintWriter ouvert sur le fichier
   * @throws IOException
   */
  private void printPixels(PrintWriter pw) throws IOException {
    for (int i = 0; i < image.getHeight(); i++) //jusqu'à la plus petite hauteur entre la nouvelle et l'ancienne matrice   
    {
      for (int j = 0; j < image.getWidth(); j++) //jusqu'à la plus petite largeur entre la nouvelle et l'ancienne matrice
      {
        pw.print(image.getPixel(j, i).toString());
        if (j != image.getWidth()) {
          pw.print(" ");
        }
      }
      pw.print("\n");
    }
    pw.print("\n");
  }

  /**
   * Print l'entête avec les informations nécéssaires: Type, Largeur, Hauteur,
   * Valeur Maxium
   *
   * @param pw pw PrintWriter ouvert sur le fichier
   */
  private void printHeader(PrintWriter pw) {
    pw.printf(
      "%s\n%d %d\n%d\n", 
      image.getType(),
      image.getWidth(),
      image.getHeight(),
      image.getMaxValue()
    );
  }
}
