package dcp;

import model.Node;

import java.io.*;

public class Decoder {
    private final Buffer buffer;
    private Node root;
    private LinearDict dict;
    private final BufferedWriter bw;

    public Decoder(String filePath, String outPath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        int bytesInFile = 0;
        while(inputStream.read() != -1)
            bytesInFile++;
        inputStream.close();

        inputStream = new FileInputStream(filePath);
        buffer = new Buffer(inputStream, bytesInFile);
        bw = new BufferedWriter(new FileWriter(outPath));
    }

    public void decode() throws IOException {
        readTree();
        createDict();
        decodeContent();
        bw.close();
    }

    private void readTree() throws IOException {
        TreeReader treeReader = new TreeReader(buffer);
        root = treeReader.readTree();
    }

    private void createDict(){
        DictionaryGenerator generator = new DictionaryGenerator();
        dict = generator.generate(root);
    }

    private void decodeContent() throws IOException {
        long currCode = 0L;
        int codeLen = 0;
        CharCode c;

        while(buffer.canRead()){
            // Search for a character, and if found, write it
            while((c = findChar(currCode, codeLen)) == null){
                currCode <<= 1;
                currCode += buffer.readNextBit();
                codeLen++;
            }
            bw.write(c.getCharacter());
            currCode = 0;
            codeLen = 0;
        }
    }

    private CharCode findChar(long code, int codeLen){
        return dict.find(code, codeLen);
    }
}
