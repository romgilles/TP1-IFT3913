import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Tls tls = new Tls(new File("/home/roman/Bureau/AUTOMNE 2023/ift 3913/tp1/TP1/jfreechart/src/test/java/org/jfree/chart/title/"));
        tls.getJavaFiles();
        tls.createFileObject2();
    }
}
