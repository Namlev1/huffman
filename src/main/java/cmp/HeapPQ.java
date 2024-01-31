package pl.edu.pw.ee.aisd2023zlab5.cmp;

import pl.edu.pw.ee.aisd2023zlab5.model.Node;

public class HeapPQ {
    private final Node[] queue;
    private int queueLen;

    public HeapPQ() {
        queue = new Node[128];
        queueLen = 0;
    }

    public void insert(Node node){
        queue[queueLen] = node;
        heapUp(queueLen);
        queueLen++;
    }

    private void heapUp(int i){
        while(i > 0){
            int parent = (i-1)/2;
            if(queue[parent].compareTo(queue[i]) < 0)
                return;
            else {
                swap(i, parent);
                i = parent;
            }
        }
    }

    private void headDown(){
        int i = 0;
        int c = 2*i + 1;
        while(c < queueLen){
            if(c+1 < queueLen && queue[c+1].compareTo(queue[c]) < 0)
                c++;
            if(queue[i].compareTo(queue[c]) <= 0)
                return;
            else{
                swap(i, c);
                i = c;
                c = 2*i + 1;
            }
        }
    }

    private void swap(int i, int j){
        Node tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }

    public Node extractSmallest(){
        Node root = queue[0];
        queueLen--;
        swap(0, queueLen);
        headDown();
        return root;
    }

    public int getQueueLen() {
        return queueLen;
    }

    public Node getFirstNode(){
        return queue[0];
    }

    // for tests
    public Node[] getQueueSnapshot() {
        Node[] snapshot = new Node[queueLen];
        System.arraycopy(queue, 0, snapshot, 0, queueLen);
        return snapshot;
    }
}
