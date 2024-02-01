package cmp;

import org.junit.jupiter.api.Test;
import model.Root;

import static org.assertj.core.api.Assertions.assertThat;

class TreeGeneratorTest {

    @Test
    public void should_GenerateTree_WithOneElement(){
        // given
        int[] input = new int[128];
        input[5] = 3;
        TreeGenerator treeGenerator = new TreeGenerator();

        // when
        Root root = treeGenerator.generateTree(input);

        // then
        assertThat(root.getCharacter()).isEqualTo(5);
        assertThat(root.getNumOfLeaves()).isEqualTo(1);
        assertThat(root.getNumOfNodes()).isEqualTo(1);
    }

    @Test
    public void should_GenerateTree_WithTwoElements(){
        // given
        int[] input = new int[128];
        input['A'] = 3;
        input['B'] = 6;
        TreeGenerator treeGenerator = new TreeGenerator();

        // when
        Root root = treeGenerator.generateTree(input);

        // then
        /*  tree structure:
                -1
            A       B
         */

        assertThat(root.getSum()).isEqualTo(9);
        assertThat(root.getNumOfLeaves()).isEqualTo(2);
        assertThat(root.getNumOfNodes()).isEqualTo(3);
        assertThat(root.getLeft().getCharacter()).isEqualTo('A');
        assertThat(root.getRight().getCharacter()).isEqualTo('B');
    }

    @Test
    public void should_GenerateTree_WithThreeElements(){
        // given
        int[] input = new int[128];
        input['A'] = 3;
        input['B'] = 100;
        input['C'] = 6;
        TreeGenerator treeGenerator = new TreeGenerator();

        // when
        Root root = treeGenerator.generateTree(input);

        // then
        /* tree structure:
                        -1
                -1              B
             A      C
         */

        assertThat(root.getSum()).isEqualTo(109);
        assertThat(root.getNumOfLeaves()).isEqualTo(3);
        assertThat(root.getNumOfNodes()).isEqualTo(5);
        assertThat(root.getLeft().getLeft().getCharacter()).isEqualTo('A');
        assertThat(root.getLeft().getRight().getCharacter()).isEqualTo('C');
        assertThat(root.getRight().getCharacter()).isEqualTo('B');
    }

    @Test
    public void should_GenerateTree_WithFiveElements(){
        // given
        int[] input = new int[128];
        input['A'] = 3;
        input['B'] = 100;
        input['C'] = 6;
        input['D'] = 30;
        input['E'] = 99;
        TreeGenerator treeGenerator = new TreeGenerator();

        // when
        Root root = treeGenerator.generateTree(input);

        // then
        /* tree structure:
                            -1
                    B                       -1
                                    -1              E
                           -1           D
                        A      C
         */

        assertThat(root.getSum()).isEqualTo(238);
        assertThat(root.getNumOfLeaves()).isEqualTo(5);
        assertThat(root.getNumOfNodes()).isEqualTo(9);
        assertThat(root.getLeft().getCharacter()).isEqualTo('B');
        assertThat(root.getRight().getRight().getCharacter()).isEqualTo('E');
        assertThat(root.getRight().getLeft().getRight().getCharacter()).isEqualTo('D');
        assertThat(root.getRight().getLeft().getLeft().getRight().getCharacter()).isEqualTo('C');
        assertThat(root.getRight().getLeft().getLeft().getLeft().getCharacter()).isEqualTo('A');
    }

    @Test
    public void should_GenerateTree_WhenFrequenciesAreTheSame(){
        // given
        int[] input = new int[128];
        input['A'] = 1;
        input['B'] = 1;
        input['C'] = 1;
        TreeGenerator treeGenerator = new TreeGenerator();

        // when
        Root root = treeGenerator.generateTree(input);

        // then
        assertThat(root.getSum()).isEqualTo(3);
        assertThat(root.getNumOfLeaves()).isEqualTo(3);
        assertThat(root.getNumOfNodes()).isEqualTo(5);
        assertThat(root.getLeft().getCharacter()).isEqualTo('A');
        assertThat(root.getRight().getLeft().getCharacter()).isEqualTo('C');
        assertThat(root.getRight().getRight().getCharacter()).isEqualTo('B');

    }
}