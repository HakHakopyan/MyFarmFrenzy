package observer;

import season.Season;

/**
 * interface defining the observer for the season
 */
public interface ObserverSeason {
    /**
     * Defines method for change Season
     * @param season contains the current season
     */
    public void changeSeason(Season season);
}
