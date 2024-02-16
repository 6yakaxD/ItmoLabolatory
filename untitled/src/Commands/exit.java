package Commands;


public class exit extends ACommand
{


    public exit()
    {
        super("exit", "end the program without saving to a file");
    }

    @Override
    public boolean launch(String[] command) {
        if(command.length == 1)
        {
            System.out.println("-----=[ Exiting ]=-----");
            System.exit(1);
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
