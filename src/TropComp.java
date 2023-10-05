import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class TropComp {
    private File directory;
    private double threshold;

    public TropComp(String directoryPath, double threshold) {
        this.directory = new File(directoryPath);
        this.threshold = threshold;
    }
    public List<FileObject> getSuspectClasses() {
        Tls tls = new Tls(directory);
        tls.setMute(true);
        tls.init();
        List<FileObject> allFileObjects = tls.getFileObjects();
        int tlocThresholdValue = getThresholdValueForTloc(allFileObjects);
        double tcmpThresholdValue = getThresholdValueForTcmp(allFileObjects);
        // Filtrer et renvoyer les classes suspectes basées sur les valeurs de seuil
        return allFileObjects.stream()
                .filter(file -> Integer.parseInt(file.toCsv().split(",")[3]) >= tlocThresholdValue &&
                                Double.parseDouble(file.toCsv().split(",")[5]) >= tcmpThresholdValue)
                .collect(Collectors.toList());
    }
    private int getThresholdValueForTloc(List<FileObject> allFileObjects) {
        List<FileObject> sortedByTloc = allFileObjects.stream()
                .sorted(Comparator.comparingInt(file -> Integer.parseInt(file.toCsv().split(",")[3])))
                .collect(Collectors.toList());
        int tlocThresholdIndex = (int) (sortedByTloc.size() * (1 - threshold));
        return Integer.parseInt(sortedByTloc.get(tlocThresholdIndex).toCsv().split(",")[3]);
    }
    private double getThresholdValueForTcmp(List<FileObject> allFileObjects) {
        List<FileObject> sortedByTcmp = allFileObjects.stream()
                .sorted(Comparator.comparingDouble(file -> Double.parseDouble(file.toCsv().split(",")[5])))
                .collect(Collectors.toList());
        int tcmpThresholdIndex = (int) (sortedByTcmp.size() * (1 - threshold));
        return Double.parseDouble(sortedByTcmp.get(tcmpThresholdIndex).toCsv().split(",")[5]);
    }
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: TropComp [-o <chemin-à-la-sortie.csv>] <chemin-de-l'entrée> [<seuil>]");
            return;
        }
        String outputPath = null;
        String inputPath = null;
        Double threshold = 0.1;  // Valeur par défaut si le seuil n'est pas précisé 
        int argIndex = 0;
        if ("-o".equals(args[argIndex])) {
            outputPath = args[++argIndex];
            argIndex++;
        }

        inputPath = args[argIndex];
        if (args.length > argIndex + 1) {
            try {
                threshold = Double.parseDouble(args[argIndex + 1]);
            } catch (NumberFormatException e) {
                System.err.println("Erreur: Le seuil doit être un nombre valide.");
                return;
            }
        }

        TropComp tropComp = new TropComp(inputPath, threshold);
        List<FileObject> suspectClasses = tropComp.getSuspectClasses();

        if (outputPath != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                for (FileObject suspect : suspectClasses) {
                    writer.write(suspect.toCsv());
                    writer.newLine();
                }
                System.out.println("Le fichier CSV a été créé avec succès à l'emplacement: " + outputPath);
            } catch (IOException e) {
                System.err.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
            }
        } else {
            System.out.println("classes suspectes : ");
            for (FileObject suspect : suspectClasses) {
                System.out.println(suspect.toCsv());
            }
        }
    }
}
