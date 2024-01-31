package pl.edu.pw.ee.aisd2023zlab5.cmp;

import pl.edu.pw.ee.aisd2023zlab5.model.Node;
import pl.edu.pw.ee.aisd2023zlab5.model.Root;

public class TreeGenerator {
    private final HeapPQ heapPQ;

    public TreeGenerator() {
        heapPQ = new HeapPQ();
    }

    public Root generateTree(int[] charFreq){
        addCharactersToDict(charFreq);
        int numOfLeaves = heapPQ.getQueueLen();

        int numOfNodes = numOfLeaves;
        while(heapPQ.getQueueLen() > 1){
            merge2smallestNodes();
            numOfNodes++;
        }
        Node firstNode = heapPQ.getFirstNode();
        return new Root(firstNode, numOfLeaves, numOfNodes);
    }

    private void merge2smallestNodes() {
        Node smallestFirst = heapPQ.extractSmallest();
        Node smallestSec = heapPQ.extractSmallest();
        Node newNode = new Node(-1, smallestFirst.getSum() + smallestSec.getSum(), smallestFirst, smallestSec);
        heapPQ.insert(newNode);
    }

    private void addCharactersToDict(int[] charFreq) {
        for (int i = 0; i < charFreq.length; i++)
            if (charFreq[i] > 0)
                heapPQ.insert(new Node(i, charFreq[i], null, null));
    }
}
