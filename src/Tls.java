import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tls {

    private File pathString;
    private ArrayList<String> javaFileList = new ArrayList<>();
    private ArrayList<FileObject> fileObjectsList = new ArrayList<>();
    private boolean mute = false; 
    public Tls(File pathString) {

        this.pathString = pathString;
    }
    public void setMute(boolean mute) {
        this.mute = mute;
    }
    public void init() {
        getJavaFiles();
        createFileObject2();
    }
    public static void main(String args[]){
        String outputFile = null;
        String entryPath = null;
        
        if (args.length == 0) {
            //obtenir le chemin absolu de ou se trouve le fichier Tls.java
            File file = new File(".");
            String path = file.getAbsolutePath();
            System.out.println(path);
            Tls tls = new Tls(new File(path));
            tls.getJavaFiles();
            tls.createFileObject2();
        }

        if (args.length == 3 && "-o".equals(args[0])){
            outputFile = args[1];
            entryPath = args[2];
            File file = new File(entryPath);
            Tls tls = new Tls(file);
            tls.getJavaFiles();
            tls.createFileObject2();
            tls.generateCsvFile(outputFile);
        }
        
    }

    public void createFileObject2(){
        for (String string : javaFileList) {
            FileObject fileObject = createFileObject(string);
            if (!mute) {
                System.out.println(fileObject.toCsv());
            }
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
        getJavaFiles(this.pathString);
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
    
    public ArrayList<FileObject> getFileObjects() {
        return fileObjectsList;
    }

    public int generateCsvFile(String outputPath){
        String fileName = "output.csv";

        Path directoryPath = Paths.get(outputPath);
        Path filePath = directoryPath.resolve(fileName);
        try {
            if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), false));
                for(int i= 0; i < fileObjectsList.size(); i++){
                    FileObject fileObject = fileObjectsList.get(i);
                    String line = fileObject.toCsv();
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Le fichier " + fileName + " a été créé avec succès.");       

            }
            else {
                System.out.println("Le dossier spécifié n'existe pas.");
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
            return 1;
        }
        
}
