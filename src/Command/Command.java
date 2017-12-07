package Command;

import Base.Countable;

public interface Command {
    public void Execute(Countable count);
}
