package pl.edu.pw.ee.aisd2023zlab5.dcp;

import pl.edu.pw.ee.aisd2023zlab5.model.Code;

public class CharCode extends Code {
    private final char character;

    public CharCode(long bitCode, int codeLen, char character) {
        super(bitCode, codeLen);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
