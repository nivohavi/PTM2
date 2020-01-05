package client_side;

public class Lexer
{
    private static Lexer _instance = null;

    private Lexer() {}

    public static Lexer getInstance()
    {
        if (_instance == null)
            _instance = new Lexer();

        return _instance;
    }

    public String[] lex(String line)
    {
        return line.split("\\s+");
    }

}
