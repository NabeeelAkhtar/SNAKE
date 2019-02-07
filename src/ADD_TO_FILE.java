import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ADD_TO_FILE {


public static void added(){
    Integer p = GamePanel.score; //gets score from gamepanel
    BufferedWriter input = null;
    final File f = new File("scores.txt");
    Date now = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //formats obtained date
    String str = df.format(now);

    try{
        input = new BufferedWriter(new FileWriter(f,true)); //score and date are appended to file
        PrintWriter out = new PrintWriter(input);
        out.println(p+" , "+str);
        out.close();
    }catch(FileNotFoundException e){
        System.out.println("file not found");
    }catch (IOException r){
        System.out.println("Could not write");
    }



    }
}
