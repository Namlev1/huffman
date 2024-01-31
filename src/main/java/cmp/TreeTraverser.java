package pl.edu.pw.ee.aisd2023zlab5.cmp;

import pl.edu.pw.ee.aisd2023zlab5.model.Code;
import pl.edu.pw.ee.aisd2023zlab5.model.Node;
import pl.edu.pw.ee.aisd2023zlab5.model.Root;

// This class traverse through Huffman tree to generate
// both dictionary and tree structure (ready to write
// to the file) at the same time.
public class TreeTraverser {
    private final Root root;
    // for the dictionary:
    private int len;
    private long code;
    private final Dictionary dict;
    // for the tree structure:
    private int treeStructPtr;
    private final int[] treeStruct;
    // tree structure:
    // every even index is number of '0' bits to write
    // every odd index is a character to write
    // e.g. 00001 - N is saved as:
    // [4, 'N', ...]

    public TreeTraverser(Root root) {
        this.root = root;
        dict = new Dictionary();
        code = 0;
        len = 0;
        treeStruct = new int[root.getNumOfLeaves() * 2]; // each node needs 2 places in int[]
        treeStructPtr = 0;
    }

    public void traverse(){
        if(root.isLeaf()){
            // otherwise traverse(root) returns codeLen = 0
            dict.add(root.getCharacter(), new Code(0b0L, 1));
            treeStruct[0] = 0;
            treeStruct[1] = root.getCharacter();
            return;
        }
        traverse(root);
    }

    private void traverse(Node node){
        if(node.isLeaf()){
            addToDictAndTreeStruct(node);
            return;
        }

        treeStruct[treeStructPtr]++;
        len++;

        code <<= 1;
        // left one ends with '0'
        traverse(node.getLeft());

        // right one ends with '1'
        code++;
        traverse(node.getRight());

        code >>= 1;
        len--;
    }

    private void addToDictAndTreeStruct(Node node) {
        dict.add(node.getCharacter(), new Code(code, len));
        treeStruct[++treeStructPtr] = node.getCharacter();
        treeStructPtr++;
    }

    public int[] getTreeStruct() {
        return treeStruct;
    }

    public Dictionary getDict() {
        return dict;
    }
}
