public class Treenode {
    private Integer value;
    private Treenode left;
    private Treenode right;

    public Treenode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }


    public Treenode getLeft() {
        return left;
    }

    public void setLeft(Treenode left) {
        this.left = left;
    }

    public Treenode getRight() {
        return right;
    }

    public void setRight(Treenode right) {
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

}
