package pl.edu.pw.ee.aisd2023zlab5.cmp;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2023zlab5.model.Node;

import static org.assertj.core.api.Assertions.assertThat;

class HeapPQTest {

    @Test
    public void should_insertNode(){
        // given
        HeapPQ heapPQ = new HeapPQ();
        Node node1 = new Node('a', 5, null, null);
        heapPQ.insert(node1);

        // when
        Node[] snapshot = heapPQ.getQueueSnapshot();

        // then
        assertThat(snapshot[0]).isEqualTo(node1);
        assertThat(heapPQ.getQueueLen()).isEqualTo(1);

    }

    @Test
    public void should_sortTwoElements(){
        // given
        HeapPQ heapPQ = new HeapPQ();
        Node node1 = new Node('a', 5, null, null);
        Node node2 = new Node('b', 1, null, null);
        heapPQ.insert(node1);
        heapPQ.insert(node2);

        // when
        Node[] snapshot = heapPQ.getQueueSnapshot();

        // then
        assertThat(snapshot[0]).isEqualTo(node2);
        assertThat(snapshot[1]).isEqualTo(node1);
    }

    @Test
    public void should_notSortTwoElements(){
        // given
        HeapPQ heapPQ = new HeapPQ();
        Node node1 = new Node('a', 1, null, null);
        Node node2 = new Node('b', 5, null, null);
        heapPQ.insert(node1);
        heapPQ.insert(node2);

        // when
        Node[] snapshot = heapPQ.getQueueSnapshot();

        // then
        assertThat(snapshot[0]).isEqualTo(node1);
        assertThat(snapshot[1]).isEqualTo(node2);
    }
}