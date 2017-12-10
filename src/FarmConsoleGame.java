import Command.Command;
import Const.ArableConst;
import Visitor.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmConsoleGame {
    static protected FarmNewsListener newsL = new FarmNewsListener();
    static Farm farm = new Farm(2, newsL);
    static BigDecimal myWallet = new BigDecimal("20");

    static final int fieldElementPlowCost =1;
    static final int fieldElementFertilizeCost =2;


    public static void main(String[] args) {

        farm.setPlant("AppleTree", 1);
        /*
        updateTime(4);
        sellAllinStorage();
        addNewArable();
        */

        writeFieldRepresentation();

        while (true) {
            try {
                String userCom = readCoomandFromConsole();
                userCom = userCom.toUpperCase();

                String[] subCom = userCom.split(" ");

                if (userCom.equals("E"))
                    break;

                if (subCom.length == 1) {
                    if (subCom[0] == "E") break;
                    simpleUserCom(subCom[0]);
                } else
                    if (subCom.length > 1) {
                        subUserCom(subCom);
                    }
                writeNews(newsL);

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
            case "A" :
                if (addNewArable()) writeFieldRepresentation();
                break;
            case "W" :
                System.out.println("Wallet -> " + myWallet.toString());
                break;
            case "I" :
                presetnInformation();
                break;
            case "F" :
                writeFieldRepresentation();
                break;
            case "S" :
                sellAllinStorage();
                break;
            case "C" :
                System.out.println("The total cost of the crop in the storage: " + calculateTotalStorageCost());
                break;
            default:
                System.out.println(userCom + " -> Wrong command");
        }

        return true;
    }

    static void sellAllinStorage() {
        BigDecimal sellMoney = new BigDecimal(calculateTotalStorageCost());
        System.out.print("Sell -> " + sellMoney + " ");
        myWallet = myWallet.add(sellMoney);
        System.out.println("Wallet -> " + myWallet.toString());
        farm.releaseStorage();
    }

    static boolean addNewArable() {
        double arablePrice = ArableConst.PRICE;
        if (compareWIthWallet(arablePrice)) {
            farm.addArable();
            deductFromTheWallet(arablePrice);
            return true;
        }
        System.out.println("Not enough money :(");
        return false;
    }

    static double calculateTotalStorageCost() {
        Visitor myVisitor = new VisitorTotalCost();
        farm.acceptVisit(myVisitor);
        return myVisitor.getValue();
    }

    static void subUserCom(String[] subStr) {
        if (isNumber(subStr[0])) {
            setPlantIfPossible(Integer.parseInt(subStr[0]), subStr[1]);
            return;
        }
        switch (subStr[0]) {
            case "C" : doCommand(subStr);

                break;
            default:
                System.out.println("Wrong Command " + subStr[0]);
        }
    }

    public static void doCommand(String[] strs) {
        List<Command> commands = new ArrayList<Command>();
        List<String> executedCommands = new ArrayList<>();
        for (int i = 1; i< strs.length; i++) {
            switch (strs[i]) {
                case "P" :
                    // повысить урожайность на 20%
                    if(!addSimpleCommmand(commands, fieldElementPlowCost, 0.2))
                        System.out.println("Plow -> not enough money");
                    else executedCommands.add("Plow");
                    break;
                case "F" :
                    // повысить урожайность на 30%
                    if (!addSimpleCommmand(commands, fieldElementFertilizeCost, 0.3))
                        System.out.println("Fertilize -> not enough money");
                    else executedCommands.add("Fertilize");
                    break;
                default:
                    System.out.println(strs[i] + " -> invalid command\n");
            }
        }
        String writeCommsnds = "Executed commands: ";
        for (String comName: executedCommands)
            writeCommsnds += comName + " ";

        if (commands.size() > 0) {
            try {
                farm.doCommand(commands);
                System.out.println(writeCommsnds);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static boolean addSimpleCommmand(List<Command> com, double cost, double effect) {
        BigDecimal totalCost = new BigDecimal(farm.getArablesCount() * cost);
        if (myWallet.compareTo(totalCost) >= 0 )
        {
            myWallet.subtract(totalCost);
            com.add((x) -> x.setCount((int) (x.getCount() * effect + x.getCount())));
            return true;
        }
        return false;
    }

    static boolean setPlantIfPossible(int position, String nameFirsLetters) {
        try {
            boolean isNameExist = false;

            Map<String, Double> namesWCost = farm.getPlantNamesWCost();
            for (String name : namesWCost.keySet()) {
                String upCaseName = name.toUpperCase();
                if (upCaseName.indexOf(nameFirsLetters) == 0) {
                    isNameExist = true;
                    BigDecimal plantCost = new BigDecimal(namesWCost.get(name));
                    if (myWallet.compareTo(plantCost) >= 0) {
                        farm.setPlant(name, position);
                        myWallet = myWallet.subtract(plantCost);
                        System.out.println(name + " set at position " + position + " You spent -> " + plantCost);
                        return true;
                    }
                }
            }
            if(isNameExist)
                System.out.println("Not enough money");
            else
                System.out.println("Plant with firs letters [" + nameFirsLetters + "] is not exist");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    static void presetnInformation() {
        System.out.println("Commands:");

        System.out.println("    <Enter> -> one update");
        System.out.println("    <Number> -> number of updates");
        System.out.println("    <Number> <Plant name first letters -> set selected plant to Field element with <number>");
        System.out.println("    A -> new arable (new field element)");
        System.out.println("    C -> calculate the total cost of the crop in the storage");
        System.out.println("    C <F> or <P>... command for the field");
        System.out.println("        P -> to plow the field. Cost = field elemet count*1, Crop count += 20%");
        System.out.println("        F -> to fertilize the field. Cost = field element count*2, Crop count += 30%");
        System.out.println("    F -> Field representation");
        System.out.println("    S -> sell the whole crop in storage");
        System.out.println("    W -> amount of accumulated money -> wallet");
        System.out.println("    E -> exit");
        System.out.println("Separate all compound commands with spaces ;) A good game from Hakop... :)");
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
    private final int arableContInLine = 2;
    private final int charactersContInName = 10;

    public static void writeFieldRepresentation() {
        String[] sRep = farm.getFieldRepresentation();

        for (int i = 0; i < sRep.length;){
            int position = i;
            for (int j = 0; position < sRep.length && j < ArableConst.ARABLE_COUNT_IN_LINE_WHEN_REPRESENT;) {
                sRep[position] = (position + 1 ) + " " + sRep[position];
                System.out.print(sRep[position]);
                for (int k = sRep[position].length(); k < ArableConst.AMOUNT_OF_SPACE_FOR_DISPLAY; k++)
                    System.out.print(" ");
                j++;
                position = i + j;
            }
            i = position;
            System.out.println();
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

    private static   boolean compareWIthWallet(double price) {
        return myWallet.compareTo(new BigDecimal(price)) >= 0;
    }

    private static void deductFromTheWallet(double price) {
        myWallet = myWallet.subtract(new BigDecimal(price));
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
