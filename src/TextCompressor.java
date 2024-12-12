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
    final static int bitLimit = 4096;
    final static int EOF = 256;
    final static int bitSize = 12;

    private static void compress() {
        int currentCode = 257;
        TST codes = new TST();
        //Add Extended ASCII to TST
        for(int i = 0; i < 256; i++){
            codes.insert(""+(char) i, i);
        }
        String s = BinaryStdIn.readString();
        int length = s.length();
        for(int i = 0; i < length; i++){
            // Check Largest Prefix
            String pre = codes.getLongestPrefix(s, i);
            int code = codes.lookup(pre);
            BinaryStdOut.write(code, bitSize);
            i = i + pre.length()-1;
            // Add Prefix of Next Char if not last Char
            if(i < length-1 && currentCode < bitLimit){
                String check = pre + s.charAt(i+1);
                codes.insert(check, currentCode++);
            }
        }
        BinaryStdOut.write(EOF,bitSize);
        BinaryStdOut.close();
    }



    private static void expand() {
        // TODO: Complete the expand() method
        int currentIndex = 257;
        String[] codes = new String[bitLimit];
        // 257 = Extended ASCII + EOF
        for(int i = 0; i < currentIndex; i++){
            if(i == EOF){
                codes[i] = "EOF";
            }
            else{
                codes[i] = ""+(char) i;
            }
        }
        int num = BinaryStdIn.readInt(bitSize);
        while(num != EOF){
            String s = codes[num];
            int next = BinaryStdIn.readInt(bitSize);
            String nextS = codes[next];
            if(codes[next] == null){
                nextS = s + s.charAt(0);
            }
            if(next != EOF && currentIndex < bitLimit) {
                codes[currentIndex] = s + nextS.charAt(0);
                currentIndex++;
            }
            BinaryStdOut.write(s);
            num = next;
        }
        BinaryStdOut.close();
    }


    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
