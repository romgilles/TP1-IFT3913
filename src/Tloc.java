import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tloc {
    
    private String path;
    public Tloc(String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Tloc <file>");
            System.exit(1);
        }
        Tloc tloc = new Tloc(args[0]);
        System.out.println(tloc.calculateTLOC());
    }

    public int calculateTLOC() {
        int tloc = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line;
            boolean inBlockComment = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("//")) {
                    // Ignore empty lines and single-line comments
                    continue;
                }

                if (line.startsWith("/*")) {
                    inBlockComment = true;
                    // Handle the start of a block comment
                    if (line.endsWith("*/")) {
                        inBlockComment = false;
                    }
                } else if (line.endsWith("*/")) {
                    // Handle the end of a block comment
                    inBlockComment = false;
                } else if (!inBlockComment) {
                    // If not inside a block comment, count the line
                    tloc++;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            System.exit(1);
        }

        return tloc;
    }
}
