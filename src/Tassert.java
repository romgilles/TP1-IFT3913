import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tassert {
    
    //chemin du fichier a analyser
    private String path;
    public Tassert(String path) {
        this.path = path;
    }

    public static void main(String args[]){
        if (args.length != 1) {
            System.err.println("Usage: java Tassert <file>");
            System.exit(1);
        }
        Tassert tassert = new Tassert(args[0]);
        System.out.println(tassert.calculateTASSERT());

    }

    public int calculateTASSERT() {
        int tassert = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
            
                if (line.matches(".*assert.*")) {
                    tassert++;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            System.exit(1);
        }

        return tassert;
    }
}
