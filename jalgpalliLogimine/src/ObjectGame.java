import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Holds all the information abouth the game itself, team names, tracks score, holds event log, time
 * Created by ovekangur on 08/12/16.
 */
public class ObjectGame {
    String koduvoistkond;
    String voorsilVoistkond;
    String seis;
    int koduVaravad;
    int voorsilVaravad;
    String aeg;
    int minutid;
    int sekundid;
    Date start;
    ArrayList<String> list;

    public ObjectGame(String koduvoistkond, String voorsilVoistkond, String seis, String aeg) {
        this.koduvoistkond = koduvoistkond;
        this.voorsilVoistkond = voorsilVoistkond;
        this.seis = seis;
        this.aeg = aeg;
        this.koduVaravad = Integer.parseInt(seis.split(":")[0]);
        this.voorsilVaravad = Integer.parseInt(seis.split(":")[1]);
        this.sekundid = Integer.parseInt(aeg.split(":")[1]);
        this.minutid = Integer.parseInt(aeg.split(":")[0]);
        this.start = new Date();
        this.list = new ArrayList<String>();
    }
    // Calculatesand returns as string game time
    public String strAeg() {

        Date now = new Date();
        int sekundid = (int) (now.getTime() - this.start.getTime()) / 1000 + this.sekundid;
        int minutid = this.minutid;

        while (sekundid > 59) {
            minutid++;
            sekundid -= 60;
        }

        String strSekundid = Integer.toString(sekundid);
        String strMinutid = Integer.toString(minutid);

        // Fromats string if number is less than 10
        if (sekundid < 10) {
            strSekundid = "0" + Integer.toString(sekundid);
        }
        if (minutid < 10) {
            strMinutid = "0" + Integer.toString(minutid);
        }

        return strMinutid + ":" + strSekundid;
    }

    // Returns current score as string
    public String seis() {
        return Integer.toString(this.koduVaravad) + " : " + Integer.toString(this.voorsilVaravad);
    }

    // Some help from http://stackoverflow.com/questions/28817715/writing-an-arraylist-to-file
    // Writes events log list to text file
    public void writer() throws IOException {
        String fileName = this.koduvoistkond + "_" + this.voorsilVoistkond + ".txt";
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        FileOutputStream fo = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fo);

        for (String elem : this.list){
            pw.println(elem);
        }

        pw.close();
        fo.close();
    }

}
