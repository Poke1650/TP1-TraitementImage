package traitement.util;

import java.io.File;

/**
 * Utilitaires pour traiter des fichiers
 *
 * @author Antoine Gagnon
 */
public class FileUtil {

  /**
   * @param path le chemin du fichier
   * @return l'extension du fichier From: https://stackoverflow.com/a/21974043
   */
  public static String getFileExtension(String path) {
    try {
      return path.substring(path.lastIndexOf(".") + 1);
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @param file le fichier
   * @return l'extension du fichier
   */
  public static String getFileExtension(File file) {
    return FileUtil.getFileExtension(file.getName());
  }
}
