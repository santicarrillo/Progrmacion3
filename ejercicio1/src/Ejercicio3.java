public class Ejercicio3<T> {
private T first;
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
}
