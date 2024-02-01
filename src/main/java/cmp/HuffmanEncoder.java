package cmp;

import model.Root;

import java.io.*;

public class HuffmanEncoder {
    public void encodeFile(String filePath) throws IOException {
        if (!filePath.endsWith(".txt"))
            throw new IOException("Wrong file extension");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e){
            throw new IOException("File does not exist");
        }
        FileAnalyser fileAnalyser = new FileAnalyser(reader);
        fileAnalyser.analyse(filePath);
        int[] charFreq = fileAnalyser.getLetterCount();

        TreeGenerator treeGenerator = new TreeGenerator();
        Root root = treeGenerator.generateTree(charFreq);

        TreeTraverser traverser = new TreeTraverser(root);
        traverser.traverse();

        Dictionary dict = traverser.getDict();
        int[] treeStruct = traverser.getTreeStruct();
        int freeBits = countFreeBits(treeStruct, charFreq, dict);

        reader.close();
        reader = new BufferedReader(new FileReader(filePath));
        String outPath = createOutFilename(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(new File(outPath))) {
            Encoder encoder = new Encoder(dict, treeStruct, freeBits, reader, outputStream);
            encoder.encode();
        }
        reader.close();

    }

    private int countFreeBits(int[] treeStruct, int[] charFreq, Dictionary dict){
        long nOfBits = 3; // reserve 3 bits in the beginning of the file

        for(int i = 0; i < treeStruct.length; i += 2){
            nOfBits += treeStruct[i] + 1;   // contains treeStruct[i] '0' and one '1'
            nOfBits += 7;                   // each ASCII char is 7 bit
        }

        for(int i = 0; i < charFreq.length; i++){
            if(dict.get(i) != null)
                nOfBits += (long) charFreq[i] * dict.get(i).getCodeLen();
        }
        return (int) (8 - (nOfBits % 8));
    }

    private String createOutFilename(String filename){
        return filename.replaceFirst(".txt", ".cmp");
    }
}
