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
        this.tcmp = (double) tloc/tassert;
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%d,%d,%.2f", this.path, this.packageName, this.name, this.tloc, this.tassert,
                this.tcmp);
    }

}
