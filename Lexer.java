import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Baserad på exempel från föreläsningarna
    Tillägg skrivna av Melvin och Tomas
*/

public class Lexer {
    // Klassen gör lexikal analys
    // = Översätter en sträng till en lista av tokens
    private String input;
    public ArrayList<Token> tokens;
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

    public Lexer(InputStream in) throws Exception {
        String input = Lexer.readInput(in).toUpperCase();       // Gör om alla små bokstäver till stora
        Pattern tokenPattern = Pattern.compile("(FORW\\s)|(BACK\\s)|(LEFT\\s)|(RIGHT\\s)|(DOWN)|(UP)|(COLOR\\s)|(REP\\s)|(#[A-Fa-f0-9]{6})|(\\.)|(\")|([1-9][0-9]*)|(%)|(\\s+)");   //lägg till regex för de olika tokens vi ska använda, regex for comments --> %(?!.*\\n).*
        
        /**
        * Matchar varje individuell token + Kommentarer + Whitespace
         */
        
        Matcher m = tokenPattern.matcher(input);
        int inputPos = 0;
        tokens = new ArrayList<Token>();
        boolean isComment = false;     //false by default
        currentToken = 0;
        int currentRow = 1;     //Kolla vilken rad kommandon ligger på, för att printa syntaxfel
        while(m.find()) {
            // Hittat en match på stora regexen
            if (m.start() != inputPos && (isComment == false)) {
                // Det finns tecken mellan föregående matchs slut och den härs början
                // = Invalid tecken emellan
                tokens.add(new Token(TokenType.INVALID, currentRow));
            }
            
            else if(m.group().startsWith("FORW")) {     //.startsWith eftersom m.group() kommer att innehålla en whitespace-karaktär ()
                tokens.add(new Token(TokenType.FORW, currentRow));
            }

            else if(m.group().startsWith("BACK")) {
                tokens.add(new Token(TokenType.BACK, currentRow));
            }

            else if(m.group().startsWith("LEFT")) {
                tokens.add(new Token(TokenType.LEFT, currentRow));
            }

            else if(m.group().startsWith("RIGHT")) {
                tokens.add(new Token(TokenType.RIGHT, currentRow));
            }

            else if(m.group().equals("DOWN")) {
                tokens.add(new Token(TokenType.DOWN, currentRow));
            }

            else if(m.group().equals("UP")) {
                tokens.add(new Token(TokenType.UP, currentRow));
            }

            else if(m.group().startsWith("COLOR")) {
                tokens.add(new Token(TokenType.COLOR, currentRow));
            }

            else if(m.group().startsWith("REP")) {
                tokens.add(new Token(TokenType.REP, currentRow));
            }

            else if(m.group().contains("#")) {
                int hashtagIndex = m.group().indexOf("#");
                // Om matchen har en # är det hex, token:s data ska vara en sträng på formatet "#hhhhhh" där h = [0-9a-f].
                tokens.add(new Token(TokenType.HEX, m.group().substring(hashtagIndex, hashtagIndex+7), currentRow));
            }

            else if(m.group().equals(".")) {
                tokens.add(new Token(TokenType.PERIOD, currentRow));
            }

            else if(m.group().contains("\"")) {
                tokens.add(new Token(TokenType.QUOTE, currentRow));
            }

            else if(m.group().matches("\\d+")) {
                // Decimal

                if(!input.substring(m.end(), m.end()+1).matches("(\\s)|(\\.)")) {
                    // Tal får endast följas av punkt eller whitespace
                    // Annars invalid
                    tokens.add(new Token(TokenType.INVALID, currentRow));
                } else {
                    // Följs av punkt eller whitespace, valid
                    tokens.add(new Token(TokenType.DECIMAL, Integer.valueOf(m.group()), currentRow));
                }
            }
            else if(m.group().equals("%")) {
                // Kommentar
                isComment = true;
            }
            
            inputPos = m.end();

            // Räkna nyrader
            if(m.group().contains("\n")) {
                isComment = false;              // Ny rad ==> Avsluta kommentar
                String tempGroup = m.group();   
                for(int i = 0; i < tempGroup.length(); i++) {       // Ifall matchningen har flera nyrader ska vi räkna alla
                    if(tempGroup.charAt(i) == '\n') {
                        currentRow++;
                    }
                }
            }      
        }
        // Kolla om det fanns något kvar av indata som inte var ett token
        if(inputPos != input.length()) {
            tokens.add(new Token(TokenType.INVALID, currentRow));
        }


        //token som signalerar slut på indata
        tokens.add(new Token(TokenType.EOF, currentRow));

        /*for(int i = 0; i < tokens.size(); i++){
            System.err.println(tokens.get(i).getType() + "   " + tokens.get(i).getRaw());
        }*/
    }


    // Kika på nästa token i indata, utan att gå vidare
    public Token peekToken() throws Error, Exception {
        // Slut på indataströmmen
        if (!hasMoreTokens())
            throw new Exception();
        return tokens.get(currentToken);
    }

    // Hämta nästa token i indata och gå framåt i indata
    public Token nextToken() throws Error, Exception {
        Token res = peekToken();
        ++currentToken;
        return res;
    }

    public boolean hasMoreTokens() {
        return currentToken < tokens.size();
    }

}