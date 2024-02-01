package cmp;

import org.junit.jupiter.api.Test;
import model.Code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;

class EncoderTest {

    @Test
    public void should_EncodeFile_With2FullBytes() throws IOException {
        // given
        String origPath = "src/test/java/text/encode/c.txt";
        String cmpPath = "src/test/java/text/encode/c.cmp";
        String refPath = "src/test/java/text/encode/c-ref.cmp";
        Code code = new Code(0b0, 1);
        Dictionary dict = new Dictionary();
        dict.add('c', code);
        int[] treeStruct = {0, 'c'};
        int freeBits = 0;
        BufferedReader br = new BufferedReader(new FileReader(origPath));
        FileOutputStream fos = new FileOutputStream(cmpPath);
        Encoder encoder = new Encoder(dict, treeStruct, freeBits, br, fos);

        /*
            Original:
            ccccc
            Codes:
            c - '0'
            Compressed:
            000          1 1100011        00000
            [free bits] [tree structure] [encoded content]
            00011100 01100000
         */

        // when
        encoder.encode();

        // then
        assertThat(areFilesEqual(cmpPath, refPath)).isTrue();
    }

    @Test
    public void should_EncodeFile_With2NotFullBytes() throws IOException {
        // given
        String origPath = "src/test/java/text/encode/d.txt";
        String cmpPath = "src/test/java/text/encode/d.cmp";
        String refPath = "src/test/java/text/encode/d-ref.cmp";
        Code code = new Code(0b0, 1);
        Dictionary dict = new Dictionary();
        dict.add('d', code);
        int[] treeStruct = {0, 'd'};
        int freeBits = 1;
        BufferedReader br = new BufferedReader(new FileReader(origPath));
        FileOutputStream fos = new FileOutputStream(cmpPath);
        Encoder encoder = new Encoder(dict, treeStruct, freeBits, br, fos);

        /*
            Original:
            dddd
            Codes:
            d - '0'
            Compressed:
            001          1 1100100        0000
            [free bits] [tree structure] [encoded content]
            00111100 10000000
         */

        // when
        encoder.encode();

        // then
        assertThat(areFilesEqual(cmpPath, refPath)).isTrue();

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