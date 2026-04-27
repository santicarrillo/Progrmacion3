import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Treenode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Treenode getRoot() {
        return root;
    }

    public void setRoot(Treenode root) {
        this.root = root;
    }

    public void printPostOrder() {
        getPrintPostOrder(this.root);
    }

    private void getPrintPostOrder(Treenode actual) {
        if (actual == null) {
            return; // condición base: no hay nodo
        }
        // recorrer izquierda
        getPrintPostOrder(actual.getLeft());
        // recorrer derecha
        getPrintPostOrder(actual.getRight());
        // visitar nodo
        System.out.println(actual.getValue() + " ");
    }
    public  void preorder(){
        getPrintPreOrder(this.root);
    }

    private void getPrintPreOrder(Treenode actual) {
        if (actual == null) {
            System.out.println("-");
            return;
        }

        // 1) visitar nodo
        System.out.println(actual.getValue() + " ");

        // 2) recorrer izquierda
        getPrintPreOrder(actual.getLeft());

        // 3) recorrer derecha
        getPrintPreOrder(actual.getRight());

    }

    public  void inorder(){
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
    public void CompletaArbol(){
        if (this.root != null) {
            CompletaArbol(this.root);
        }
    }

    private void CompletaArbol(Treenode actual) {
        int left =0 ,right = 0;

        if (actual.getLeft() == null && actual.getRight() == null) {
            return;
        }

        if (actual.getLeft() != null) {
            CompletaArbol(actual.getLeft());
            left = actual.getLeft().getValue();

        }
        if (actual.getRight() != null) {
            CompletaArbol(actual.getRight());
            right = actual.getRight().getValue();
        }

        actual.setValue(right-left);

    }




}
