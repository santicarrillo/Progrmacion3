import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, List<Arco <T>>> vertices;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
    }
    @Override
        public void agregarVertice(int verticeId) {
        if (!vertices.containsKey(verticeId)) {
            vertices.put(verticeId, new ArrayList<>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        vertices.remove(verticeId);// borra el vertice y todos sus salientes
        for (List<Arco<T>> arcos : vertices.values()) {
            arcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        agregarVertice(verticeId1);
        agregarVertice(verticeId2);

        // Paso 2: agrego el arco a la lista del vértice origen
        vertices.get(verticeId1).add(new Arco<>(verticeId1, verticeId2, etiqueta));
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
            vertices.get(verticeId1).removeIf(arco -> arco.getVerticeDestino() == verticeId2);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
            for (Arco<T> arco : vertices.get(verticeId1)) {
                if (arco.getVerticeDestino() == verticeId2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
            for (Arco<T> arco : vertices.get(verticeId1)) {
                if (arco.getVerticeDestino() == verticeId2) {
                    return arco;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int cantidad = 0;
        for (List<Arco<T>> arcos : vertices.values()) {
            cantidad += arcos.size();
        }
        return cantidad;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
            return vertices.keySet().iterator();// me itera todas las claves de los vertices
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<Integer> adyacentes = new ArrayList<>();// guarda todos los adyasentes del vertice
        if (vertices.containsKey(verticeId)) {// todos los vertices que contengan la clave
            for (Arco<T> arco : vertices.get(verticeId)) { // trae todos los arcos con ese id origen
                adyacentes.add(arco.getVerticeDestino());// y agrega los arrcos completos
            }
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        List<Arco<T>> arcos = new ArrayList<>();
        for (List<Arco<T>> listaArcos : vertices.values()) {
            arcos.addAll(listaArcos);
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if (vertices.containsKey(verticeId)) {
            return vertices.get(verticeId).iterator();
        }
        return new ArrayList<Arco<T>>().iterator();
    }





    }
