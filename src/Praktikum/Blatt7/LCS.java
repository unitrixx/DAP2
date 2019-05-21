import java.security.InvalidParameterException;
import java.util.*;

public class LCS {

    public static void main(String[] args) {
        int n = 0;
        try {
            if (args.length != 1)
                throw new InvalidParameterException();
            n = Integer.parseInt(args[0]);
        }
        catch (Exception e) {
            System.out.println("Error!");
            System.exit(0);
        }
        Random rand = new Random();
        String a = randStr(n, rand);
        String b = randStr(n, rand);
        int[][] res = lcs(a, b);
        System.out.println("Generierte Strings: \n" + a + "\n" + b);
        System.out.println("Laenge der Longest Common Sequence: \n" + res[n][n]);
        System.out.println("Longest Common Sequence: ");
        System.out.println(generateOutput(res, a, b, n, n));
    }

    public static int[][] lcs(String x, String y) {
        int m = x.length();
        int n = y.length();
        char[] xr = x.toCharArray();
        char[] yr = y.toCharArray();
        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (xr[i-1] == yr[j-1])
                    c[i][j] = c[i - 1][j - 1] + 1;
                else {
                    if (c[i - 1][j] >= c[i][j - 1])
                        c[i][j] = c[i - 1][j];
                    else
                        c[i][j] = c[i][j - 1];
                }
            }
        }
        return c;
    }

    public static String randStr(int n, Random r) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return res.toString();
    }

    public static String generateOutput(int[][] array, String a, String b, int i, int j) {
        char[] ar = a.toCharArray();
        char[] br = b.toCharArray();
        // Anfang vom array => keine Zeichen werden verglichen
        if (i == 0 || j == 0)
            return "";
        if (array[i][j] == array[i][j - 1])
            return generateOutput(array, a, b, i, j - 1);
        // Zeichen wurde gefunden
        if (ar[i - 1] == br[j - 1])
            return generateOutput(array, a, b, i - 1, j - 1) + ar[i - 1];
        if (array[i - 1][j] > array[i][j - 1])
            return generateOutput(array, a, b, i - 1, j);
        return generateOutput(array, a, b, i, j - 1);
    }
}
