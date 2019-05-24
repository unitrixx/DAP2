import java.util.Stack;

public class EditDistance {

    public static void main (String[] args){
        boolean output = false;
        if (args.length > 3){
            System.out.println("Error!!");
            System.exit(0);
        }
        if (args.length == 3 && args[2].equals("-o"))
            output = true;
        else if (args.length == 3){
            System.out.println("Error!!");
            System.exit(0);
        }
        String a = args[0];
        String b = args[1];
        int[][] editDistance = distance(a, b);
        if (!output)
            System.out.println("Kuerzeste Editierdistanz zwischen \n"
                    + a + " und " + b + " betraegt: \n" +
                    editDistance[a.length()][b.length()]);
        else {
            printEditOperations(editDistance, a, b);
        }
    }

    public static int[][] distance(String a, String b){

        int[][] array = new int[a.length() + 1][b.length() + 1];

        array[0][0] = 0;
        for (int i = 1; i <= a.length(); i++) {
            array[i][0] = i;
        }
        for (int j = 1; j <= b.length(); j++) {
            array[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1))
                    array[i][j] = array[i-1][j-1];
                else {
                    array[i][j] = Math.min(array[i][j-1] + 1, array[i-1][j] + 1);
                    array[i][j] = Math.min(array[i][j], array[i-1][j-1] + 1);
                }
            }
        }

        return array;

    }

    public static void printEditOperations(int[][] array, String a, String b){

        /******** Head der Ausgabe ********/
        String head = "Loesung fuer \"" + a + "\" --> \"" + b + "\" mit Gesamtkosten: "
                        + array[a.length()][b.length()];
        System.out.println(head);
        for (int i = 0; i < head.length(); i++) {
            System.out.print("=");
        }
        System.out.print("\n");
        /**********************************/

        int i = a.length();
        int j = b.length();

        Stack<Integer> out = new Stack<>();

        //  "Weg" der Berechnung rekonstuieren
        while (i > 0 && j > 0){
            if (array[i-1][j-1] < array[i][j]){
                out.push(1);    //  1 = ersetzen
                i--;
                j--;
            }
            else if (array[i][j-1] < array[i][j]){
                out.push(2);    //  2 = einfügen
                j--;
            }
            else if (array[i-1][j] < array[i][j]){
                out.push(3);    //  3 = löschen
                i--;
            }
            else if (array[i-1][j-1] == array[i][j]){
                out.push(0);    //  0 = beibehalten
                i--;
                j--;
            }
        }
        while (i > 0) {
            out.push(3);
            i--;
        }
        while (j > 0) {
            out.push(2);
            j--;
        }

        char[] ar = a.toCharArray();
        char[] br = b.toCharArray();

        int k = 1;
        int n = 1;
        int maxLength = Math.max(a.length(), b.length());

        //  Ausgabe erzeugen
        while (!out.isEmpty() && k <= maxLength) {
            System.out.print(n + ") ");
            if (out.peek() == 0) {
                System.out.print("Kosten 0: " + ar[k - 1] + " an Postition " + k);
                System.out.print(" --> " + new String(ar));
            }
            else if (out.peek() == 1){
                System.out.print("Kosten 1: Ersetze " + ar[k-1] + " durch " + br[k-1] + " an Position " + k);
                ar[k-1] = br[k-1];
                System.out.print(" --> " + new String(ar));
            }
            else if (out.peek() == 2){
                System.out.print("Kosten 1: Fuege " + br[k-1] + " an Position " + k + " ein");
                char[] arn = new char[ar.length+1];
                for (int l = 0; l < arn.length; l++) {
                    if (l == k-1)
                        arn[l] = br[l];
                    else if (l < k-1)
                        arn[l] = ar[l];
                    else
                        arn[l] = ar[l-1];
                }
                ar = arn;
                System.out.print(" --> " + new String(ar));
            }
            else if (out.peek() == 3){
                System.out.print("Kosten 1: Loesche " + ar[k-1] + " an Position " + k);
                char[] arn = new char[ar.length-1];
                for (int l = 0; l < ar.length; l++) {
                    if (l == k-1) {
                        continue;
                    }
                    else if (l < k-1)
                        arn[l] = ar[l];
                    else
                        arn[l-1] = ar[l];
                }
                ar = arn;
                System.out.print(" --> " + new String(ar));
                k--;
            }
            System.out.print("\n");
            k++;
            n++;
            out.pop();
        }
    }

}
