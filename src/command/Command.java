package command;

import base.Countable;

/**
 * функциональный интерфейс для команды
 */
@FunctionalInterface
public interface Command {
    /**
     * выполняет операции над объектом(изменяет количество), который передается методу в параметре
     * @param count содержит объект, количество которого нужно изменить
     */
    public void Execute(Countable count);
}
