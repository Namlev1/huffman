package pl.edu.pw.ee.aisd2023zlab5.dcp;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2023zlab5.model.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DictionaryGeneratorTest {
    
    @Test
    public void should_GenerateDir_WithFiveCodes() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/niemanie_tree.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 6);
        TreeReader treeReader = new TreeReader(buffer);
        Node root = treeReader.readTree();
        DictionaryGenerator generator = new DictionaryGenerator();

        // then
        LinearDict res = generator.generate(root);
        /*
            Tree structure:
                                -1
                -1                              -1
            N       I                   -1              E
                                    A       M
                                    
            Codes:
            N - 00
            I - 01
            A - 100
            M - 101
            E - 11
         */
        
        // then
        List<CharCode> row1 = res.getRow(2);
        List<CharCode> row2 = res.getRow(3);
        assertThat(row1.get(0).getBitCode()).isEqualTo(0b00L);
        assertThat(row1.get(0).getCharacter()).isEqualTo('N');
        assertThat(row1.get(1).getBitCode()).isEqualTo(0b01L);
        assertThat(row1.get(1).getCharacter()).isEqualTo('I');
        assertThat(row1.get(2).getBitCode()).isEqualTo(0b11L);
        assertThat(row1.get(2).getCharacter()).isEqualTo('E');
        assertThat(row2.get(0).getBitCode()).isEqualTo(0b100L);
        assertThat(row2.get(0).getCharacter()).isEqualTo('A');
        assertThat(row2.get(1).getBitCode()).isEqualTo(0b101L);
        assertThat(row2.get(1).getCharacter()).isEqualTo('M');
    }

    @Test
    public void should_GenerateDir_WithOneCode() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/text/dcp/tree_one_node.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 2);
        TreeReader treeReader = new TreeReader(buffer);
        Node root = treeReader.readTree();
        DictionaryGenerator generator = new DictionaryGenerator();

        // then
        LinearDict res = generator.generate(root);
        // Codes:
        // A - 0

        // then
        List<CharCode> row = res.getRow(1);
        assertThat(row.get(0).getCharacter()).isEqualTo('A');
    }
}