import java.util.Date;

/**
 * Checks if the click made is on the pich or not and adjusts it if needed
 * Created by ovekangur on 08/12/16.
 */
public class Funktsioonid {

    public static int kontrolliX (int x) {
        if (x < 200) {
            x = 200;
        }
        if (x > 1250) {
            x = 1250;
        }
        return x;
    }

    public static int kontrolliY (int y) {
        if (y < 100) {
            y = 100;
        }
        if (y > 780) {
            y = 780;
        }
        return y;
    }

}
