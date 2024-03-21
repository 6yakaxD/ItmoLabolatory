package CustomExeptions;

public class ErrorInsertCommandInScript extends Throwable {

    public ErrorInsertCommandInScript(String text)
    {
        System.out.println(text);
    }

}
