package pl.edu.pw.ee.aisd2023zlab5.dcp;

import pl.edu.pw.ee.aisd2023zlab5.model.Node;

public class DictionaryGenerator {
    private long code;
    private int codeLen;
    private LinearDict dict;
    public LinearDict generate(Node node){
        code = 0;
        dict = new LinearDict();

        // if tree has 1 node, add it. Otherwise traverse() would add
        // a code with codeLen == 0
        if(node.isLeaf()){
            dict.add(new CharCode(0L, 1, (char) node.getCharacter()));
            return dict;
        }

        traverse(node);
        return dict;
    }

    private void traverse(Node node){
        if(node.isLeaf()){
            dict.add(new CharCode(code, codeLen, (char) node.getCharacter()));
            return;
        }

        codeLen++;
        code <<= 1;
        traverse(node.getLeft());

        code++;
        traverse(node.getRight());

        code >>= 1;
        codeLen--;
    }
}