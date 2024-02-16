package Tools;

import Commands.ACommand;

import java.util.HashMap;
import java.util.Map;

public class CommandManager
{

    private Map<String, ACommand> commands = new HashMap<>();

    public void addCommand(String commandName, ACommand command)
    {
        commands.put(commandName, command);
    }

    public Map<String, ACommand> getCommands()
    {
        return commands;
    }

    public ACommand getCommand(String commandName)
    {
        return commands.get(commandName);
    }

}
