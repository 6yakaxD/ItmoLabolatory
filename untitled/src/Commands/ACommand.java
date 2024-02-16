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

    @Override
    public String toString() {
        return String.format("%s -> %s", getName(), getDescription());
    }
}
