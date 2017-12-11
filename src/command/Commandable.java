package command;

import java.util.List;

/**
 * определяет интерфейс для объектов, позволяющий им принимать команды, которые изменяют их количество
 */
public interface Commandable {
    /**
     * выполнить команду у объекта
     * @param commandList содержит список камонд, которые нужно выполнить для данного объекта
     */
    public void doCommand(List<Command> commandList);
}
