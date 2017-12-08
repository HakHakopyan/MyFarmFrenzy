package Observer;

import Season.Season;

import java.util.ArrayList;
import java.util.List;

public class MyObservable<T extends ObserverTime&ObserverSeason> {
    List<T> myObservers = new ArrayList<>();

    public void addObserver(T t) {
        this.myObservers.add(t);
    }

    public void notifyTimeUpdate() {
        for (T t: myObservers) {
            t.updateTime();
        }
    }

    public void notifySeasonChange(Season season) {
        for (T t: myObservers) {
            t.changeSeason(season);
        }
    }

    public void deleteObserver(T t) {
        this.myObservers.remove(t);
    }
}
