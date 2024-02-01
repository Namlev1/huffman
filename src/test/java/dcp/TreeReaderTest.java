package dcp;

import org.junit.jupiter.api.Test;
import model.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class TreeReaderTest {

    @Test
    public void should_ReadTree_WithFiveLeaves() throws IOException {
        // given
        String path = "src/test/java/text/dcp/niemanie_tree.cmp";
        InputStream is = new FileInputStream(path);
        Buffer buffer = new Buffer(is, 6);
        TreeReader treeReader = new TreeReader(buffer);

        // when
        Node root = treeReader.readTree();

        // then
        /*
            Tree structure:
                                -1
                -1                              -1
            N       I                   -1              E
                                    A       M
         */
        assertThat(root.getLeft().getLeft().getCharacter()).isEqualTo('N');
        assertThat(root.getLeft().getRight().getCharacter()).isEqualTo('I');
        assertThat(root.getRight().getLeft().getLeft().getCharacter()).isEqualTo('A');
        assertThat(root.getRight().getLeft().getRight().getCharacter()).isEqualTo('M');
        assertThat(root.getRight().getRight().getCharacter()).isEqualTo('E');
    }

}