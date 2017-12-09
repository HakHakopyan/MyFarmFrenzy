import Command.Command;
import Visitor.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FarmConsoleGame {
    static protected FarmNewsListener newsL = new FarmNewsListener();
    static Farm farm = new Farm(1, newsL);
    static BigDecimal myMoney = new BigDecimal("20");

    static final int fieldElementPlowCost =1;
    static final int fieldElementFertilizeCost =2;


    public static void main(String[] args) {

        farm.setPlant("AppleTree", 1);
        //farm.setPlant("AppleTree", 3);
        List<String> sRep = farm.getFieldRepresentation();
        int i = 1;
        for (String s : sRep) {
            System.out.println(i + " " + s);
            i++;
        }

        while (true) {
            try {
                String userCom = readCoomandFromConsole();
                userCom = userCom.toUpperCase();

                String[] subCom = userCom.split(" ");

                if (subCom.length == 1) {
                    if (subCom[0] == "E") break;
                    simpleUserCom(subCom[0]);
                }

                if (subCom.length > 1) {
                    subUserCom(subCom);
                }

                writeNews(newsL);

                if (userCom.equals("E"))
                    break;

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static boolean simpleUserCom(String userCom) {
        if (isNumber(userCom)) {
            updateTime(new Integer(userCom));
            return true;
        }

        switch (userCom) {
            case "" :
                updateTime(1);
                break;
            case "M" :
                System.out.println("Wallet -> " + myMoney.toString());
                break;
            case "I" :
                presetnInformation();
                break;
            case "S" :
                BigDecimal sellMoney = new BigDecimal(calculateTotalStorageCost());
                System.out.print("Sell -> " + sellMoney + " ");
                myMoney = myMoney.add(sellMoney);
                System.out.println("Wallet -> " + myMoney.toString());
                farm.releaseStorage();
                break;
            case "C" :
                System.out.println("The total cost of the crop in the storage: " + calculateTotalStorageCost());
                break;
        }

        return true;
    }

    static double calculateTotalStorageCost() {
        Visitor myVisitor = new VisitorTotalCost();
        farm.acceptVisit(myVisitor);
        return myVisitor.getValue();
    }

    public void doCommand(String[] strs) {
        List<Command> commands = new ArrayList<Command>();
        for (int i = 1; i< strs.length; i++) {
            switch (strs[i]) {
                case "P" :
                    // овысить урожайность на 20%
                    if(!addSimpleCommmand(commands, fieldElementPlowCost, 0.2))
                        System.out.println("Plow -> not enough money");;
                    break;
                case "F" :
                    // овысить урожайность на 30%
                    if (!addSimpleCommmand(commands, fieldElementFertilizeCost, 0.3))
                        System.out.println("Fertilize -> not enough money");
                    break;
                default:
                    System.out.println(strs[i] + " -> invalid command\n");
            }
        }

        if (commands.size() > 0) {
            try {
                farm.doCommand(commands);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static boolean addSimpleCommmand(List<Command> com, double cost, double effect) {
        BigDecimal totalCost = new BigDecimal(farm.getArablesCount() * cost);
        if (myMoney.compareTo(totalCost) >= 0 )
        {
            myMoney.subtract(totalCost);
            com.add((x) -> x.setCount((int) (x.getCount() * effect + x.getCount())));
            return true;
        }
        return false;
    }

    static void subUserCom(String[] subStr) {

    }

    static void presetnInformation() {
        System.out.println("Команды:");

        System.out.println("    M -> amount of accumulated money -> wallet");
        System.out.println("    <number> <Plant name first litter -> set selected plant to Field element with <number>");
        System.out.println("    S -> sell the whole crop in storage");
        //System.out.println("    C <Command> -> execute a command by name <Command>");
        System.out.println("    C -> calculate the total cost of the crop in the storage");
        System.out.println("    F <F> or <P>... command for the field");
        System.out.println("        P -> to plow the field. Cost = field elemet count*1, Crop count += 20%");
        System.out.println("        F -> to fertilize the field. Cost = field element count*2, Crop count += 30%");
        System.out.println("    E -> exit");
    }

    static void writeNews(FarmNewsListener news) {
        if (news.isNews()) {
            for (String line: news.getNews())
                System.out.println(line);
        }
    }

    static void updateTime(int count) {
        for (int i = 0; i < count; i++) {
            farm.updateTime();
        }
    }

    static boolean isNumber(String symbol) {
        try {
            new Integer(symbol);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
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
