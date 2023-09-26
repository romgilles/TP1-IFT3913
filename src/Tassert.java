import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tassert {
    private String path;

    public Tassert(String path) {
        this.path = path;
    }

    public int calculateTASSERT() {
        int tassert = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                //todo : check a good regex
                if (line.matches(".*assert.*")) {
                    // Check for an assertion method call pattern
                    
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
