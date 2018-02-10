package traitement.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import traitement.component.MatricePixel;
import traitement.component.Pixel;
import traitement.component.PixelMono;

/**
 *
 * @author Antoine Gagnon
 */
public class PGMParser extends ImageParser{

    /**
    * {@inheritDoc}
    */
    @Override
    public void read(File file) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(file);
        int currentLine = 1;
        try {
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
    private void readPixels(Scanner sc) {
        
        px = new MatricePixel(getHeight(), getWidth());
        
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                px.setValue(i, j, new PixelMono(sc.nextInt()));
            }
        }
    }
}
