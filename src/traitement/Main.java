package traitement;

/**
 *
 * @author Antoine Gagnon
 */
public class Main {

    public static void main(String[] args) {
        Matrice matrice = new Matrice(7, 5);
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                matrice.setValue(i, j, i * 5 + j);
            }
        }
        
        System.out.println(matrice.getHeight());
        System.out.println(matrice.getWidth());
        matrice.print();
        
        matrice.resize(10,10);
        System.out.println("============");
        
        System.out.println(matrice.getHeight());
        System.out.println(matrice.getWidth());
        matrice.print();
        
    }
}
