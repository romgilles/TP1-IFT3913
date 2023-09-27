import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TropComp {
    private File directory;
    private double threshold;
    public TropComp(String directoryPath, double threshold) {
        this.directory = new File(directoryPath);
        this.threshold = threshold;
    }
    // Récupère les classes suspectes basées sur les valeurs de seuil pour tloc et tcmp
    public List<FileObject> getSuspectClasses() {
        Tls tls = new Tls(directory);
        tls.getJavaFiles();
        List<FileObject> allFileObjects = tls.getFileObjects();
        // Déterminer la valeur de seuil pour tloc
        int tlocThresholdValue = getThresholdValueForTloc(allFileObjects);
        // Déterminer la valeur de seuil pour tcmp
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
}
