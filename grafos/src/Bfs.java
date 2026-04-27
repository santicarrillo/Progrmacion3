import java.util.*;

public class Bfs<T> {

    HashMap<Integer,String> visito;

    public ArrayList<Integer> bfs(Grafo<T>grafo, int v) {
        ArrayList<Integer> resultado = new ArrayList<>();
        Queue<Integer> cola = new LinkedList<>();
        visito = new HashMap<>();
        visito.put(v, "visito");
        cola.add(v);
        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            Iterator<Integer> adya= grafo.obtenerAdyacentes(vertice);
            while (adya.hasNext()) {
                int Adyacente = adya.next();
                if (!visito.containsKey(Adyacente)) {
                    visito.put(Adyacente,"visito");
                    cola.add(Adyacente);
                }
            }
            resultado.add(vertice);
        }
        return resultado;
    }
}
