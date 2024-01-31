package pl.edu.pw.ee.aisd2023zlab5.cmp;

import java.io.*;

public class FileAnalyser {

    private final int[] letterCount;
    private BufferedReader br;

    public FileAnalyser() {
        letterCount = new int[128];
    }

    public FileAnalyser(BufferedReader br) {
        this.br = br;
        letterCount = new int[128];
    }

    public void analyse(String filePath) throws IOException {
        if(br == null) // condition for testing
            try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
                readFile(br);
            } catch(IOException e){
                if(e.getMessage().endsWith("(No such file or directory)"))
                    throw new IOException("File not found");
                else
                    throw e;
            }
        else
            readFile(this.br);
    }

    private void readFile(BufferedReader br) throws IOException {
        int c = br.read();
        // if file is empty
        if(c == -1)
            throw new IOException("File is empty");
        // if non-ascii character
        if(c > 127)
            throw new IOException("File contains non-ascii characters");
        letterCount[c]++;

        while((c = br.read()) != -1) {
            if (c > 127)
                throw new IOException("File contains non-ascii characters");
            letterCount[c]++;
        }
    }

    public int[] getLetterCount() {
        return letterCount;
    }
}
