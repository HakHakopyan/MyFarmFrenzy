import Crop.Crop;

import java.util.Observable;
import java.util.Observer;

public class WorkWithConsole implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().getSuperclass().equals(Crop.class)) {
            System.out.println("Получили урожай: " +
                    arg.getClass().getSimpleName() + " " + ((Crop)arg).getCount() + "шт.");
        }
    }
}
