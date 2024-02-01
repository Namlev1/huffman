package dcp;

import java.util.ArrayList;
import java.util.List;

/*
    This structure represents decoded dictionary.
    Implementation:
    Dictionary has a list of List<Code> objects. Each List<Code>
    corresponds to list of codes with appropriate codeLen.
    Example:
    dict.get(0) returns List<Code> list of codes with codeLen = 1
    dict.get(5) returns List<Code> list of codes with codeLen = 6

    This is faster than regular sequence search, because in ssearch
    algorithm has to search through all the codes and perhaps conclude,
    that considered code is not present, and it needs to be lengthened.
    In this implementation, each searching of the code is limited
    to a corresponding row of dictionary.
 */
public class LinearDict {
    private final List<List<CharCode>> dict;

    public LinearDict() {
        dict = new ArrayList<>();
        for(int i = 0; i < 128; i++)
            dict.add(new ArrayList<>());
    }

    public void add(CharCode code){
        int codeLen = code.getCodeLen();
        List<CharCode> row = dict.get(codeLen-1);
        row.add(code);
    }

    public CharCode find(long code, int codeLen){
        if(codeLen < 1)
            return null;

        List<CharCode> row = getRow(codeLen);
        if(row.isEmpty())
            return null;

        for(CharCode c : row){
            if(c.getBitCode() == code)
                return c;
        }
        return null;
    }
    
    public List<CharCode> getRow(int codeLen){
        return dict.get(codeLen - 1);
    }
}
