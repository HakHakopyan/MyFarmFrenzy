import Crop.Crop;
import Crop.Cropable;
import Factory.*;
import FieldElement.Arable;
import FieldElement.GreenHouse;
import FieldElement.Parsel;
import Plant.Plantable;
import Plant.Tree.AppleTree;
import Season.Season;
import Storage.*;
import Visitor.Visitor;
import Visitor.VisitorTotalCost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class FarmGameTestClass implements Observer {
    public static void main(String[] args) {
        Plantable apple = new AppleTree(20*12, 10);
        Factoriable factory = new Factory();
        factory.registre(Plants.class);
        Arable gHouse = new GreenHouse(new Parsel(factory));

        gHouse.setPlant("AppleTree");
        apple = gHouse;

        Storable<Cropable> myStorage = new Storage();
        boolean bool = true;
        for(int i = 1; i < 400; i++) {
            apple.updateTime();
            if (bool) {
                apple.doComand((x) -> x.setCount((int)(x.getCount()*0.2 + x.getCount())));
                bool = false;
            }
            if (apple.isDie()) {
                System.out.println("i = " + i + "  Die");
                break;
            }
            if (apple.isCropReady()) {
                bool = true;
                Cropable crop = apple.getDelivery();
                System.out.println("i = " + i + " " + crop.getClass().getSimpleName() + " = " + crop.getCount());

                myStorage.store(crop);
            }
        }

        VisitorTotalCost vTC = new VisitorTotalCost();
        myStorage.acceptVisit(vTC);

        System.out.println(vTC.getTotalCost());
        /*
        while (true) {
            TimeThread t = new TimeThread();
            t.start();
        }
        */
        WorkWithConsole consW = new WorkWithConsole();
        Farm farm = new Farm(4, consW);
        farm.setPlant("AppleTree", 1);
        farm.setPlant("AppleTree", 3);
        List<String> sRep = farm.getFieldRepresentation();
        int i = 1;
        for (String s: sRep) {
            System.out.println(i + " " + s);
            i++;
        }


        try
        {
            String line;
            BufferedReader input = new BufferedReader();
            while((line = input.readLine()) != null)
            {
                System.out.println(line);
            }
            input.close();
        }
        catch(Exception e)
        {
            //Log.e(TAG,e.toString(),e);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}