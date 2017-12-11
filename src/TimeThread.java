import season.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Deprecated
/**
 * This class not used in the program, because it is difficulty for Console
 */
public class TimeThread extends Thread {
    List<Observable> myObs = new ArrayList<>();

    public TimeThread(Observable... observables) {
        super("TimeThread");
        for (Observable ob: observables) {
            myObs.add(ob);
        }
    }

    @Override
    public void run() {
        int myTime = 0;
        Season mySeason = Season.SUMMER;

        while (true) {
            try {
                Thread.sleep(1000*10);
                myTime++;
                updateObserverables(null);
                if (myTime == 6) {
                    myTime = 0;
                    if (mySeason == Season.SUMMER)
                        mySeason = Season.WINTER;
                    mySeason = Season.SUMMER;

                    updateObserverables(mySeason);
                }
            } catch (InterruptedException e) {
                // прерывание дочернего потока
            }
        }
    }

    public <T> void updateObserverables(T t) {
        for(Observable ob: myObs) {
            ob.notifyObservers(t);
        }
    }
}
