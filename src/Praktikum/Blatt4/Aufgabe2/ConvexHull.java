import java.util.Iterator;
import java.util.LinkedList;

public class ConvexHull {

    public LinkedList<Point> simpleConvex(Point[] P){

        //  LinearFunction ist eine selbst programmierte Hilfsklasse,
        //  welche die gerichteten Kanten in Form von linearen Funktionen
        //  im zweidimensionalen Raum darstellt.

        //  Initialisierung der Menge/Liste für die gerichteten Kanten
        LinkedList<LinearFunction> bounds = new LinkedList<>();

        //  Umsetzung des Pseudocodes aus den Vorlesungsfolien
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P.length; j++) {
                if (i == j)
                    continue;
                else{
                    boolean valid = true;

                    //  Initialisierung der gerichteten Kante zwischen P[i] (p) und P[j] (q)
                    LinearFunction f = new LinearFunction(P[i], P[j]);

                    for (int z = 0; z < P.length - 1; z++){
                        if (z == i || z == j)
                            continue;
                        else{

                            //  Wenn ein Punkt auf einer anderen Seite der Geraden liegt als sein
                            //  Vorgänger bzw. Nachfolger, ist die Kante f keine Grenze der Konvexen Hülle.
                            if (f.relativeLocation(P[z]) != f.relativeLocation(P[z+1])) {
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid && !bounds.contains(f)){
                        bounds.add(f);
                    }
                }
            }
        }

        //  Initialisierung der Liste für die Punkte der Konvexen Hülle
        LinkedList<Point> hull = new LinkedList<>();
        LinearFunction g = bounds.getFirst();
        bounds.remove(g);

        //  Hinzufügen der ersten Punkte
        for (Point p : g.getInits() ) {
            hull.add(p);
        }
        while (bounds.size() > 0) {
            Iterator<LinearFunction> it = bounds.iterator();
            while (it.hasNext()) {
                LinearFunction f = it.next();
                Point[] inits = f.getInits();
                boolean found = false;

                //  Wenn eine Kante aus der Menge in einem Punkt mit dem Punkt übereinstimmt,
                //  der als letztes in die Liste der Hülle hinzugefügt wurde ist der andere Punkt der nächste
                //  in der Folge der Hülle.
                if (inits[0] == hull.getLast()){
                    hull.add(inits[1]);
                    found = true;
                }
                else if (inits[1] == hull.getLast()){
                    hull.add(inits[0]);
                    found = true;
                }

                //  Wenn der nächste Punkt der Hülle gefunden wurde, wird die Kante, mit diesem als Endpunkt,
                //  aus der Liste/Menge der Kanten gelöscht.
                if (found)
                    bounds.remove(f);

            }
        }
        return hull;
    }

}
