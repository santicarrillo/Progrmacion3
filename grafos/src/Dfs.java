import java.util.*;
import java.util.stream.Gatherer;

public class Dfs<T> {

    private HashMap<Integer,String> color;


    public ArrayList<Integer> dfs(Grafo<T> grafo) {
      color= new HashMap<>();
      ArrayList<Integer> list= new ArrayList<>();

    Iterator<Integer> vertices = grafo.obtenerVertices();

      while(vertices.hasNext()){
        color.put(vertices.next(),"BLANCO");
      }

      Iterator<Integer> vertices2= grafo.obtenerVertices();
      while(vertices2.hasNext()){
          vertices2.next();
          if (color.get(vertices.next()).equals("BLANCO")){
              dfsrec(grafo,vertices.next(),list);
          }
      }
        return list;
    }
    private void dfsrec(Grafo<T> grafo,int v,ArrayList<Integer> list){
        color.put(v,"AMARILLO");
        list.add(v);
        Iterator<Integer> adyacente=  grafo.obtenerAdyacentes(v);
        while(adyacente.hasNext()){
            int ady=adyacente.next();
            if (color.get(ady).equals("BLANCO")){
                dfsrec(grafo,ady,list);
                
            } else if (color.containsKey("AMARILLO")) {
                System.out.println("Hay ciclo");
            }
        }
        color.put(v,"negro");
    }
    public ArrayList<Integer> caminoMasLargo(Grafo<T> grafo,int i, int j) {
        ArrayList<Integer> caminoMasLargo = new ArrayList<>();
        ArrayList<Integer>  caminoActual = new ArrayList<>();

        caminoRecto(grafo,i,j,caminoActual,caminoMasLargo);

        return caminoMasLargo;
    }


    private ArrayList<Integer>caminoRecto(Grafo<T> grafo, int actual ,int j,
                                          ArrayList<Integer> caminoActual,
                                          ArrayList<Integer> caminoMasLargo) {
        caminoActual.add(actual);

        if (actual==j){
            if (caminoMasLargo.size()<caminoActual.size()){
                caminoMasLargo.clear();
                caminoMasLargo.addAll(caminoActual);
            }
        }else {
            // SIGUE BUSCANDO ADYACENTE
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                caminoRecto(grafo,adyacentes.next(),j,caminoActual,caminoMasLargo);
            }
        }
        caminoMasLargo.remove(caminoActual.size()-1);

        return caminoMasLargo;
    }

    public List<List<Integer>> buscarCaminosEvitandoTramo(Grafo<T>grafo,int origen, int destino, int ciudadA, int ciudadB) {
        List<List<Integer>> todosLosCaminos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        // Pasamos ciudadA y ciudadB como los extremos del tramo prohibido
        dfs(grafo,origen, destino, ciudadA, ciudadB, visitados, caminoActual, todosLosCaminos);

        return todosLosCaminos;
    }

    private void dfs(Grafo<?> grafo, int actual, int destino, int p1, int p2,
                     Set<Integer> visitados, List<Integer> caminoActual,
                     List<List<Integer>> resultados) {

        // 1. PINTAMOS: Marcamos el nodo actual como parte de la ruta
        visitados.add(actual);
        caminoActual.add(actual);

        // 2. BASE: Si llegamos a Tandil (destino), guardamos una copia del camino
        if (actual == destino) {
            resultados.add(new ArrayList<>(caminoActual));
        } else {
            // 3. EXPLORACIÓN: Buscamos vecinos
            Iterator<Integer> adyacentes = (Iterator<Integer>) grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int vecino = adyacentes.next();

                // 4. VALIDACIÓN: ¿Está el tramo cortado?
                boolean tramoObstruido = (actual == p1 && vecino == p2) || (actual == p2 && vecino == p1);

                if (!visitados.contains(vecino) && !tramoObstruido) {
                    dfs(grafo, vecino, destino, p1, p2, visitados, caminoActual, resultados);
                }
            }
        }

        // 5. DESPINTAMOS (Backtracking): Quitamos el nodo para permitir otras rutas
        caminoActual.remove(caminoActual.size() - 1);
        visitados.remove(actual);
    }
}

