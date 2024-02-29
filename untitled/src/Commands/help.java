package Commands;

import Tools.CommandManager;

public class help extends ACommand
{
    private CommandManager commandManager;

    public help(CommandManager commandManager)
    {
        super("help", "display help on available commands");
        this.commandManager = commandManager;
    }

    @Override
    public boolean launch(String[] command)
    {
        if(command.length == 1)
        {
            commandManager.getCommands().values().stream().map(ACommand::toString).forEach(System.out::println);

            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }

    }


}
