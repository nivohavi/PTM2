package commands;

import java.io.IOException;

public interface Command {
    void doCommand(String[] args) throws IOException;
}
