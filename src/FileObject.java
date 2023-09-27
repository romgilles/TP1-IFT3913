import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FileObject {
    private String path;
    private String packageName;
    private String name;
    private int tloc;
    private int tassert;
    private double tcmp;

    public FileObject(String path, String packageName, String name, int tloc, int tassert) {
        this.path = path;
        this.packageName = packageName;
        this.name = name;
        this.tloc = tloc;
        this.tassert = tassert;
         // Vérification pour éviter la division par zéro 
        if (tassert == 0) {
            this.tcmp = 0;
        } else {
            this.tcmp = (double) tloc / tassert;
        }
    }

    public String toCsv() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.##", symbols);
        
        return String.format("%s,%s,%s,%d,%d,%s", this.path, this.packageName, this.name, this.tloc, this.tassert,
                format.format(this.tcmp));
    
    }

}
