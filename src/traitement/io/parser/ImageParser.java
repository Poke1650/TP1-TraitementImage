package traitement.io.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import traitement.component.MatricePixel;

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
     */
    MatricePixel px;
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void read(String path) throws FileNotFoundException, ParseException {
        read(new File(path));
    }
    
    @Override
    public void read(File file) throws FileNotFoundException, ParseException {
        
        Scanner sc = new Scanner(file);
        int currentLine = 1;
        try {
            metadata.put("file_name", file.getName());
            metadata.put("header", sc.next());
            currentLine++;
            metadata.put("width", sc.nextInt());
            metadata.put("height", sc.nextInt());
            currentLine++;
            metadata.put("max_value", sc.nextInt());

            readPixels(sc);

        } catch (NoSuchElementException e) {
            throw new ParseException("Error parsing file" + file.getAbsolutePath(), currentLine);
        }
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
    public MatricePixel getPixelMatrix() {
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
