import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Anwendung {

    public static final String INFO = "Error!!";

    public static void main (String[] args){

        BufferedReader file = null;
        try{
            file = new BufferedReader(new FileReader(args[0]));
        }
        catch (Exception e){
            System.out.println(INFO);
            System.exit(0);
        }

    }

    public static ArrayList<Interval> intervalScheduling (ArrayList<Interval> intervals){

        if (intervals == null)
            return null;
        int n = intervals.size();
        if (n == 0)
            return null;

        ArrayList<Interval> ret = new ArrayList<>();
        ret.add(intervals.get(0));

        int j = 0;
        for (int i = 1; i < n; i++) {
            if (intervals.get(i).getStart() >= intervals.get(j).getEnd()){
               ret.add(intervals.get(i));
               j = i;
            }
        }

        return ret;
    }

}
