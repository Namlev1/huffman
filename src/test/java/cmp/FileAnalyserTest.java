package cmp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class FileAnalyserTest {

    @Test
    public void should_ThrowException_WhenFileNotExists(){
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            fileAnalyser.analyse("non_existing.txt");
        });

        // then
        String message = "File not found";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenFileIsEmpty(){
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            fileAnalyser.analyse("src/test/java/text/cmp/empty.txt");
        });

        // then
        String message = "File is empty";
        assertThat(exceptionCaught)
                .isInstanceOf(IOException.class)
                .hasMessage(message);
    }

    @Test
    public void should_NotThrowException_WhenReadingAllAsciiCharacters() {
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        assertDoesNotThrow(() -> {
            fileAnalyser.analyse("src/test/java/text/cmp/all_ascii.txt");
        });

    }

    @Test
    public void should_OpenFile_IfExists(){
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        assertDoesNotThrow(() -> {
            fileAnalyser.analyse("src/test/java/text/cmp/exist.txt");
        });
    }

    @Test
    public void should_ReadLetters() throws IOException {
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        fileAnalyser.analyse("src/test/java/text/cmp/aabba.txt");

        // then
        int[] result = fileAnalyser.getLetterCount();
        assertThat(result['a']).isEqualTo(3);
        assertThat(result['b']).isEqualTo(2);
    }

    @Test
    public void should_ReadLetters_IfContainsWhiteCharacters() throws IOException {
        // given
        FileAnalyser fileAnalyser = new FileAnalyser();

        // when
        fileAnalyser.analyse("src/test/java/text/cmp/newline.txt");

        // then
        int[] result = fileAnalyser.getLetterCount();
        assertThat(result['a']).isEqualTo(2);
        assertThat(result['\n']).isEqualTo(1);
        assertThat(result['b']).isEqualTo(2);
    }
}