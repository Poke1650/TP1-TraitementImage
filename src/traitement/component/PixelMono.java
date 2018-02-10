package traitement.component;

/**
 * Pixel monochrome
 * @author Antoine Gagnon
 */
public class PixelMono extends Pixel {
    
    /**
     * Niveau de gris du pixel
     * Typiquement entre 0 et 255, 0 étant completement blanc et 255 étant completement noir
     * Certaines implémentations utilise des valeurs plus élevé que 255.=
     */
    private int scale;

    /**
     * Construit un pixel monochrome
     * @param scale la teinte de gris
     */
    public PixelMono(int scale) {
        this.scale = scale;
    }

    /**
     * @return teinte de gris du pixel 
     */
    public int getScale() {
        return scale;
    }

    /**
     * Set la teinte du pixel
     * @param scale 
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * @return représentation du pixel en string
     */
    @Override
    public String toString() {
        return "PixelMono[scale=" + scale + "]";
    }
    
    

    
    
}
