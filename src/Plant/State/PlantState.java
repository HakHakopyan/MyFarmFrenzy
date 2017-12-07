package Plant.State;
import Base.Seasonable;
import Command.Command;
import Crop.*;

import Base.ObserverTime;
import Season.Season;

public interface PlantState extends ObserverTime, Seasonable {

    public String getRepresentation(String name);

    public Cropable GetCrop();

    public boolean isDie();

    public boolean isCropReady();

    public void doComand(Command com);

    public void changeSeason(Season season);

}
