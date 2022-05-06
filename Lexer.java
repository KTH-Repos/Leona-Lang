import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    public List<Token> tokens;
    private int currentToken;

    // Hjälpmetod som läser in innehållet i en inputstream till en
    // sträng
    private static String readInput(InputStream f) throws java.io.IOException {
        Reader stdin = new InputStreamReader(f);
        StringBuilder buf = new StringBuilder();
        char input[] = new char[1024];
        int read = 0;
        while ((read = stdin.read(input)) != -1) {
            buf.append(input, 0, read);
        }
        return buf.toString();
    }

    public Lexer(InputStream in) throws java.io.Exception {
        String input = Lexer.readInput(in);
        Pattern tokenPattern = Pattern.compile("");   //lägg till regex för de olika tokens vi ska använda
        Matcher m = tokenPattern.matcher(input);
        int inputPos = 0;
        tokens = new ArrayList<Token>();
        currentToken = 0;
        // Hitta förekomster av tokens/whitespace i indata
        while(m.find()) {

        }
        // Kolla om det fanns något kvar av indata som inte var ett token
        if(inputPos != input.length()) {
            tokens.add(new Token(TokenType.INVALID));
        }
        //token som signalerar slut på indata
        tokens.add(new Token(TokenType.EOF));
    }


    // Kika på nästa token i indata, utan att gå vidare
    public Token peekToken() throws SyntaxError {
        // Slut på indataströmmen
        if (!hasMoreTokens())
            throw new SyntaxError();
        return tokens.get(currentToken);
    }

    // Hämta nästa token i indata och gå framåt i indata
    public Token nextToken() throws SyntaxError {
        Token res = peekToken();
        ++currentToken;
        return res;
    }

    public boolean hasMoreTokens() {
        return currentToken < tokens.size();
    }

}