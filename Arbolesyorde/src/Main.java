import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear árbol de prueba

        BinarySearchTree arbol = new BinarySearchTree();

        // hojas
        Treenode hoja14 = new Treenode(14);
        Treenode hoja7 = new Treenode(7);
        Treenode hojaMenos5 = new Treenode(-5);
        Treenode hoja9 = new Treenode(9);
        Treenode hoja20 = new Treenode(20);

        // nodos internos vacios
        Treenode raiz = new Treenode(null);
        Treenode nodoIzq = new Treenode(null);
        Treenode nodoInterno = new Treenode(null);  // padre de 7 y -5
        Treenode nodoDer = new Treenode(null);
        Treenode nodoInterno2 = new Treenode(null); // padre de 9

        // armar subárbol izquierdo
        //      ( )
        //      / \
        //     14 ( )
        //        / \
        //       7  -5
        nodoInterno.setLeft(hoja7);
        nodoInterno.setRight(hojaMenos5);
        nodoIzq.setLeft(hoja14);
        nodoIzq.setRight(nodoInterno);

        // armar subárbol derecho
        //      ( )
        //      / \
        //    ( )  20
        //    /
        //   9
        nodoInterno2.setRight(hoja9);
        nodoDer.setLeft(nodoInterno2);
        nodoDer.setRight(hoja20);

        // armar raiz
        raiz.setLeft(nodoIzq);
        raiz.setRight(nodoDer);
        arbol.setRoot(raiz);

        // completar el arbol
        arbol.CompletaArbol();

        // verificar
        System.out.println("Raiz esperada 37, obtenida: " + arbol.getRoot().getValue());
        System.out.println("Hijo izq esperado -26, obtenido: " + arbol.getRoot().getLeft().getValue());
        System.out.println("Hijo der esperado 11, obtenido: " + arbol.getRoot().getRight().getValue());
        System.out.println("Nieto izq-der esperado -12, obtenido: " + arbol.getRoot().getLeft().getRight().getValue());

    }
    }


