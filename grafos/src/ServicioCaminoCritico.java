import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServicioCaminoCritico <T>{

    private  Grafo<Integer> grafo= new GrafoDirigido<>();
    private HashMap<Integer,Tarea> tareas= new HashMap<>();


public ArrayList<Integer> caminoCritico(){
        ArrayList<Integer> caminoActual = new ArrayList<>();
        ArrayList<Integer> caminoMasLargo = new ArrayList<>();
        int[] tiempoMaximo = {0}; // tiempo del camino más largo

        // recorrer todos los vértices como posible inicio
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int v = vertices.next();
            caminoCri(v,-1, caminoActual, caminoMasLargo, tiempoMaximo, 0);
        }

        return caminoMasLargo;
}

    private void caminoCri(int actual, int padre, ArrayList<Integer> caminoActual,
                           ArrayList<Integer> caminoMasLargo,
                           int[] tiempoMaximo, int tiempoAcumulado) {

        // calculo tiempo de espera si tiene padre
        int tiempoEspera = 0;
        if (padre != -1) {
            tiempoEspera = grafo.obtenerArco(padre, actual).getEtiqueta();
        }

        // sumo duración de la tarea actual y tiempo de espera
        int tiempoTotal = tiempoAcumulado + tareas.get(actual).getDuracion() + tiempoEspera;

        caminoActual.add(actual); // entro al vértice

        // si es el camino más largo hasta ahora lo guardo
        if (tiempoTotal > tiempoMaximo[0]) {
            tiempoMaximo[0] = tiempoTotal;
            caminoMasLargo.clear();
            caminoMasLargo.addAll(caminoActual);
        }

        // sigo explorando adyacentes
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
        while (adyacentes.hasNext()) {
            int ady = adyacentes.next();
            caminoCri(ady, actual, caminoActual, caminoMasLargo, tiempoMaximo, tiempoTotal);
        }

        caminoActual.remove(caminoActual.size() - 1); // backtracking
    }
}
