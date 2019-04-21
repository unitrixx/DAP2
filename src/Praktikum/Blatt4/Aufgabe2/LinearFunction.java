public class LinearFunction {

    private double m;           //  m ist die Steigung der Geraden
    private double n;           //  n ist der Schnittpunkt mit der y-Achse
    private Point[] inits;      //  inits sind die initialen Punkte, aus denen die Gerade gebildet wurde

    public LinearFunction (Point p1, Point p2){
        calcM(p1, p2);
        calcN(p1);
        inits = new Point[]{p1, p2};
    }

    //  Bestimmt die Steigung der Geraden
    public void calcM(Point p1, Point p2) {
        m = (p1.get(1) - p2.get(1)) / (p1.get(0) - p2.get(0));
    }

    //  Bestimmt den Schnittpunkt der Geraden mit der y-Achse
    public void calcN(Point p) {
        n = p.get(1) - p.get(0) * m;
    }

    public Point[] getInits() {
        return inits;
    }

    //  Berechnet den Funktionswert zu einem gegebenen x-Wert
    public double getVal(double x) {
        return n + x * m;
    }

    // Bestimmt die relative Position des Punktes p zur Geraden
    public int relativeLocation(Point p){
        double val = getVal(p.get(0));
        if (val < p.get(1))         //  Ist der y-Wert des Punktes größer als der berechnete, liegt er oberhalb
            return 1;
        else if (val > p.get(1))    //  Ist der y-Wert des Punktes kleiner als der berechnete, liegt er unterhalb
            return -1;
        else                        //  Sind die Werte gleich, liegt der Punkt auf der Geraden
            return 0;
    }
}
