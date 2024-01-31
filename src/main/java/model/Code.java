package pl.edu.pw.ee.aisd2023zlab5.model;

public class Code {
    private final long bitCode;
    private final int codeLen;

    public Code(long bitCode, int codeLen) {
        this.bitCode = bitCode;
        this.codeLen = codeLen;
    }

    public long getBitCode() {
        return bitCode;
    }

    public int getCodeLen() {
        return codeLen;
    }
}
