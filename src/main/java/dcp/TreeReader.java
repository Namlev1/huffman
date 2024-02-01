package dcp;

import model.Node;

import java.io.IOException;

public class TreeReader {
    private final Buffer buffer;

    public TreeReader(Buffer buffer) {
        this.buffer = buffer;
    }

    public Node readTree() throws IOException {
        if (buffer.readNextBit() == 0) {
            Node left = readTree();
            Node right = readTree();
            return new Node(-1, -1, left, right);
            // -1 is a placeholder, bc Node is also used in compressor
        } else {
            return new Node(readChar(), -1, null, null);
        }
    }

    private int readChar() throws IOException {
        return buffer.readBits(7);
    }
}
