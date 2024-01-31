package pl.edu.pw.ee.aisd2023zlab5.dcp;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class BufferTest {

    @Test
    public void should_readFiveBits() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/random.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 2);

        // when
        int res = buffer.readBits(5);

        // then
        assertThat(res).isEqualTo(0b11000);

    }

    @Test
    public void should_readNineBits() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/random.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 2);

        // when
        int res = buffer.readBits(9);

        // then
        assertThat(res).isEqualTo(0b11000_1110);

    }

    @Test
    public void should_readTwo_AndThenThreeBits() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/random.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 2);

        // when
        int res1 = buffer.readBits(2);
        int res2 = buffer.readBits(3);

        // then
        assertThat(res1).isEqualTo(0b11);
        assertThat(res2).isEqualTo(0b000);

    }
}