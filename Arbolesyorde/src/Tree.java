import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Treenode root;

    public Tree() {
        this.root = null;
    }

    public int getRoot() {
        return this.root != null ? this.root.getValue() : 0;
    }

    public boolean hasElement(int element) {
        return hasElement(this.root, element);
    }

    private boolean hasElement(Treenode actual, int element) {
        if (actual == null) {
            // Caso base: llegamos a un nodo vacío, no está el elemento
            return false;
        }

        if (element == actual.getValue()) {
            // Caso encontrado: el valor del nodo coincide con el buscado
            return true;
        } else if (element < actual.getValue()) {
            // Si el valor buscado es menor, seguimos por la izquierda
            return hasElement(actual.getLeft(), element);
        } else {
            // Si el valor buscado es mayor, seguimos por la derecha
            return hasElement(actual.getRight(), element);
        }
    }

    public void add(Integer value) {
        if (this.root == null)
            this.root = new Treenode(value);
        else
            this.add(this.root, value);
    }

    private void add(Treenode actual, Integer value) {
        // Compara el valor del nodo actual con el valor que queremos insertar
        if (actual.getValue() > value) {
            // Si el valor a insertar es menor que el valor del nodo actual,
            // entonces debe ir en el subárbol izquierdo.

            if (actual.getLeft() == null) {
                // Si el hijo izquierdo está vacío (null),
                // significa que encontramos el lugar donde insertar.
                Treenode temp = new Treenode(value);
                // Creamos un nuevo nodo con el valor.
                actual.setLeft(temp);
                // Lo asignamos como hijo izquierdo del nodo actual.
            } else {
                // Si el hijo izquierdo NO está vacío,
                // seguimos bajando recursivamente por la izquierda.
                add(actual.getLeft(), value);
            }

        } else if (actual.getValue() < value) {
            // Si el valor a insertar es mayor que el valor del nodo actual,
            // entonces debe ir en el subárbol derecho.

            if (actual.getRight() == null) {
                // Si el hijo derecho está vacío,
                // encontramos el lugar donde insertar.
                Treenode temp = new Treenode(value);
                // Creamos un nuevo nodo con el valor.
                actual.setRight(temp);
                // Lo asignamos como hijo derecho del nodo actual.
            } else {
                // Si el hijo derecho NO está vacío,
                // seguimos bajando recursivamente por la derecha.
                add(actual.getRight(), value);
            }
        }
        // Nota: si actual.getValue() == value, no hace nada.
        // Este código no contempla duplicados.
    }

    public boolean isEmpty() {
        return (this.root == null);
    }

    public boolean delete(Integer element) {
        if (this.root == null) return false;

        if (this.root.getValue().equals(element)) {
            return deleteRaiz(element);
        }
        // Llamada correcta a la recursión con padre null (si no existe padre)
        return this.delete(this.root, null, element);
    }

    private boolean deleteRaiz(Integer element) {
        if (this.root == null) return false;
        if (!this.root.getValue().equals(element)) return false;

        Treenode izq = this.root.getLeft();
        Treenode derecha = this.root.getRight();

        // Caso: solo hoja
        if (esHoja(this.root)) {
            this.root = null;
            return true;
        }

        // Caso: tiene subárbol izquierdo -> usar mayor de la izquierda como reemplazo
        if (izq != null) {
            Treenode reemplazo = nodoMasDerecha(izq);
            // Copiamos el valor del reemplazo a la raíz
            this.root.setValue(reemplazo.getValue());
            // Borramos el nodo reemplazo en el subárbol izquierdo
            delete(this.root.getLeft(), this.root, reemplazo.getValue());
            return true;
        }

        // Caso: no tiene izquierda pero sí derecha -> usar menor de la derecha
        if (derecha != null) {
            Treenode reemplazo = nodoMasIzq(derecha);
            this.root.setValue(reemplazo.getValue());
            delete(this.root.getRight(), this.root, reemplazo.getValue());
            return true;
        }

        return false;
    }

    private boolean delete(Treenode actual, Treenode padre, Integer eliminar) {
        if (actual == null) {
            return false; // No se encontró nada
        }

        if (eliminar < actual.getValue()) {
            return delete(actual.getLeft(), actual, eliminar);
        } else if (eliminar > actual.getValue()) {
            return delete(actual.getRight(), actual, eliminar);
        } else {
            // Encontramos el nodo a borrar
            if (esHoja(actual)) {
                return borrarHoja(actual, padre);
            } else if (tieneSoloHijoIzq(actual)) {
                return borrarConHijoIzq(actual, padre);
            } else if (tieneSoloHijoDer(actual)) {
                return borrarConHijoDer(actual, padre);
            } else {
                return borrarConDosHijos(actual);
            }
        }
    }

    private boolean esHoja(Treenode nodo) {
        return nodo.getLeft() == null && nodo.getRight() == null;
    }

    private boolean tieneSoloHijoIzq(Treenode nodo) {
        return nodo.getLeft() != null && nodo.getRight() == null;
    }

    private boolean tieneSoloHijoDer(Treenode nodo) {
        return nodo.getLeft() == null && nodo.getRight() != null;
    }

    private boolean borrarHoja(Treenode nodo, Treenode padre) {
        if (padre == null) {
            // Si padre es null y es hoja, entonces es la raíz
            this.root = null;
            return true;
        }
        if (padre.getLeft() == nodo) {
            padre.setLeft(null);
        } else if (padre.getRight() == nodo) {
            padre.setRight(null);
        }
        return true;
    }

    private boolean borrarConHijoIzq(Treenode nodo, Treenode padre) {
        if (padre == null) {
            // Si es la raíz con solo hijo izquierdo
            this.root = nodo.getLeft();
            return true;
        }
        if (padre.getLeft() == nodo) {
            padre.setLeft(nodo.getLeft());
        } else if (padre.getRight() == nodo) {
            padre.setRight(nodo.getLeft());
        }
        return true;
    }

    private boolean borrarConHijoDer(Treenode nodo, Treenode padre) {
        if (padre == null) {
            // Si es la raíz con solo hijo derecho
            this.root = nodo.getRight();
            return true;
        }
        if (padre.getLeft() == nodo) {
            padre.setLeft(nodo.getRight());
        } else if (padre.getRight() == nodo) {
            padre.setRight(nodo.getRight());
        }
        return true;
    }

    private boolean borrarConDosHijos(Treenode nodo) {
        // Usamos el menor del subárbol derecho como reemplazo
        Treenode reemplazo = nodoMasIzq(nodo.getRight());
        nodo.setValue(reemplazo.getValue());
        delete(nodo.getRight(), nodo, reemplazo.getValue());
        return true;
    }

    private Treenode nodoMasDerecha(Treenode actual) {
        while (actual.getRight() != null) {
            actual = actual.getRight();
        }
        return actual;
    }

    private Treenode nodoMasIzq(Treenode actual) {
        while (actual.getLeft() != null) {
            actual = actual.getLeft();
        }
        return actual;
    }

    public int getHeight() {
        return getHeight(this.root);
    }

    private int getHeight(Treenode actual) {
        if (actual == null) {
            return 0;
        }
        int left = getHeight(actual.getLeft());
        int rigth = getHeight(actual.getRight());

        int mayor = 0;
        if (left > rigth) {
            mayor = left;
        } else {
            mayor = rigth;
        }
        return mayor + 1;
    }

    public void printPostOrder() {
        getPrintPostOrder(this.root);
    }

    private void getPrintPostOrder(Treenode actual) {
        if (actual == null) {
            System.out.println("-");
            return;
        }

        // paso 1 : recorre por rama izq
        getPrintPostOrder(actual.getLeft());

        // paso 2 ; recorre por rama der
        getPrintPostOrder(actual.getRight());

        System.out.println(actual.getValue() + " ");
    }

    public void preorder() {
        getPrintPreOrder(this.root);
    }

    private void getPrintPreOrder(Treenode actual) {
        if (actual == null) {
            System.out.println("-");
            return;
        }

        // paso 1 : recorre por rama izq
        getPrintPostOrder(actual.getLeft());

        // paso 2 ; recorre por rama der
        getPrintPostOrder(actual.getRight());

        System.out.println(actual.getValue() + " ");
    }

    public void inorder() {
        getInOrder(this.root);
    }

    private void getInOrder(Treenode actual) {
        if (actual == null) {
            return;
        }
        getInOrder(actual.getLeft());
        System.out.println(actual.getValue() + " ");
        getInOrder(actual.getRight());
    }

    public List<Integer> getLongestBranch() {
        List<Integer> branches = new ArrayList<>();
        if (root == null) {
            return branches;
        }
        return getLongestBranch(this.root);
    }

    private List<Integer> getLongestBranch(Treenode actual) {
        // Caso base: nodo hoja (sin hijos)
        if (actual.getLeft() == null && actual.getRight() == null) {
            List<Integer> leaf = new ArrayList<>();
            leaf.add(actual.getValue());
            return leaf;
        }

        List<Integer> leftBranch = new ArrayList<>();
        List<Integer> rightBranch = new ArrayList<>();

        // Explorar subárbol izquierdo si existe
        if (actual.getLeft() != null) {
            leftBranch = getLongestBranch(actual.getLeft());//--> recursividad
        }

        // Explorar subárbol derecho si existe
        if (actual.getRight() != null) {
            rightBranch = getLongestBranch(actual.getRight());//--> recursividad
        }

        // Elegir la rama más larga
        List<Integer> longest;
        if (leftBranch.size() >= rightBranch.size()) {
            longest = leftBranch;
        } else {
            longest = rightBranch;
        }

        // Agregar el nodo actual al INICIO de la rama
        longest.add(0, actual.getValue());
        return longest;
    }

    public List<Integer> getFrontera() {
        List<Integer> frontera = new ArrayList<>();
        if (root == null) {
            return frontera;
        }
        return getFront(this.root, frontera);
    }

    private List<Integer> getFront(Treenode actual, List<Integer> frontera) {
        // si es tiene hojas
        if (actual.getLeft() == null && actual.getRight() == null) {
            frontera.add(actual.getValue());

        }//por izq
        if (actual.getLeft() != null) {
            getFront(actual.getLeft(), frontera);
            // por derecha
        } else if (actual.getRight() != null) {
            getFront(actual.getRight(), frontera);
        }
        return frontera;
    }

    public Integer getMaxElem() {
        if (root == null) return null;
        return getMaxElem(root);
    }

    private Integer getMaxElem(Treenode actual) {
        // caso base: no hay mas derecha, este es el maximo
        if (actual.getRight() == null) {
            return actual.getValue();
        }
        // sigo yendo a la derecha
        return getMaxElem(actual.getRight());
    }

    public List<Integer> getElemAtLevel(int nivel) {
        List<Integer> resultado = new ArrayList<>();
        if (root == null) return resultado;
        getElemAtLevel(root, nivel, resultado);
        return resultado;
    }

    private void getElemAtLevel(Treenode actual, int nivel, List<Integer> resultado) {

        // caso base: llegue al nivel buscado
        if (nivel == 0) {
            resultado.add(actual.getValue());
            return;
        }

        // bajo por izquierda restando 1 al nivel
        if (actual.getLeft() != null) {
            getElemAtLevel(actual.getLeft(), nivel - 1, resultado);
        }

        // bajo por derecha restando 1 al nivel
        if (actual.getRight() != null) {
            getElemAtLevel(actual.getRight(), nivel - 1, resultado);
        }
    }

    // suma de todos los nodos deel arbol
    public int sumaNodos() {
        if (root == null) {
            return 0;
        }
        return getsumaArbol(this.root);
    }

    private int getsumaArbol(Treenode actual) {
        // caso base: nodo hoja, devuelvo su valor
        if (actual.getLeft() == null && actual.getRight() == null) {
            return actual.getValue();
        }

        int sumaIzq = 0;
        int sumaDer = 0;

        // sumo todo lo de la izquierda
        if (actual.getLeft() != null) {
            sumaIzq += getsumaArbol(actual.getLeft());
        }

        // sumo todo lo de la derecha
        if (actual.getRight() != null) {
            sumaDer += getsumaArbol(actual.getRight());
        }

        // devuelvo mi valor + suma izquierda + suma derecha
        return actual.getValue() + sumaIzq + sumaDer;
    }

    // Dado un árbol binario de búsqueda que almacena números
    //enteros y un valor de entrada K, implementar un algoritmo
    //que permita obtener un listado con los valores de todas las
    //hojas cuyo valor supere K. Por ejemplo, para el árbol de la
    //derecha, con un valor K = 8, el resultado debería ser [9, 11]

    public  List<Integer> getListMayorValor(int K) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
       return  list = getListMayorValor(this.root, K, list);
    }

    private List<Integer> getListMayorValor(Treenode actual, int K, List<Integer> list) {

        // caso base: es hoja
        if (esHoja(actual)) {
            // si el valor supera K la agrego
            if (actual.getValue() > K) {
                list.add(actual.getValue());
            }
        }
    if (actual.getValue()<=K) {
        // bajo por derecha
            if (actual.getRight() != null){
                getListMayorValor(actual.getRight(), K, list);
            }
        }else {
            if (actual.getLeft() != null) {
                getListMayorValor(actual.getLeft(), K, list);
            }
            if (actual.getRight() != null)
                getListMayorValor(actual.getRight(), K, list);

        }
    return list;
    }
//Se posee un árbol binario (no de búsqueda), donde los nodos internos están vacíos, mientras
//que las hojas tienen valores enteros. Se debe implementar un método que recorra el árbol y
//coloque valores en los nodos vacíos (los nodos internos). El valor de cada nodo interno debe ser
//igual al valor de su hijo derecho, menos el valor de su hijo izquierdo. En caso de que el nodo
//tenga un solo hijo, el valor del hijo faltante se reemplaza por un 0. Por ejemplo, tomando como
//entrada el árbol de la izquierda, el árbol resultante debería quedar con los mismos valores que el
//de la derecha


    public void CompletaArbol(){
        if (this.root != null) {
            CompletaArbol(this.root);
        }
    }

   private void CompletaArbol(Treenode actual) {
        int left =0 ,right = 0;
            // si es hoja
        if (actual.getLeft() == null && actual.getRight() == null) {
            return;
      }
        // completa los hijos
        if (actual.getLeft() != null) {
            CompletaArbol(actual.getLeft());
            left = actual.getLeft().getValue();

        }
        if (actual.getRight() != null) {
            CompletaArbol(actual.getRight());
            right = actual.getRight().getValue();

        }
       actual.setValue(right-left );
   }

   public ArrayList<String> PalabraVocal(int N) {
       ArrayList<String> lista = new ArrayList<>();
       if (this.root == null) {
           return lista;
       }
       getListPalabras(this.root, "", 0, N, lista);
       return lista;
   }
private ArrayList<String> getListPalabras(Treenode actual, String palabra,
                             int cantVocales, int N,
                             ArrayList<String> lista) {

    if (actual == null) return lista;

    // agregar letra actual a la palabra
    char letra = (char) actual.getValue().intValue();

    if (IsVocal(letra) && cantVocales<N) {
        cantVocales++;
        palabra += letra;

    } else if (!IsVocal(letra)) {
        palabra += letra;
    }

        // si es hoja → evaluar
        if (actual.getLeft() == null && actual.getRight() == null) {
            if (cantVocales == N) {
                lista.add(palabra);
            }
        }else if (actual.getLeft() != null) {
            // recorre por izquierda
            getListPalabras(actual.getLeft(), palabra, cantVocales, N, lista);
        }else if (actual.getRight() != null) {
            // recorre por derecha
            getListPalabras(actual.getRight(), palabra, cantVocales, N, lista);
        }
    return lista;
}
   private boolean IsVocal(char c) {
        return c =='A'|| c =='E'|| c =='I'
                || c =='O'|| c =='U';
   }


}

