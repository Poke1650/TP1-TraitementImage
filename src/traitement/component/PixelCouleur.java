package traitement.component;

import java.awt.Color;

/**
 * Un pixel de couleur ayant un teinte de rouge, vert, bleu (RGB)
 * @author Antoine Gagnon
 */
public class PixelCouleur extends Pixel {
    
    /**
     * Teinte de rouge du pixel
     */
    private int red;
    
    /**
     * Teinte de vert du pixel
     */
    private int green;
    
    /**
     * Teinte de bleu du pixel
     */
    private int blue;
    
    /**
     * Construit un pixel de la couleur passer en paramètre
     * @param couleur couleur du pixel
     */
    public PixelCouleur(Color couleur) {
        red = couleur.getRed();
        green = couleur.getGreen();
        blue = couleur.getBlue();
    }
    
    /**
     * Construit un pixel de la couleur passer en paramètre
     * @param r teinte de rouge
     * @param g teinte de vert
     * @param b teinte de bleu
     */
    public PixelCouleur(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    /**
     * @return un objet Color de la couleur du pixel
     */
    public Color getCouleur() {
        return new Color(red, green, blue);
    }

    /** 
     * @return la teinte de rouge du pixel
     */
    public int getRed() {
        return red;
    }

    /**
     * Set la teinte de rouge
     * @param red teinte de rouge
     */
    public void setRed(int red) {
        this.red = red;
    }
    
    /**
     * @return la teinte de vert du pixel
     */
    public int getGreen() {
        return green;
    }
    
    /**
     * Set la teinte de vert
     * @param green teinte de vert
     */
    public void setGreen(int green) {
        this.green = green;
    }

    /**
     * @return la teinte de bleu du pixel
     */
    public int getBlue() {
        return blue;
    }
    
    /**
     * Set la teinte de bleu
     * @param blue teinte de bleu
     */
    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     * @return représentation de l'objet sous forme de string
     */
    @Override
    public String toString() {
        return getRed() + " " + getGreen() + " " + getBlue();
    }
    
     

}
