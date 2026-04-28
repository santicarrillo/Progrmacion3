import java.util.*;

public class Bfs<T> {

    HashMap<Integer,String> visito;

    public ArrayList<Integer> bfs(Grafo<T>grafo, int v) {
        ArrayList<Integer> resultado = new ArrayList<>();// crea el resultado
        Queue<Integer> cola = new LinkedList<>();// la fila
        visito = new HashMap<>();// crea el hashmap

        visito.put(v, "visito"); // agrego el hashmap
        cola.add(v);// agrega a la cola
        while (!cola.isEmpty()) {// si la cola no esta vacia
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



    public ArrayList<Integer> camMascorto(Grafo<T>grafo,int nodo1,int nodo2){
        ArrayList<Integer> camino = new ArrayList<>();
        HashMap<Integer, Integer> padre = new HashMap<>();
        Queue<Integer> cola = new LinkedList<>();
        visito = new HashMap<>();

        visito.put(nodo1, "visito");
        padre.put(nodo1, -1);//nodo1 no tiene padre
        cola.add(nodo1);

        while (!cola.isEmpty()) {
            int vertice = cola.poll();

            Iterator<Integer> adya= grafo.obtenerAdyacentes(vertice);
            while (adya.hasNext()) {
                int Adyacente = adya.next();
                if (padre.containsKey(Adyacente)) {
                    visito.put(Adyacente, "visito");
                    padre.put(Adyacente, vertice);
                    cola.add(Adyacente);
                }
                if (Adyacente == nodo2) {
                    int actual = nodo2;
                    while (actual!=-1){
                        camino.add(0, actual);
                        actual= padre.get(actual);
                    }
                }
                return camino;
            }
        }
        return camino;
    }


}
