package traitement.io.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import traitement.Image;

/**
 *
 * @author Antoine Gagnon
 */
public class ImageWriter {
    
    private Image image = null;
    private File file = null;
    
    public ImageWriter(Image image, File file) {
        this.image = image;
        this.file = file;
    }
    
    public ImageWriter(Image image) {
        this.image = image;
    }
    
    public ImageWriter(File file) {
        this.file = file;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public void setFile(String path) {
        this.file = new File(path);
    }
    
    public void write(Image image, File file) throws IOException {
        setImage(image);
        setFile(file);
        write();
    }
    
    public void write() throws IOException {
        FileWriter fr = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fr);
        
        printHeader(pw);
        printPixels(pw);
    }
    
    private void printPixels(PrintWriter pw) throws IOException {
    for (int i = 0; i < image.getHeight(); i++)     //jusqu'à la plus petite hauteur entre la nouvelle et l'ancienne matrice   
        {
            for (int j = 0; j < image.getWidth(); j++)   //jusqu'à la plus petite largeur entre la nouvelle et l'ancienne matrice
            {
                pw.print(image.getPixel(j, i).toString());
                if(j != image.getWidth()){
                    pw.print(" ");
                }
            }  
            pw.print("\n");
        }
    }
    private void printHeader(PrintWriter pw) {
        pw.printf("%s\n%d %d\n%d\n", image.getType(), image.getWidth(), image.getHeight(), image.getMaxValue());
    }
}
