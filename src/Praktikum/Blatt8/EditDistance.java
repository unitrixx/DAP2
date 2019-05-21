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

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }

        int i = a.length();
        int j = b.length();

        Stack<Integer> out = new Stack<>();

        while (i > 0 && j > 0){
            if (array[i-1][j-1] == array[i][j] - 1){
                out.push(1);    //  1 = ersetzen
                i--;
                j--;
            }
            if (array[i][j-1] == array[i][j] - 1){
                out.push(2);    //  2 = einfügen
                j--;
            }
            if (array[i-1][j] == array[i][j] - 1){
                out.push(3);    //  3 = löschen
                i--;
            }
            if (array[i-1][j-1] == array[i][j]){
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

        while (!out.isEmpty())
            System.out.println(out.pop());
    }

}
