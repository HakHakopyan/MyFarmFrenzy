import crop.Crop;
import season.Season;

import java.util.*;

public class FarmNewsListener implements Observer{
    Boolean myIsNews = false;
    List<String> news = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().getSuperclass().equals(Crop.class)) {
            news.add("Получили урожай: " +
                    arg.getClass().getSimpleName() + " " + ((Crop)arg).getCount() + "шт.");
        } else
            if (arg.getClass().equals(Season.class)) {
                news.add("Change season: " + arg);
            }

    }

    public boolean isNews() {
        return news.size() > 0;
    }

    public List<String> getNews() {
        List<String> retNews = new ArrayList<>();
        retNews.addAll(news);
        news.clear();
        myIsNews = false;

        return retNews;
    }
}
