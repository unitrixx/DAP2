package Praktikum.Blatt2;

public class Sortierung {

    public static void main(String[] args) {

        //Startinitialisierung der Parameter
        int size = 0;           //Array-Größe
        String fill = "rand";   //Füllart des Arrays

        //Verarbeitung der Eingabe
        try {
            size = Integer.parseInt(args[0]);
            if (args.length > 1) {
                fill = args[1];
            }
        }
        catch (Exception e) {
            System.out.println("Parameter : array_size [auf|ab|rand]");
            System.exit(0);
        }

        int[] array;
        if (fill.equals("auf")) array = fillUp(size);
        else if (fill.equals("ab")) array = fillDown(size);
        else array = fillRandom(size);

        insertionSort(array);

    }

    public static void insertionSort(int[] array) {
        //Implementierung des Pseudocodes aus den Vorlesungsfolien
        for (int j = 2; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while (i > 0 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
            }
            array[i] = key;
        }
        assert isSorted(array);
    }

    // Copy and Paste aus der Aufgabenstellung
    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
        assert isSorted(array);
    }

    // Merge Sort Implementierung aus dem Zusatzblatt
    public static void mergeSort(int[] array, int[] tmpArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, tmpArray, left, middle);
            mergeSort(array, tmpArray, middle + 1, right);
            merge(array, tmpArray, left, middle, right);
        }
    }

    public static void merge(int[] array, int[] tmpArray, int left, int middle, int right) {
        // Zähler Variablen
        int i = left;       //Linkes Array
        int j = middle + 1; //Rechtes Array
        int tmpI = 0;       //Temporäres Array

        //Sortieren vom linken und rechten Array im tmpArray, bis das linke oder reche Array leer ist
        while (i < middle + 1 && j <= right) {
            if (array[i] <= array[j]) {
                tmpArray[tmpI] = array[i];
                tmpI++;
                i++;
            }
            else {
                tmpArray[tmpI] = array[j];
                tmpI++;
                j++;
            }
        }

        //Wenn das linke Array noch Elemente enthält wird alles in tmpArray kopiert
        while (i < middle + 1) {
            tmpArray[tmpI] = array[i];
            i++;
            tmpI++;
        }

        //Wenn das rechte Array noch Elemente enthält wird alles in tmpArray kopiert
        while (j <= right) {
            tmpArray[tmpI] = array[j];
            j++;
            tmpI++;
        }

        //tmpArray enthält das linke und rechte Array sortiert
        //array wird von index left bis right den daten von tmpArray aktualisiert
        for (tmpI = 0; tmpI <= (right - left); tmpI++) {
            array[left + tmpI] = tmpArray[tmpI];
        }
    }

    public static boolean isSorted(int[] array) {
        //Sobald es ein Paar von aufeinander folgenden Werten im Array gibt,
        //von denen der "erste" der größere Wert ist, ist das Array nicht aufsteigend sortiert.
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }

    public static int[] fillUp(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] fillDown(int size) {
        int[] array = new int[size];
        for (int i = 1; i <= array.length; i++) {
            array[array.length - i] = i;
        }
        return array;
    }

    public static int[] fillRandom(int size) {
        int[] array = new int[size];
        java.util.Random numberGenerator = new java.util.Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = numberGenerator.nextInt();
        }
        return array;
    }

}
