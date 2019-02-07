import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

import static java.lang.Integer.parseInt;


 class ADD_TO_LIST {
     static HashMap<String,Integer> scorelist = new HashMap<String, Integer>();

     private static Scanner input = null;
    public static final File f = new File("scores.txt");


    public static HashMap<String, Integer> LISTOSCORE() { // inputs scores and dates from scores.txt, places them into a HashMap


        try {
            input = new Scanner(f);
            while (input.hasNext()) {
                String data = input.nextLine();
                String[] dat = data.split(" , ");
                int sco = parseInt(dat[0]);
                String date = dat[1];
                scorelist.put(date, sco);
            }
        } catch (FileNotFoundException e) { // exception is handled
            System.out.println("file not found");
        }


        return scorelist;
    }

}
