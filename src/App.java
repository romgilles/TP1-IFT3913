public class App {
    public static void main(String[] args) throws Exception {
        Tloc tloc = new Tloc("src/Tloc.java");
        int tlocCount = tloc.calculateTLOC();
        System.out.println("TLOC: " + tlocCount);
    }
}
