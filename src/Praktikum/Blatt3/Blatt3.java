public class Blatt3 {

    public static String INFO = "Als Parameter wird eine Zeitvorgabe in Form " +
            "einer rationalen Zahl benötigt.";

    public static void main (String[] args){

        //  Initialisierung des Zeit-Parameters
        float time = 0;

        try{
            if (args.length != 1)
                throw new IllegalArgumentException();
            time = Float.parseFloat(args[0]);
        }
        catch (Exception e){
            System.err.println(INFO);
            System.exit(0);
        }

        //  Initialisieren der Grenzen und der Variable für die Laufzeit
        int n1 = 1000;                    //  Obere Grenze
        int n2 = 0;                       //  Untere Grenze
        float res = measureTime(n1);      //  Laufzeit

        //  Bestimmen des Intervals, in dem die binäre Suche gestartet werden soll
        while (res < time) {
            n1 *= 2;                //  Verdopplung der Elementenzahl
            res = measureTime(n1);  //  Messen der Laufzeit für die doppelte Elementenzahl
        }
        n2 = n1 / 2;

        //  Binäre Suche

        boolean found = false;
        int n = 0;

        while (!found) {
            n = (n1 / 2) + (n2 / 2);
            res = measureTime(n);
            if (Math.abs(res - time) < 100 || n1 == n2)
                found = true;
            else if (res > time)
                n1 = n;
            else
                n2 = n;
            System.out.println("Anzahl der Elemente: " + n);
            System.out.println("Ben\u00f6tigte Zeit in sec: " + res / 1000);
        }

        System.out.println("Bei einer Anzahl von " + n + " Elementen betr\u00e4gt die Laufzeit des " +
                "BubbleSort-Algorithmus " + res + " ms. \n Vorgabe waren: " + time + " ms");
    }

    public static float measureTime (int size){
        //  Initialisierung des zu sortierenden Feldes
        int[] array = fillDesc(size);

        //  Beginn der Messung
        long tStart = System.currentTimeMillis();

        //  Zu messender Code
        bubbleSort(array);

        //  Ende der Messung
        long tEnd = System.currentTimeMillis();

        //  Berechnung der benötigten Zeit
        float milSecs = (tEnd - tStart);

        return milSecs;
    }

    public static void bubbleSort (int[] array){
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > i; j--) {
                if (array[j-1] > array[j]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }

    public static int[] fillDesc (int size){
        int[] arr = new int[size];
        for (int i = size; i > 0; i--) {
            arr[size - i] = i;
        }
        return arr;
    }

}
