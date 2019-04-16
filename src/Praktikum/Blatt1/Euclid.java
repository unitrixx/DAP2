package Praktikum.Blatt1;

public class Euclid {

        public static void main (String[] args){
            try {
                System.out.println(euclid(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
            }
            catch (Exception e){
                System.out.println("Zwei Integer als Parameter erwartet!");
                System.exit(0);
            }
        }

        public static int euclid( int a, int b ){
            if ( b == 0 )
                return a;
            else
                return euclid( b , a % b );
        }

}
