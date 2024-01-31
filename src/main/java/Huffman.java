package pl.edu.pw.ee.aisd2023zlab5;

import pl.edu.pw.ee.aisd2023zlab5.cmp.HuffmanEncoder;
import pl.edu.pw.ee.aisd2023zlab5.dcp.HuffmanDecoder;

public class Huffman {
    public static void main(String[] args){
        if(args.length >  1 && args[1].equals("-h")){
            showHelp();
            return;
        }

        if(args.length < 1){
            System.err.println("Error: wrong call");
            showHelp();
            return;
        }

        if(args[0].equals("-c")){
            HuffmanEncoder encoder = new HuffmanEncoder();
            try {
                encoder.encodeFile(args[1]);
                System.out.println("File compressed successfully");
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }

        else if(args[0].equals("-d")){
            HuffmanDecoder decoder = new HuffmanDecoder();
            try{
                decoder.decode(args[1]);
                System.out.println("File decompressed successfully");
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }

        else
            showHelp();
    }

    private static void showHelp(){
        System.out.println("Usage:");
        System.out.println("java Huffman.java <option> <filename>");
        System.out.println("\nOptions:\n-c compress\n-d decompress\n-h help");
    }
}
