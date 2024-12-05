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
 *  @author Zach Blick, YOUR NAME HERE
 */
public class TextCompressor {
    final static char[] special = {'A','B','C','D','E', 'F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k',
            'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    // Special Codes
    final static int the = 53;
    final static int be = 54;
    final static int to = 55;
    final static int of = 56;
    final static int and = 57;
    final static int in = 58;
    final static int that = 59;
    final static int have = 60;
    final static int it = 61;
    final static int For = 62;
    // key to change from 6 bit to 8 bit
    final static int change = 63;
    private static void compress() {

        // TODO: Complete the compress() method
        String s = BinaryStdIn.readString();
        int n = s.length();
        BinaryStdOut.close();
        for (int i = 0; i < n; i++) {
            int special = getSpecial(s.charAt(i));
            if(special != -1){
                BinaryStdOut.write(special, 6);
            }
            else{
                BinaryStdOut.write(63,6);
                BinaryStdOut.write(s.charAt(i));
            }
        }
    }

    private static void expand() {

        // TODO: Complete the expand() method
        while(!BinaryStdIn.isEmpty()){
            int key = 6;
            int sp = BinaryStdIn.readInt(key);
            if(sp != 63){
                BinaryStdOut.write(special[sp]);
            }
            else{
                char c = BinaryStdIn.readChar();
                BinaryStdOut.write(c);
            }
        }
        BinaryStdOut.close();
    }

    public static int getSpecial(char c){
        for(int i = 0; i < special.length; i++){
           if(c == special[i]){
               return i;
           }
        }
        return -1;
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
