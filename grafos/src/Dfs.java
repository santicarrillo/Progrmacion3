import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    public List<List<Integer>> buscarCaminosEvitandoTramo(int origen, int destino, int ciudadA, int ciudadB) {
        List<List<Integer>> todosLosCaminos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        // Pasamos ciudadA y ciudadB como los extremos del tramo prohibido
        dfs(origen, destino, ciudadA, ciudadB, visitados, caminoActual, todosLosCaminos);

        return todosLosCaminos;
    }

    private void dfs(int actual, int destino, int prohibida1, int prohibida2,
                     Set<Integer> visitados, List<Integer> caminoActual,
                     List<List<Integer>> todosLosCaminos) {

        visitados.add(actual);
        caminoActual.add(actual);

        if (actual == destino) {
            todosLosCaminos.add(new ArrayList<>(caminoActual));
        } else {
            for (Integer vecino : this.grafo.obtenerAdyacentes(actual)) {

                // Verificamos si el movimiento actual representa el tramo cortado
                boolean esTramoCortado = (actual == prohibida1 && vecino == prohibida2) ||
                        (actual == prohibida2 && vecino == prohibida1);

                if (!visitados.contains(vecino) && !esTramoCortado) {
                    dfs(vecino, destino, prohibida1, prohibida2, visitados, caminoActual, todosLosCaminos);
                }
            }
        }

        // Backtracking
        caminoActual.remove(caminoActual.size() - 1);
        visitados.remove(actual);
    }
}

