import java.io.File;
public class App {
    public static void main(String[] args) throws Exception {
        
        String directoryPath = "/home/roman/Bureau/AUTOMNE 2023/ift 3913/tp1/jfreechart/src/test/java/org/jfree/chart/title/";
        String outputPath = "/home/roman/Bureau/AUTOMNE 2023/ift 3913/tp1/TP1/output/";
        Tls tls = new Tls(new File(directoryPath));
        tls.generateCsvFile(outputPath);
    }
}
