package dcp;

import java.io.*;

public class HuffmanDecoder {

    public void decode(String inPath) throws IOException {
        if (!inPath.endsWith(".cmp"))
            throw new IOException("Wrong file extension");

        String outPath = createOutFilename(inPath);
        Decoder decoder = new Decoder(inPath, outPath);
        decoder.decode();

    }

    private String createOutFilename(String filename){
        return filename.replaceFirst(".cmp", ".txt");
    }
}
