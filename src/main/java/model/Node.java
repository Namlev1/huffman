package pl.edu.pw.ee.aisd2023zlab5.model;

public class Node implements Comparable<Node>{
    private final int character;
    private final int sum;
    private final Node left;
    private final Node right;

    public Node(int character, int sum, Node left, Node right) {
        this.character = character;
        this.sum = sum;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf(){
        return character != -1;
    }

    public int getCharacter() {
        return character;
    }

    public int getSum() {
        return sum;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public int compareTo(Node o) {
       return sum - o.getSum();
    }
}
