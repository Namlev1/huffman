package cmp;

import org.junit.jupiter.api.Test;
import model.Code;
import model.Root;

import static org.assertj.core.api.Assertions.assertThat;

class TreeTraverserTest {

    @Test
    public void should_generateDict_whenTreeHas3Nodes(){
        // given
        int[] input = new int[128];
        input['a'] = 5;
        input['b'] = 10;
        TreeGenerator generator = new TreeGenerator();
        /*
            Tree structure:
                    -1
                 a      b
         */
        Root root = generator.generateTree(input);
        TreeTraverser traverser = new TreeTraverser(root);

        // when
        traverser.traverse();

        // then
        Dictionary result = traverser.getDict();
        Code codeA = result.get('a');
        Code codeB = result.get('b');
        assertThat(codeA.getCodeLen()).isEqualTo(1);
        assertThat(codeA.getBitCode()).isEqualTo(0b0);
        assertThat(codeB.getCodeLen()).isEqualTo(1);
        assertThat(codeB.getBitCode()).isEqualTo(0b1);
    }

    @Test
    public void should_generateDict_whenTreeHas5Nodes(){
        // given
        int[] input = new int[128];
        input['a'] = 5;
        input['b'] = 2;
        input['c'] = 4;
        TreeGenerator generator = new TreeGenerator();
        Root root = generator.generateTree(input);
        TreeTraverser traverser = new TreeTraverser(root);
        /*
            Tree structure:
                        -1
                  a            -1
                            b       c
         */

        // when
        traverser.traverse();

        // then
        Dictionary result = traverser.getDict();
        Code codeA = result.get('a');
        Code codeB = result.get('b');
        Code codeC = result.get('c');
        assertThat(codeA.getCodeLen()).isEqualTo(1);
        assertThat(codeA.getBitCode()).isEqualTo(0b0);
        assertThat(codeB.getCodeLen()).isEqualTo(2);
        assertThat(codeB.getBitCode()).isEqualTo(0b10);
        assertThat(codeC.getCodeLen()).isEqualTo(2);
        assertThat(codeC.getBitCode()).isEqualTo(0b11);
    }
    @Test
    public void should_generateDict_whenTreeHas7Nodes(){
        // given
        int[] input = new int[128];
        input['a'] = 10;
        input['b'] = 11;
        input['c'] = 12;
        input['d'] = 13;
        TreeGenerator generator = new TreeGenerator();
        Root root = generator.generateTree(input);
        TreeTraverser traverser = new TreeTraverser(root);
        /*
            Tree structure:
                        -1
                  -1            -1
               a     b        c    d
         */

        // when
        traverser.traverse();

        // then
        Dictionary result = traverser.getDict();
        Code codeA = result.get('a');
        Code codeB = result.get('b');
        Code codeC = result.get('c');
        Code codeD = result.get('d');
        assertThat(codeA.getCodeLen()).isEqualTo(2);
        assertThat(codeA.getBitCode()).isEqualTo(0b00);
        assertThat(codeB.getCodeLen()).isEqualTo(2);
        assertThat(codeB.getBitCode()).isEqualTo(0b01);
        assertThat(codeC.getCodeLen()).isEqualTo(2);
        assertThat(codeC.getBitCode()).isEqualTo(0b10);
        assertThat(codeD.getCodeLen()).isEqualTo(2);
        assertThat(codeD.getBitCode()).isEqualTo(0b11);
    }

    @Test
    public void should_generateTreeStruct_IfNumberOfLeavesIsThree(){
        // given
        int[] input = new int[128];
        input['N'] = 3;
        input['I'] = 100;
        input['E'] = 6;
        TreeGenerator generator = new TreeGenerator();
        Root root = generator.generateTree(input);
        TreeTraverser traverser = new TreeTraverser(root);
        /* tree:

                    -1
                -1      I
            N       E

        */

        // when
        traverser.traverse();

        // then
        int[] res = traverser.getTreeStruct();
        int[] expected = {2, 'N', 0, 'E', 0, 'I'};
        assertThat(res).isEqualTo(expected);
    }

    @Test
    public void should_generateTreeStruct_IfNumberOfLeavesIsFive(){
        // given
        int[] input = new int[128];
        input['A'] = 3;
        input['B'] = 100;
        input['C'] = 6;
        input['D'] = 30;
        input['E'] = 99;
        TreeGenerator generator = new TreeGenerator();
        Root root = generator.generateTree(input);
        TreeTraverser traverser = new TreeTraverser(root);
        /* tree structure:
                            -1
                B                           -1
                                    -1              E
                                -1     D
                              A   C
         */

        // when
        traverser.traverse();

        // then
        int[] res = traverser.getTreeStruct();
        int[] expected = {1, 'B', 3, 'A', 0, 'C', 0, 'D', 0, 'E'};
        assertThat(res).isEqualTo(expected);
    }
}