public class ejercicio5 <T>{
private Node<T> first;
    public MyList<T> resolver(MyList<T> lista1, MyList<T> lista2) {
        MyList<T> resultado = new MyList<>();
        MyIterador<T> i= (MyIterador<T>) lista1.iterator();
        while (i.hasNext()) {
            T dato = i.next();
            if (lista2.indexof(dato) != -1) {
                resultado.agregarOrdenado(dato);
            }
        }
        return resultado;
    }
// ejercicio6
public Mylist<T> resolver(MyList<T> lista1, MyList<T> lista2) {
    MyList<T> resultado = new MyList<>();
    MyIterador<T> i = (MyIterador<T>) lista1.ite();
    while (i.hasNext()) {
        T dato = i.next();
        if (lista2.indexOf(dato) == -1) {
            resultado.agregarOrdenado(dato);
        }
    }
    return resultado;
}

}