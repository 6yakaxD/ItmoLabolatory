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
            System.exit(1);
            return true;
        }
        else
        {
            no_need_args(command, getName());
            return false;
        }
    }


}
