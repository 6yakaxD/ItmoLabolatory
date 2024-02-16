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
            System.out.println("-----=[ Command List ]=-----");
            commandManager.getCommands().values().stream().map(ACommand::toString).forEach(System.out::println);

            return true;
        }
        else
        {
            System.out.println("command <" + getName() + "> does not expect any arguments");
            for (int i=1; i<command.length; i++)
            {
                System.out.println("unused argument -> " + command[i]);
            }
            return false;
        }

    }


}
