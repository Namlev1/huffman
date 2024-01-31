package pl.edu.pw.ee.aisd2023zlab5.model;

public class Root extends Node {
    private int numOfLeaves;
    private int numOfNodes;

    public Root(Node node, int numOfLeaves, int numOfNodes) {
        super(node.getCharacter(), node.getSum(), node.getLeft(), node.getRight());
        this.numOfLeaves = numOfLeaves;
        this.numOfNodes = numOfNodes;
    }

    public Root(int character, int sum, Node left, Node right) {
        super(character, sum, left, right);
        numOfLeaves = 0;
        numOfNodes = 0;
    }


    public int getNumOfLeaves() {
        return numOfLeaves;
    }

    public void setNumOfLeaves(int numOfLeaves) {
        this.numOfLeaves = numOfLeaves;
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

    public void setNumOfNodes(int numOfNodes) {
        this.numOfNodes = numOfNodes;
    }
}