public abstract class Simplex {

    int d;              //  Dimension des Simplex
    Point[] points;     //  Eckpunkte des Simplex

    public Simplex(int dim, Point... pnts){

        //  Wenn die angegebene Dimension des Simplex (dim+1)
        //  und die Anzahl der Punkte nicht übereinstimmen,
        //  kann kein richtiger Simplex erzeugt werden.
        if (dim+1 != pnts.length)
            throw new IllegalArgumentException();

        d = dim;
        points = pnts;
    }

    //  "Getter" für die Dimension des Simplex
    public int dim(){
        return d;
    }

    //  "Getter" für den Eckpunkt des Simplex am Index i des Punkte-Arrays
    public Point get(int i) {
        return points[i];
    }

    //  Berechnet des Umfang des Simplex und gibt diesen zurück.
    public double perimeter(){

        //  Initialisierung der Variable für den Umfang
        double perim = 0;

        //  Schleife zur Aufsummierung der Abstände der Eckpunkte
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (j == points.length)
                    perim +=points[i].distance(points[0]);
                else
                    perim += points[i].distance(points[j]);
            }
        }
        return perim;
    }

    //  Abstrakte Methode zur Validierung des Simplex
    public abstract boolean validate();
}
