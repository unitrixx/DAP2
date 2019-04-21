public class Eratosthenes {

    public static void main(String[] args){
        //Initialisierung der Parameter
        int size = 0;
        String comp = "-o";
        boolean out = false;

        //Verarbeiten der Eingabe
        try {
            size = Integer.parseInt(args[0]);
            if (args.length > 1)                    //Prüfen, ob ein zweiter Parameter angegeben wurde
                out = comp.equals(args[1]);       //Prüfen, ob zweiter Parameter gleich "-o" ist
        }
        //Fehlerhafte Eingabe der Parameter
        catch (Exception e){
            System.out.println("Parameter: array_size [-o]");
            System.exit(0);
        }

        boolean[] primes = isPrime(size);
        if (out){
            String output = "1";
            for (int i = 2; i < primes.length; i++) {
                if (primes[i]){
                    output = output + ", " + i;
                }
            }
            System.out.println(output);
        }

    }

    public static boolean[] isPrime (int n){
        boolean[] primes = new boolean[n];                          //Feld der Länge n anlegen
        for (int i = 0; i < primes.length; i++) {                   //Feld mit true initialisieren
            primes[i] = true;
        }
        for (int k = 2; k < primes.length; k++) {                   //Das eigentliche Sieb des Eratosthenes
            if (primes[k]){
                for (int s = 2*k; s < primes.length; s+= k) {
                    primes[s] = false;
                }
            }
        }
        return primes;
    }

}
