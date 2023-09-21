import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tls {

    private File pathString;
    private ArrayList<String> javaFileList = new ArrayList<>();
    private ArrayList<FileObject> fileObjectsList = new ArrayList<>();

    
    public Tls(File pathString) {
        this.pathString = pathString;
    }

     public void createFileObject2(){
        for (String string : javaFileList) {
            FileObject fileObject = createFileObject(string);
            System.out.println(fileObject.toCsv());
            fileObjectsList.add(fileObject);
        }
    } 

    public FileObject createFileObject(String filePath){
        File file = new File(filePath);
        String fileName = file.getName();
        
        Path source = Paths.get(this.pathString.getPath());
        Path relative = Paths.get(file.getPath());
        Path path = source.relativize(relative);

        //obtenir le nom complet du package en remontant jusqu'à java et en enlevant le nom du fichier
        String packageName = "";
        
        int index = filePath.indexOf("java");
        if (index != -1) {
            packageName = filePath.substring(index + 5, filePath.length() - fileName.length() - 1);
        }
        packageName = packageName.replace('/', '.');
        
        String pathString = path.toString();

        Tloc tloc = new Tloc(filePath);
        Tassert tassert = new Tassert(filePath);

        int tlocValue = tloc.calculateTLOC();
        int tassertValue = tassert.calculateTASSERT();
        FileObject fileObject = new FileObject("./"+pathString, packageName, fileName, tlocValue, tassertValue);
        return fileObject;
    }

    public void getJavaFiles(){
        getJavaFiles(pathString);
    }

    private void getJavaFiles(File pathString) {

        File[] files = pathString.listFiles();
        
        for (File file : files) {
            if (file.isDirectory()) {
                getJavaFiles(file);
            } 
            else if (file.getName().endsWith(".java")) {
                String filePath = file.getPath();
                javaFileList.add(filePath);
            }
        }
        return ;
    }
}
