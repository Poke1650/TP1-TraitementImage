package traitement.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import traitement.component.Pixel;

/**
 *
 * @author Antoine Gagnon
 */
public abstract class ImageParser implements IImageParser{

    /**
     * Map contenant tout les metadata du fichier
     */
    Map<String, Object> metadata = new HashMap<>();
    
    /**
     * Matrix de pixel représentant l'image
     * TODO: change Pixel for a Matrix instance
     */
    Pixel[][] px;
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void read(String path) throws FileNotFoundException, ParseException {
        read(new File(path));
    }
    
    /**
    * {@inheritDoc}
    */    
    @Override
    public String getHeader() {
        return String.valueOf(metadata.get("header"));
    }

    /**
    * {@inheritDoc}
    */    
    @Override
    public int getHeight() {
        return (int) metadata.get("height");
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public int getWidth() {
        return (int) metadata.get("width");
    }

    /**
    * {@inheritDoc}
    */  
    @Override
    public int getMaxValue() {
        return (int) metadata.get("max_value");
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Pixel[][] getPixelMatrix() {
        return px;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Map getMetadata() {
        return metadata;
    }

}
