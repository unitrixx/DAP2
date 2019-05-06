import javax.print.DocFlavor;

public class CoinChange {

    public static final String INFO = "Es werden zwei Parameter erwartet. \n" +
            "Als Erstes wird ein String für die W\u00e4hrung erwartet. Entweder \"Euro\" oder \"Alternative\". \n" +
            "Als Zweites wird ein ganzzahliger positiver Wert in Cent erwartet, der zerlegt werden soll.";

    public static void main (String[] args){

        int value = 0;
        boolean euro = true;
        String currency = "Euro";

        try{

            if (args.length != 2)
                throw new IllegalArgumentException();

            if (args[0].equals("Alternative"))
                euro = false;
            else if (!args[0].equals("Euro"))
                throw new IllegalArgumentException();

            value = Integer.parseInt(args[1]);
        }
        catch (Exception e){
            System.out.println(INFO);
            System.exit(0);
        }

        int[] w;
        if (euro)
            w = new int[]{200, 100, 50, 20, 10, 5, 2, 1};
        else {
            w = new int[]{200, 100, 50, 20, 10, 5, 4, 2, 1};
            currency = "Alternative";
        }

        int[] res = change(value, w);

        String out = "Der Wert " + value + " l\u00e4sst sich in der W\u00e4hrung \"" + currency + "\" in: \n";
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 0)
                continue;
            else{
                out += res[i] + "x" + w[i] + " Cent\n";
            }
        }
        out += "zerlegen.";
        System.out.println(out);

    }

    public static int[] change (int b, int[] w){

        //  Initialisierung des Ergebnisarrays
        int[] res = new int[w.length];
        int k = 0;  //  Zähler für die Arrays

        //  Umsetzung des gierigen Algorithmus
        while (b > 0 && k < w.length){
            if (b >= w[k]){
                res[k] = b / w[k];
                b = b - w[k] * res[k];
            }
            k++;
        }

        return res;
    }

}
