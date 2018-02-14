/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement;

import java.util.ArrayList;
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
     * Constructeur de la classe TraiteurImage sans paramètres
     */
    private TraiteurImage() {

    }

    /**
     * (En construction) Retourne la couleur prépondérante de l’image i
     *
     * @param q Image où on recherche sa couleur prépondérante
     * @return Le pixel dont sa couleur est la plus fréquente de l'image From : https://stackoverflow.com/questions/8545590/ ...
     * find-the-most-popular-element-in-int-array
     */
    public static String couleurPreponderante(Image q) {
        Pixel[][] m = q.getMatrice().getMatrice();

        if (q.getType().equals("P3")) { //Color
            int r = 0;
            int g = 0;
            int b = 0;

            for (Pixel[] col : m) {
                for (Pixel p : col) {
                    b += ((PixelCouleur) p).getBlue();
                    r += ((PixelCouleur) p).getRed();
                    g += ((PixelCouleur) p).getGreen();
                }
            }

            if (r > b) {
                if (r > g) {
                    return "Rouge";
                }
            } else if (b > g) {
                return "Bleu";
            }
            return "Vert";

        }
        return "";
    }

    /**
     * Modifie la valeur de chaque pixel de l’image d’une valeur spécifiée, si v est positif, l’image devient plus noire, si v est négatif, l’image devient plus claire
     *
     * @param i
     * @param v
     */
    public static void eclaircir_noircir(Image i, int v) {
        for (Pixel[] col : i.getMatrice().getMatrice()) {
            for (Pixel px : col) {

                if (px instanceof PixelMono) {
                    PixelMono p = (PixelMono) px;
                    if (v < 0 && p.getScale() > 0) {
                        p.setScale(p.getScale() - 1);
                    } else if (v > 0 && p.getScale() < i.getMaxValue()) {
                        p.setScale(p.getScale() + 1);
                    }
                }

                if (px instanceof PixelCouleur) {
                    PixelCouleur p = (PixelCouleur) px;
                    if (v < 0 && (p.getBlue() > 0 && p.getRed() > 0 && p.getGreen() > 0)) {
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
     * Extait une partie d'une image selon des coordonnées passées en paramètres. L'image est extraite à partir du point (p1,c1) et du point (p2,c2). Retourne la nouvelle
     * image.
     *
     * @param i image à rogner
     * @param p1 coordonée x du premier point
     * @param c1 coordonée y du premier point
     * @param p2 coordonée x du deuxième point
     * @param c2 coordonée y du deuxième point
     * @return Retourne la nouvelle image rognée (Image)
     */
    public static Image extraire(Image i, int p1, int c1, int p2, int c2) {
        if (p1 >= p2 || c1 >= c2) {
            throw new IllegalArgumentException("les points ne sont pas valides : " + p1 + " " + c1 + " " + p2 + " " + c2);
        }

        int newHeight = c2 - c1;            //hauteur de la nouvelle image rognée
        int newWidth = p2 - p1;             //largeur de la nouvelle image rognée

        MatricePixel newMat = new MatricePixel(newHeight, newWidth);    //matrice de pixels de la nouvelle image

        for (int j = 0; j < newHeight; j++) //selon la largeur de la nouvelle image
        {
            for (int k = 0; k < newWidth; k++) //selon la hauteur de la nouvelle image
            {
                newMat.setValue(j, k, i.getPixel(k + p1, j + c1)); //copie la valeur de la vielle image dans la nouvelle
            }
        }

        Image newImage = new Image(newMat, i.getMaxValue());

        return newImage;
    }

    /**
     * Réduit l’image i1 pour produire l’image i2 en faisant la moyenne de la valeur des groupes de quatres pixels pour en former un seul. Les pixels formés constituent
     * la nouvelle image
     *
     * @param i
     * @return L'image reduite d'un facteur de 2 en hauteur et en largeur
     */
    public static Image reduire(Image i) throws Exception {

        if (i.getHeight() < 2 || i.getWidth() < 2) {
            throw new Exception("L'image est trop petite pour être réduite : " + i.getWidth() + i.getHeight());
        }
        int newHeight = i.getHeight() / 2;          //hauteur de la nouvelle image
        int newWidth = i.getWidth() / 2;            //largeur de la nouvelle image

        MatricePixel newMat = new MatricePixel(newHeight, newWidth);    //matrice de pixels de la nouvelle image

        int posI = 0;                               //position I du pixel à placer dans la nouvelle matrice
        int posJ = 0;                               //position J du pixel à placer dans la nouvelle matrice

        for (int j = 0; j < i.getHeight() - 1; j += 2) //bonds de 2 en hauteur pour l'image originale
        {
            for (int k = 0; k < i.getWidth() - 1; k += 2) //bonds de 2 en largeur pour l'image originale
            {
                ArrayList<Pixel> pixels = new ArrayList();      //ajoute les pixels dans un array afin de faire la moyenne de leur valeur
                pixels.add(i.getPixel(k, j));
                pixels.add(i.getPixel(k, j + 1));
                pixels.add(i.getPixel(k + 1, j));
                pixels.add(i.getPixel(k + 1, j + 1));

                if (i.getType().equals("P3")) //image couleur
                {
                    newMat.setValue(posI, posJ, getAveragePixel(pixels, "P3"));
                } else //image noir et blanc
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
     * Get la valeur moyenne des pixels en paramètres. Retourne un pixel avec cette nouvelle valeur. Peut gérer un pixel RGB et un pixel noir et blanc.
     *
     * @param pixels ArrayList de pixels, c'est avec leur valeurs que nous effectuons la moyenne
     * @param type type du pixel, soit P3 (RGB) ou P2(noir et blanc)
     * @return retourne un pixel avec les moyennes des pixels en paramètre
     */
    private static Pixel getAveragePixel(ArrayList<Pixel> pixels, String type) {
        if (type.equals("P3")) //pixel RGB
        {
            int r = 0;          //rouge
            int g = 0;          //vert
            int b = 0;          //bleu

            for (Pixel p : pixels) //pour chaque pixel de l'array, on additionne sa valeur de RGB
            {
                r += ((PixelCouleur) p).getRed();
                g += ((PixelCouleur) p).getGreen();
                b += ((PixelCouleur) p).getBlue();
            }

            if (r > 0) {
                r /= pixels.size();      //on ne divise pas par 0
            }
            if (g > 0) {
                g /= pixels.size();
            }
            if (b > 0) {
                b /= pixels.size();
            }

            PixelCouleur newP = new PixelCouleur(r, g, b);
            return newP;

        } else //noir et blanc
        {
            int scale = 0;                      //niveau de gris
            for (Pixel p : pixels) //pour chaque pixel de l'array, on additionne son niveau de gris
            {
                scale += ((PixelMono) p).getScale();
            }

            if (scale != 0) {
                scale /= pixels.size(); //moyenne
            }
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
     * @param img Image à faire pivoter de 90° dans le sens horaire
     * @return la nouvelle image pivotée à 90° dans le sens horaire
     */
    public static Image pivoter90(Image img) {

        int newHeight = img.getHeight();        //hauteur de l'image pivotée
        int newWidth = img.getWidth();          //largeur de l'image pivotée

        MatricePixel newMat = new MatricePixel(newWidth, newHeight);

        for (int i = 0; i < newHeight; i++) //pour chaque pixel de la hauteur de l'image pivotée
        {
            for (int j = 0; j < newWidth; j++) //pour chaque pixel de la largeur de l'image pivotée
            {
                newMat.setValue(j, newHeight - 1 - i, img.getPixel(j, i)); //copie le pxiel de l'image origniale dans l'image pivotée
            }
        }

        Image newImg = new Image(newMat, img.getMaxValue());
        return newImg;
    }

}
