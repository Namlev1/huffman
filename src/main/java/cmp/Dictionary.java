package cmp;

import model.Code;

public class Dictionary {
    private final Code[] codes;

    public Dictionary() {
        codes = new Code[128];
    }

    public void add(int c, Code code){
        codes[c] = code;
    }

    public Code get(int c){
        return codes[c];
    }
}
