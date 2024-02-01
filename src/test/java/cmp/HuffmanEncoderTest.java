package cmp;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class HuffmanEncoderTest {

    @Test
    public void should_EncodeFile_WithOneCharacter_AndFullBytes() throws IOException {
        // given
        String origPath = "src/test/java/text/cmp/c.txt";
        String outPath = "src/test/java/text/cmp/c.cmp";
        String refPath = "src/test/java/text/cmp/c-ref.cmp";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        huffmanEncoder.encodeFile(origPath);

        // then
        assertThat(areFilesEqual(outPath, refPath)).isTrue();
    }

    @Test
    public void should_EncodeFile_WithOneCharacter_AndNotFullBytes() throws IOException {
        // given
        String origPath = "src/test/java/text/cmp/d.txt";
        String outPath = "src/test/java/text/cmp/d.cmp";
        String refPath = "src/test/java/text/cmp/d-ref.cmp";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        huffmanEncoder.encodeFile(origPath);

        // then
        assertThat(areFilesEqual(outPath, refPath)).isTrue();
    }

    @Test
    public void should_EncodeFile_With2Characters() throws IOException {
        // given
        String origPath = "src/test/java/text/cmp/aabbb.txt";
        String outPath = "src/test/java/text/cmp/aabbb.cmp";
        String refPath = "src/test/java/text/cmp/aabbb-ref.cmp";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        huffmanEncoder.encodeFile(origPath);

        // then
        assertThat(areFilesEqual(outPath, refPath)).isTrue();

    }

    @Test
    public void should_ThrowException_IfFileIsEmpty(){
        // given
        String origPath = "src/test/java/text/cmp/empty.txt";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            huffmanEncoder.encodeFile(origPath);
        });

        // then
        String message = "File is empty";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_IfFileDoesNotExist(){
        // given
        String origPath = "non_existing.txt";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            huffmanEncoder.encodeFile(origPath);
        });

        // then
        String message = "File does not exist";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);

    }

    @Test
    public void should_ThrowException_IfFileHasWrongExtension(){
        // given
        String origPath = "src/test/java/text/cmp/c-ref.cmp";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            huffmanEncoder.encodeFile(origPath);
        });

        // then
        String message = "Wrong file extension";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_IfFileIsNotAsciiOnly() throws IOException {
        // given
        String origPath = "src/test/java/text/cmp/aogonek.txt";
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            huffmanEncoder.encodeFile(origPath);
        });

        // then
        String message = "File contains non-ascii characters";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);
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