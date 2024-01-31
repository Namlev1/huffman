package pl.edu.pw.ee.aisd2023zlab5.dcp;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class DecoderTest {

    @Test
    public void should_DecodeFile_WithTwoCharacters() throws IOException {
        // given
        String inPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/aabbb.cmp";
        String outPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/aabbb.txt";
        String refPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/aabbb-ref.txt";
        Decoder decoder = new Decoder(inPath, outPath);

        // when
        decoder.decode();

        // then
        assertThat(areFilesEqual(outPath, refPath)).isTrue();
    }

    @Test
    public void should_DecodeFile_WithTwoLinesOfText() throws IOException {
        // given
        String inPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/niemanie_2lines.cmp";
        String outPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/niemanie_2lines.txt";
        String refPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/niemanie_2lines-r.txt";
        Decoder decoder = new Decoder(inPath, outPath);

        // when
        decoder.decode();

        // then
        assertThat(areFilesEqual(outPath, refPath)).isTrue();
    }

    private static boolean areFilesEqual(String path1, String path2) throws IOException {
        BufferedReader b1 = new BufferedReader(new FileReader(path1));
        BufferedReader b2 = new BufferedReader(new FileReader(path2));
        int c1 = 0;
        int c2 = 0;
        while(c1 != -1 && c2 != -1){
            c1 = b1.read();
            c2 = b2.read();
            if(c1 != c2)
                return false;
        }
        return true;
    }
}