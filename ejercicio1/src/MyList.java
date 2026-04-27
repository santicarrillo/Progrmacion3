import java.util.Comparator;
import java.util.Iterator;

public class MyList<T extends Comparable<T>> implements Iterable<T> {

        private Node<T> first;
        private int size;
        public Mylist() {
            this.first = null;
        }

        // lista tiene nodo insert
        public void insertFront(T info) {
            Node<T> tmp = new Node<T>(info,null);
            tmp.setNext(this.first);// agarra el primero
            this.first = tmp;
        }

        public T extractFront() {
            // TODO
            return null;
        }

        public boolean isEmpty() {
            // TODO
            return this.size == 0;
        }

        public T get(int index) {
            // TODO
            return null;
        }

        public int size() {
            // TODO
            return size;
        }

        @Override
        public String toString() {
            // TODO
            return "";
        }


        public void agregarOrdenado(T dato) {
            Node<T> nodo=new Node<>(dato,null);
            //lista vacia
            if (first==null){
                this.first=nodo;
                return;
            }
            // Caso 2: insertar al inicio
            if (dato.compareTo(first.getInfo()) < 0) {
                nodo.setNext(first);
                first = nodo;
                return;
            }
            // caso 3 recorre hasta encontrar la posicion
            Node<T> cursor = first;
            while (cursor.getNext() != null && dato.compareTo(cursor.getNext().getInfo()) > 0) {
                cursor = cursor.getNext();
            }

            // insertar en la posición encontrada
            nodo.setNext(cursor.getNext());
            cursor.setNext(nodo);
        }


    public int indexof(T info){
        Node<T> cursor= first;
        int i=0;
        while (cursor != null && cursor.getInfo()!=info){
            i++;
            cursor=cursor.getNext();
        }
        if (cursor==null){
            return -1;
        }
        return i;
    }
    @Override
    public Iterator<T> iterator() {
        return new MyIterador<>(this.first);
    }

}
