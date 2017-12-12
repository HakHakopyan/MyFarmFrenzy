import crop.Crop;
import season.Season;

import java.util.*;

/**
 * Является внешним наболдателем для класса Farm,
 * информируется через MyExternalObserver об изменениях состояний объектов внутри Farm
 */
public class FarmNewsListener implements Observer{
    List<String> news = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Crop) {
            news.add("Получили урожай: " +
                    arg.getClass().getSimpleName() + " " + ((Crop)arg).getCount() + "шт.");
        } else
            if (arg.getClass().equals(Season.class)) {
                news.add("Change season: " + arg);
            }

    }

    /**
     * позволяет узнать существуют ли новости из класса {@link Farm Farm}
     * @return
     */
    public boolean isNews() {
        return news.size() > 0;
    }

    /**
     * Получить существующие новости
     * @return коллекцию текстовых новостей
     */
    public List<String> getNews() {
        List<String> retNews = new ArrayList<>();
        retNews.addAll(news);
        news.clear();

        return retNews;
    }
}
