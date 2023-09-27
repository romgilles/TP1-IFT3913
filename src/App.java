import java.io.File;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {
        Tls tls = new Tls(new File("/Users/mahmo/Downloads/jfreechart-master/jfreechart-master/src/test/java/org/jfree/chart/title"));
        tls.getJavaFiles();
        tls.createFileObject2();
        String directoryPath = "C:/Users/mahmo/Downloads/jfreechart-master/jfreechart-master/src/test"; 
        double threshold = 0.1; // pour 10%

        TropComp tropComp = new TropComp(directoryPath, threshold);
        List<FileObject> suspectClasses = tropComp.getSuspectClasses();

        System.out.println("Classes suspectes :");
        for (FileObject suspect : suspectClasses) {
            System.out.println(suspect.toCsv());}
                 ;
                        
    }
}
