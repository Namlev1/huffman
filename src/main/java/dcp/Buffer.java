package pl.edu.pw.ee.aisd2023zlab5.dcp;

import java.io.IOException;
import java.io.InputStream;

public class Buffer {
    private final InputStream is;
    private int bitsToRead;
    private long buffer;
    private int bytesRead;
    private final int freeBits;
    private final int bytesInFile;

    public Buffer(InputStream inputStream, int bytesInFile) throws IOException {
        is = inputStream;
        this.bytesInFile = bytesInFile;
        bitsToRead = 0;
        buffer = 0;
        bytesRead = 0;
        freeBits = readFreeBits();
    }

    public int readFreeBits() throws IOException {
        readNextByte();
        bitsToRead -= 3; // freeBits info takes 3 bits
        int res = (int) (buffer & 0b1110_0000) >> 5;
        moveBuffer(3);
        return res;
    }

    public int readNextBit() throws IOException {
        if(!canRead())
            return -1;
        if(bitsToRead <= 0)
            readNextByte();
        int res = (int) ((buffer >> 7) & 1);
        bitsToRead--;
        moveBuffer(1);
        return res;
    }

    public int readBits(int n) throws IOException {
        if(!canRead())
            return -1;
        while(n > bitsToRead)
            readNextByte();

        int shift;
        if(bitsToRead > 8)
            shift = bitsToRead - n;
        else
            shift = 8 - n;

        int res = (int) buffer >> shift;
        bitsToRead -= n;
        moveBuffer(8 - shift);
        return res;
    }

    private void readNextByte() throws IOException {
        buffer >>= 8 - bitsToRead;
        bytesRead++;
        buffer <<= 8;
        buffer += is.read();
        bitsToRead += 8;
    }

    private void moveBuffer(int n){
        buffer <<= n;
        buffer &= 0b1111_1111;
    }

    public boolean canRead() {
        return !(bytesRead == bytesInFile && bitsToRead == freeBits);
    }

}