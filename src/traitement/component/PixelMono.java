/*
 * TP1 en Programmation d'environement de base de données   (420-276-SH)
 */
package traitement.component;

/**
 * Pixel monochrome
 *
 * @author Antoine Gagnon
 */
public class PixelMono extends Pixel {

    /**
     * Niveau de gris du pixel Typiquement entre 0 et 255, 0 étant completement blanc et 255 étant completement noir Certaines implémentations utilise des valeurs plus
     * élevé que 255.=
     */
    private int scale;

    /**
     * Construit un pixel monochrome
     *
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
     *
     * @param scale teinte de gris
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * @return représentation du pixel en string
     */
    @Override
    public String toString() {
        return String.valueOf(scale);
    }

    /**
     * Compare le pixel à un autre pixle
     *
     * @param o
     * @return 0 si les pixels sont identique, 1 si le pixel (this) est plus "bright" et -1 si il est plus sombre
     */
    @Override
    public int compareTo(Pixel o) {

        if (o instanceof PixelCouleur) {
            PixelCouleur p = (PixelCouleur) o;
            if (getScale() == p.getBlue() && getScale() == p.getRed() && getScale() == p.getGreen()) {
                return 0;
            }
            if (getScale() > ((p.getBlue() + p.getRed() + p.getGreen()) / 3)) {
                return 1;
            } else {
                return -1;
            }
        } else if (o instanceof PixelMono) {
            if (getScale() == ((PixelMono) o).getScale()) {
                return 0;
            } else if (getScale() > ((PixelMono) o).getScale()) {
                return 1;
            } else {
                return -1;
            }
        }

        throw new UnsupportedOperationException("Can't compare" + o + " to " + this);
    }
}
