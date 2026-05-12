import java.util.ArrayList;

public class Establo {

    private int[][] matriz = new int[0][0];
   private ArrayList<Integer>Pisadas = new ArrayList();
   private int pisadaActua;

    public Establo(int[][] matriz, int pisadaActua, ArrayList<Integer> pisadas) {
        this.matriz = matriz;
        this.pisadaActua = pisadaActua;
        Pisadas = pisadas;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getPisadaActua() {
        return pisadaActua;
    }

    public void setPisadaActua(int pisadaActua) {
        this.pisadaActua = pisadaActua;
    }

    public ArrayList<Integer> getPisadas() {
        return Pisadas;
    }

    public void setPisadas(ArrayList<Integer> pisadas) {
        Pisadas = pisadas;
    }
}
