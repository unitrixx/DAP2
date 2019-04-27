public class Point {

    private int d;              //  Dimension des Punktes
    private double[] coords;    //  Double-Array mit den Koordinaten des Punktes

    public Point(int dim, double... values) {

        //  Wenn angegebene Dimension und Anzahl der Koordinaten nicht übereinstimmen, muss das korrigiert werden.
        if (dim != values.length)
            throw new IllegalArgumentException("Dimension und Anzahl der Koordinaten stimmen nicht überein!");

        d = dim;
        coords = values;
    }

    public Point(double... values) {
        this(values.length, values);
    }

    //  "Getter" für die Dimension des Punktes
    public int dim() {
        return d;
    }

    //  "Getter" für die Koordinate des Punktes in der Dimension i+1
    public double get(int i) {
        return coords[i];
    }

    //  Berechnet den Euklidischen Abstand zwischen dem aufrufenden Punkt und dem Punkt,
    //  der als Argument übergeben wird.
    //  Wird benötigt um den Perimeter eines Simplex zu berechnen.
    public double distance(Point p2) {

        //  Wenn die beiden Punkte nicht die gleiche Dimension haben,
        //  kann kein Abstand zwischen ihnen berechnet werden.
        if (d != p2.d)
            throw new IllegalArgumentException();

        //  Die Quadrate der Differenz der Koordinaten berechnen.
        double[] squares = new double[d];
        for (int i = 0; i < d; i++) {
            squares[i] = Math.pow((coords[i] - p2.coords[i]), 2);
        }

        //  Erst werden die Quadrate der Koordinatendifferenten aufsummiert
        double result = 0;
        for (double sqr : squares) {
            result += sqr;
        }

        //  Dann wird die Wurzel aus der Summe gezogen.
        //  -> quasi Anwendung des mehrdimensionalen Satz des Pythagoras
        result = Math.sqrt(result);
        return result;
    }
}
