package Praktikum.Blatt1;

public class Eratosthenes {

    public static void main(String[] args){

        int size = Integer.parseInt(args[0]);
        boolean out = (args.length > 1);
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
        boolean[] primes = new boolean[n];
        for (int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }
        for (int k = 2; k < primes.length; k++) {
            if (primes[k]){
                for (int s = 2*k; s < primes.length; s+= k) {
                    primes[s] = false;
                }
            }
        }
        return primes;
    }

}
