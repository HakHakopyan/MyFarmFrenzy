import Crop.Cropable;
import Factory.*;
import FieldElement.Arable;
import FieldElement.GreenHouse;
import FieldElement.Parsel;
import Plant.Plantable;
import Plant.Tree.AppleTree;
import Storage.*;
import Visitor.VisitorTotalCost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class FarmGameTestClass{
    public static void main(String[] args) {

        /*
        Plantable apple = new AppleTree(20 * 12, 10);
        Factoriable factory = new Factory();
        factory.registre(Plants.class);
        Arable gHouse = new GreenHouse(new Parsel(factory));

        gHouse.setPlant("AppleTree");
        apple = gHouse;

        Storable<Cropable> myStorage = new Storage();
        boolean bool = true;
        for (int i = 1; i < 400; i++) {
            apple.updateTime();
            if (bool) {
                apple.doCommand((x) -> x.setCount((int) (x.getCount() * 0.2 + x.getCount())));
                bool = false;
            }
            if (apple.isDie()) {
                System.out.println("i = " + i + "  Die");
                break;
            }
            if (apple.isCropReady()) {
                bool = true;
                Cropable crop = apple.getCrop();
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
        /*
        enum Com{
            PLOW{public double COST = 1; public double EFFECT = 0.2;},
            FERTILIZE{public double COST = 1; public double EFFECT = 0.2;}
        }
        */

        FarmNewsListener newsL = new FarmNewsListener();
        Farm farm = new Farm(1, newsL);
        farm.setPlant("AppleTree", 1);
        //farm.setPlant("AppleTree", 3);
        String[] sRep = farm.getFieldRepresentation();
        int i = 1;
        for (String s : sRep) {
            System.out.println(i + " " + s);
            i++;
        }

        while (true) {
            try {
                String userCom = readCoomandFromConsole();
                switch (userCom) {
                    case "" :
                        farm.updateTime();
                        if (newsL.isNews()) {
                            for (String news: newsL.getNews())
                            System.out.println(news);
                        }
                        break;
                }

                if (userCom.equals("E"))
                    break;

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     *  read expression from the console and return it
     * @return type String, expression entered by the user
     * @throws IOException when problems with console
     */
    public  static  String readCoomandFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return  br.readLine();
    }
}