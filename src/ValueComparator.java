import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class ValueComparator implements Comparator<String> { //compares values based on key Strings in a map
                                                             //if values are equal, compares keys as dates, so latest date is always first.
                                                             //essential for Ranking scores.

    private Map<String, Integer> map;

    public ValueComparator(Map<String, Integer> map) {
        this.map = map;
    }

    public int compare(String s1, String s2) {
        int ab = 0;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try{Date dat1 = df.parse(s1);
            Date dat2 = df.parse(s2);
            ab = dat2.compareTo(dat1);
        }catch (ParseException e){}


        if (map.get(s2).equals(map.get(s1))){return ab;}else{

        return map.get(s2).compareTo(map.get(s1));}
    }
}