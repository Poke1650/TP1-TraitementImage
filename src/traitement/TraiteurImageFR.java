/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import java.util.ArrayList;
import traitement.component.MatricePixel;
import traitement.component.Pixel;
import traitement.component.PixelCouleur;
import traitement.component.PixelMono;

/**
 *
 * @author Francis
 */
public class TraiteurImageFR {
    
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
    public Image extraire(Image i, int p1, int c1, int p2, int c2)
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
     * Créé une nouvelle image avec une dimension de 50% de l'orginale. Les pixels de l'originale sont
     * arrondis pour recréer la nouvelle image.
     * @param i image originale à redimensionner
     * @return la nouvelle image redimensionnée
     */
    public Image reduire(Image i)
    {
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
    private Pixel getAveragePixel(ArrayList<Pixel> pixels, String type)
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
    
    
}
