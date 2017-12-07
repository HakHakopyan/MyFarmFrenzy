import java.util.Observable;

public class MyObserbarable extends Observable {
    public MyObserbarable() {
        this.setChanged();
    }
}
