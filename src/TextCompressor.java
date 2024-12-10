/******************************************************************************
 *  Compilation:  javac TextCompressor.java
 *  Execution:    java TextCompressor - < input.txt   (compress)
 *  Execution:    java TextCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   abra.txt
 *                jabberwocky.txt
 *                shakespeare.txt
 *                virus.txt
 *
 *  % java DumpBinary 0 < abra.txt
 *  136 bits
 *
 *  % java TextCompressor - < abra.txt | java DumpBinary 0
 *  104 bits    (when using 8-bit codes)
 *
 *  % java DumpBinary 0 < alice.txt
 *  1104064 bits
 *  % java TextCompressor - < alice.txt | java DumpBinary 0
 *  480760 bits
 *  = 43.54% compression ratio!
 ******************************************************************************/

/**
 *  The {@code TextCompressor} class provides static methods for compressing
 *  and expanding natural language through textfile input.
 *
 *  @author Zach Blick, Caden Chock
 */
// 10 Bit Code
public class TextCompressor {
    // Largest Num represent with 10 bits
    final static int bitLimit = 1024;
    final static int EOF = 256;
    private static void compress() {
        int currentCode = 257;
        TST codes = new TST();
        //Add Extended ASCII to TST
        for(int i = 0; i < 255; i++){
            codes.insert(""+(char) i, i);
        }
        String s = BinaryStdIn.readString();
        int n = s.length();
        for(int i = 0; i < n; i++){
            // Check Largest Prefix
            String pre = codes.getLongestPrefix(s, i);
            i = i + pre.length();
            int code = codes.lookup(pre);
            BinaryStdOut.write(code, 10);
            // Add Prefix of Next Char if not last Char
            if(i < n-1 && currentCode < bitLimit){
                String check = pre + s.charAt(i);
                codes.insert(check, currentCode);
                currentCode++;
            }
        }
        BinaryStdOut.write(EOF,10);
    }



    private static void expand() {
        // TODO: Complete the expand() method

    }


    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
