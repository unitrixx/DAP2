public class Quicksort {

    public static final String INFO = "Für einen validen Aufruf wird ein Integerwert erwartet," +
            "der für die Anzahl der zu sortierenden Werte steht.";

    public static void main (String[] args){

        //  Initialisierung des Parameters für die Arraylänge
        int size = 0;

        try{
            //  Ein Aufruf mit mehr oder weniger als einem Parameter ist nicht zulässig
            if (args.length != 1)
                throw new IllegalArgumentException();

            //  Auslesen des eingebenen Parameters
            size = Integer.parseInt(args[0]);
        }
        catch (Exception e){
            System.out.println(INFO);
            System.exit(0);
        }

        // Initialisieren und Befüllen des zu sortierenden Arrays
        int[] array = fillRandom(size);

        //  Beginn der Messung
        long tStart = System.currentTimeMillis();

        //  Zu messender Code
        quicksort(array, 0, array.length-1);

        //  Ende der Messung
        long tEnd = System.currentTimeMillis();

        //  Berechnung der benötigten Zeit
        long milSecs = (tEnd - tStart);

        System.out.println("Zeit f\u00fcr " + size + " Zahlen: " + milSecs);
    }

    //  Implementierung des Quicksort-Algorithmus nach Pseudocode aus der Aufgabenstellung
    public static void quicksort(int[] arr, int l, int r){
        if (l < r){
            int i = l;
            int j = r;
            int pivot = arr[(l+r)/2];
            while (i <= j){
                while (arr[i] < pivot)
                    i += 1;
                while (arr[j] > pivot)
                    j -= 1;
                if (i <= j){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i += 1;
                    j -= 1;
                }
            }
            quicksort(arr, l, j);
            quicksort(arr, i, r);
        }
        assert isSorted(arr) : "Array ist NICHT sortiert.";
    }

    public static int[] fillRandom(int size) {
        int[] array = new int[size];
        java.util.Random numberGenerator = new java.util.Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = numberGenerator.nextInt();
        }
        return array;
    }

    public static boolean isSorted(int[] array) {
        //Sobald es ein Paar von aufeinander folgenden Werten im Array gibt,
        //von denen der "erste" der größere Wert ist, ist das Array nicht aufsteigend sortiert.
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

}
