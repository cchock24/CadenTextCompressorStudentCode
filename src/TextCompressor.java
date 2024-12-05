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
    // Alphabet Codes
    final static int A = 1;
    final static int B = 2;
    final static int C = 3;
    final static int D = 4;
    final static int E = 5;
    final static int F = 6;
    final static int G = 7;
    final static int H = 8;
    final static int I = 9;
    final static int J = 10;
    final static int K = 11;
    final static int L = 12;
    final static int M = 13;
    final static int N = 14;
    final static int O = 15;
    final static int P = 16;
    final static int Q = 17;
    final static int R = 18;
    final static int S = 19;
    final static int T = 20;
    final static int U = 21;
    final static int V = 22;
    final static int W = 23;
    final static int X = 24;
    final static int Y = 25;
    final static int Z = 26;
    final static int a = 27;
    final static int b = 28;
    final static int c = 29;
    final static int d = 30;
    final static int e = 31;
    final static int f = 32;
    final static int g = 33;
    final static int h = 34;
    final static int i = 35;
    final static int j = 36;
    final static int k = 37;
    final static int l = 38;
    final static int m = 39;
    final static int n = 40;
    final static int o = 41;
    final static int p = 42;
    final static int q = 43;
    final static int r = 44;
    final static int s = 45;
    final static int t = 46;
    final static int u = 47;
    final static int v = 48;
    final static int w = 49;
    final static int x = 50;
    final static int y = 51;
    final static int z = 52;
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
