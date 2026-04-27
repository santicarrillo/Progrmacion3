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
          if (color.get(vertices2.next()).equals("BLANCO")){
              dfsrec(grafo,vertices2.next(),list);
          }
      }
        return list;
    }
    private void dfsrec(Grafo<T> grafo,int v,ArrayList<Integer> list){
        color.put(v,"Amarrillo");
        list.add(v);
        Iterator<Integer> adyacente=  grafo.obtenerAdyacentes(v);
        while(adyacente.hasNext()){
            int ady=adyacente.next();
            if (color.get(ady).equals("Amarrillo")){
                dfsrec(grafo,ady,list);
            }
        }
        color.put(v,"negro");
    }

    public boolean tieneCiclo(Grafo<T> grafo) {
        color = new HashMap<>();

        // inicializo todos en BLANCO
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            color.put(vertices.next(), "BLANCO");
        }

        // recorro vértices y llamo a tieneCicloVisit
        Iterator<Integer> vertices2 = grafo.obtenerVertices();
        while (vertices2.hasNext()) {
            int v = vertices2.next();
            if (color.get(v).equals("BLANCO")) {
                if (tieneCicloVisit(grafo, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tieneCicloVisit(Grafo<T> grafo, int v) {
        color.put(v, "AMARILLO");

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
        while (adyacentes.hasNext()) {
            int ady = adyacentes.next();
            if (color.get(ady).equals("AMARILLO")) {
                return true; // encontramos un ciclo!
            }
            if (color.get(ady).equals("BLANCO")) {
                if (tieneCicloVisit(grafo, ady)) {
                    return true;
                }
            }
        }

        color.put(v, "NEGRO");
        return false;
    }

}

