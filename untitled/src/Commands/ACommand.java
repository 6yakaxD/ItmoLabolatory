package Commands;

import java.io.IOException;

public abstract class ACommand
{
    protected ACommand(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    private String name;
    private String description;

    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }

    public abstract boolean launch(String[] command) throws IOException;

    public static void no_need_args(String[] command, String name)
    {
        System.out.println("-----=[ <" + name + "> does not expect any arguments ]=-----");
        /*
        for (int i=1; i<command.length; i++)
        {
            System.out.println("unused argument -> " + command[i]);
        }
        */
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", getName(), getDescription());
    }
}
