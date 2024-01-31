package pl.edu.pw.ee.aisd2023zlab5.cmp;

import pl.edu.pw.ee.aisd2023zlab5.model.Code;

import java.io.*;

public class Encoder {
    private final Dictionary dict;
    private final int[] treeStruct;
    private final int freeBits;
    private final BufferedReader reader;
    private final FileOutputStream outputStream;
    private int nOfBitsInBuffer;
    private long buffer;

    public Encoder(Dictionary dict, int[] treeStruct, int freeBits, BufferedReader reader, FileOutputStream outputStream) {
        this.dict = dict;
        this.treeStruct = treeStruct;
        this.freeBits = freeBits;
        this.reader = reader;
        this.outputStream = outputStream;
    }

    public void encode() throws IOException {
        buffer = freeBits;
        nOfBitsInBuffer = 3; // 3 bits for value <0, 7>
        encodeTree();
        encodeContent();
        writeLastByteIfNeeded();
    }

    private void encodeTree() throws IOException {
        for(int i = 0; i < treeStruct.length; i += 2){
            int nOfZeros = treeStruct[i];
            writeBits(1, nOfZeros+1); // for example [2, 'A'] is 001 <7bit ascii code for 'A']
            int character = treeStruct[i+1];
            writeBits(character, 7); // writes corresponding 7bit ascii code
        }
    }

    private void encodeContent() throws IOException {
        int c;
        while((c = reader.read()) != -1){
            Code code = dict.get(c);
            writeBits(code.getBitCode(), code.getCodeLen());
        }
    }

    private void writeLastByteIfNeeded() throws IOException {
        if(nOfBitsInBuffer != 0){
            buffer <<= 8 - nOfBitsInBuffer;
            byte[] byteArr = longToByteArray(buffer, 1, 0);
            outputStream.write(byteArr);
        }
    }

    private void writeBits(long value, int len) throws IOException {
        buffer = buffer << len;
        buffer += value;
        nOfBitsInBuffer += len;
        if(nOfBitsInBuffer >= 8){
            int bitsLeft = nOfBitsInBuffer % 8;
            int byteNum = nOfBitsInBuffer / 8;
            byte[] bytesToWrite = longToByteArray(buffer, byteNum, bitsLeft);
            outputStream.write(bytesToWrite, 0, byteNum);
            int shift = (1 << bitsLeft) - 1;
            buffer &= shift;
            nOfBitsInBuffer = bitsLeft;
        }
    }

    private byte[] longToByteArray(long value, int nOfBytes, int bitsLeft) {
        byte[] byteArray = new byte[nOfBytes];
        value >>= bitsLeft;

        for (int i = nOfBytes-1; i >= 0; i--) {
            byteArray[i] = (byte) (value & 0xFF);
            value >>= 8;
        }

        return byteArray;
    }
}
