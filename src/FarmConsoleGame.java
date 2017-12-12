import command.Command;
import constants.*;
import visitor.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Основной класс, при запуске метода main которого начинается игра
 */
public class FarmConsoleGame {
    /**
     * получает все новости которые происходят внутри класса Farm
     */
    static protected FarmNewsListener newsL = new FarmNewsListener();
    /**
     * Ферма, в которой растет урожай и перемещается на склад
     */
    static Farm myFarm = new Farm(2, newsL);
    /**
     * Кошелек, количество денег которые у нас есть за конкретный момент времени
     */
    static BigDecimal myWallet = new BigDecimal("20");

    /**
     * стоимость вспахивания одного элемента поля
     */
    static final int fieldElementPlowCost =1;
    /**
     * стоимость удобрения одного элемента поля
     */
    static final int fieldElementFertilizeCost =2;

    /**
     * метода, с которого начинается игры
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the game myFarm frenzy");
        System.out.println("To get information on possible commands, press I and enter button");
        myFarm.setPlant("AppleTree", 1);

        writeRepresentation(myFarm.getFieldRepresentation());

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

    /**
     * Обработка простых команд- состоящих из одного символа/слова
     * @param userCom содежит команду, введенную пользователем
     * @return true when the command is executed, false when the command is not defined
     */
    public static boolean simpleUserCom(String userCom) {
        if (isNumber(userCom)) {
            updateTime(new Integer(userCom));
            return true;
        }

        switch (userCom) {
            case "" : updateTime(1);
                break;
            case "A" :
                if (addNewArable()) writeRepresentation(myFarm.getFieldRepresentation());
                else System.out.println("Not enough money :(");
                break;
            case "C" : System.out.println("The total cost of the crop in the storage: " + calculateTotalStorageCost());
                break;
            case "F" : writeRepresentation(myFarm.getFieldRepresentation());
                break;
            case "I" : presetnInformation();
                break;
            case "P" : showPlantsPossibleForPlanting();
                break;
            case "S" : System.out.print("Sell -> " + sellAllinStorage() + " ");
                System.out.println("Wallet -> " + myWallet);
                break;
            case "W" : System.out.println("Wallet -> " + myWallet);
                break;
            default: System.out.println(userCom + " -> Wrong command");
        }

        return true;
    }

    /**
     * показывает все доступные для посадки растения с ценами
     */
    public static void showPlantsPossibleForPlanting() {
        Map<String, Double> plantsWithCost = myFarm.getPlantNamesWCost();
        String[] showInfo = new String[plantsWithCost.size()];
        int i = 0;
        for(String name: plantsWithCost.keySet()) {
            showInfo[i] = name + "->" + plantsWithCost.get(name);
            i++;
        }
        writeRepresentation(showInfo);
    }

    /**
     * Продать весь накопившийся на складе урожай
     */
    public static BigDecimal sellAllinStorage() {
        BigDecimal sellMoney = new BigDecimal(calculateTotalStorageCost());
        myWallet = myWallet.add(sellMoney);
        myFarm.releaseStorage();
        return sellMoney;
    }

    /**
     * Добавить новый участок земли
     * @return true when a new piece of land was able to add
     */
    public static boolean addNewArable() {
        double arablePrice = ArableConst.PARSEL_PRICE;
        if (compareWIthWallet(arablePrice)) {
            myFarm.addArable();
            deductFromTheWallet(arablePrice);
            return true;
        }
        return false;
    }

    /**
     * посчитать стоимость всего урожая на складе
     * @return Resul of calculation
     */
    public static double calculateTotalStorageCost() {
        Visitor myVisitor = new VisitorTotalCost();
        myFarm.acceptVisit(myVisitor);
        return myVisitor.getValue();
    }

    /**
     * Выполняет составные команды, команды состоящий из нескольких слов
     * @param subStr contain command words
     */
    public static void subUserCom(String[] subStr) {
        if (isNumber(subStr[0])) {
            setPlantIfPossible(Integer.parseInt(subStr[0]), subStr[1]);
            return;
        }
        switch (subStr[0]) {
            case "C" : doCommand(subStr);
                break;
            case "G" : myWallet = myWallet.subtract( new BigDecimal(buildGreenHouseIfPossible(subStr[1])));
                break;
            default:
                System.out.println("Wrong command " + subStr[0]);
        }
    }

    /**
     * Строит теплицу по заданной позиции
     * @param position number of a piece of land on which to build a greenhouse
     * @return 0 - еcли теплица не построена, Цена теплицы если она построена.
     */
    public static double buildGreenHouseIfPossible(String position) {
        if (isNumber(position)) {
            try {
                if (!compareWIthWallet(ArableConst.GREENHOUSE_PRICE)) {
                    System.out.println("There is not enough money to build a greenhouse, you need " +
                            ArableConst.GREENHOUSE_PRICE);
                } else
                    if (myFarm.setGreenHouse(Integer.valueOf(position))) {
                        System.out.println("GreenHouse is built on the position of " + position + " you spent "
                                + ArableConst.GREENHOUSE_PRICE);
                        return ArableConst.GREENHOUSE_PRICE;
                    }
                else System.out.println("In position " + position + " GreenHouse is already exist");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } else
            System.out.println(position + " is not integer number :(");
        return 0;
    }

    /**
     * выполняет команды типа вспахать/удобрить поле
     * @param strs содержит набор комманд типа F удобрить / P вспахать
     */
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
        String writeCommands = "Executed commands: ";
        for (String comName: executedCommands)
            writeCommands += comName + " ";

        if (commands.size() > 0) {
            try {
                myFarm.doCommand(commands);
                System.out.println(writeCommands);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Добавляет в переданный параметр com новую лямбда функцию
     * @param com сюда мы добавляем новую команда
     * @param cost стоимость выполнения команды (вычитаемая из кошелька)
     * @param effect фактически процент увеличения урожайности
     * @return true when add a new command to the transferred properties managed
     */
    static boolean addSimpleCommmand(List<Command> com, double cost, double effect) {
        BigDecimal totalCost = new BigDecimal(myFarm.getArablesCount() * cost);
        if (myWallet.compareTo(totalCost) >= 0 )
        {
            myWallet = myWallet.subtract(totalCost);
            com.add((x) -> x.setCount((int) (x.getCount() * effect + x.getCount())));
            return true;
        }
        return false;
    }

    /**
     * the method tries to set a new plant in the element field position, if it possible
     * @param position number Field element
     * @param nameFirsLetters the first letters of the name of the plant, which determine which plant should be planted
     * @return true if new plant was send
     */
    public static boolean setPlantIfPossible(int position, String nameFirsLetters) {
        try {
            boolean isNameExist = false;

            Map<String, Double> namesWCost = myFarm.getPlantNamesWCost();
            for (String name : namesWCost.keySet()) {
                String upCaseName = name.toUpperCase();
                if (upCaseName.indexOf(nameFirsLetters) == 0) {
                    isNameExist = true;
                    BigDecimal plantCost = new BigDecimal(namesWCost.get(name));
                    if (myWallet.compareTo(plantCost) >= 0) {
                        myFarm.setPlant(name, position);
                        myWallet = myWallet.subtract(plantCost);
                        System.out.println(name + " set at position " + position + " You spent -> " + plantCost);
                        return true;
                    }
                }
            }
            if(isNameExist)
                System.out.println("Not enough money");
            else
                System.out.println("plant with firs letters [" + nameFirsLetters + "] is not exist");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Выводит на консоль информацию по возможным командам для игры
     */
    public static void presetnInformation() {
        System.out.println("Commands:");

        System.out.println("    <Enter> -> one update");
        System.out.println("    <Number> -> number of updates");
        System.out.println("    <Number> <plant name first letters> -> set selected plant to Field element with <number>");
        System.out.println("    <A> -> new arable (new field element)");
        System.out.println("    <C> -> calculate the total cost of the crop in the storage");
        System.out.println("    <C> <F> or <P>... command for the field");
        System.out.println("        <P> -> to plow the field. Cost = field elemet count*1, crop count += 20%");
        System.out.println("        <F> -> to fertilize the field. Cost = field element count*2, crop count += 30%");
        System.out.println("    <F> -> Field representation");
        System.out.println("    <G> <Number> -> build a GreenHouse in the field position <Number>");
        System.out.println("    <P> -> show plants possible for planting on the Field");
        System.out.println("    <S> -> sell the whole crop in storage");
        System.out.println("    <W> -> amount of accumulated money -> wallet");
        System.out.println("    <E> -> exit");
        System.out.println(" After entering each command, press enter button to make it work");
        System.out.println(" Separate all compound commands with spaces ;) A good game from Hakop... :)");
    }

    /**
     * Выводит новости, которые накопились в параметре news
     * @param news содержит строки(новости) которые нужно вывести
     */
    public static void writeNews(FarmNewsListener news) {
        if (news.isNews()) {
            for (String line: news.getNews())
                System.out.println(line);
        }
    }

    /**
     * Добалвяет время ферме, чтобы растения росли и давали урожай :)
     * @param count количество "месяцев", на которое нужно обновить ферму
     */
    public static void updateTime(int count) {
        for (int i = 0; i < count; i++) {
            myFarm.updateTime();
        }
    }

    /**
     * Выводит информацию, содержащуюся в sRep на консоль в виде таблицы,
     * кол-во столбцов задается в ArableConst.ARABLE_COUNT_IN_LINE_WHEN_REPRESENT
     * @param sRep содержит информацию, которую нужно вывести
     */
    public static void writeRepresentation(String[] sRep) {

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

    /**
     * Проверяет является ли переданный параметр целым числом
     * @param symbol содержит предполагаемое число
     * @return true if symbol is integer
     */
    protected static boolean isNumber(String symbol) {
        try {
            new Integer(symbol);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Сравнивает переданное число с кол-вом денег в кошельке
     * @param price число, которое нужно сравнить с имеющимся кол-вом денег в кошельке
     * @return true if money in wallet is more than price value
     */
    protected static   boolean compareWIthWallet(double price) {
        return myWallet.compareTo(new BigDecimal(price)) >= 0;
    }

    /**
     * Вычитает переданное число из кол-ва денег имеющегося в кошельке
     * @param price содежит число, которое нужно вычесть из накопленного кол-ва денег
     */
    protected static void deductFromTheWallet(double price) {
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
