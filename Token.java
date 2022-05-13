/* Skriven av Tomas & Melvin */

// De olika token-typer vi har i grammatiken
enum TokenType {
    FORW, BACK, LEFT, RIGHT, DOWN, UP, COLOR, REP, PERIOD, QUOTE, DECIMAL, HEX, EOF, INVALID
}

/**
 * Klass för att representera en token
 * I praktiken vill man nog även spara info om vilken rad/position i
 * indata som varje token kommer ifrån, för att kunna ge bättre
 * felmeddelanden
 */
class Token {
    private TokenType type;
    private Object data;
    private int raw; // Radnummer av token


    public Token(TokenType type, int raw) {
        this.type = type;
        this.data = null;
        this.raw = raw;
    }

    public Token(TokenType type, Object data, int raw) {
        this.type = type;
        this.data = data;
        this.raw = raw;
    }

    public String toString() {
        if (data == null)
            return type.toString();
        else
            return type.toString() + "(" + data.toString() + ")";
    }

    public TokenType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public int getRaw() {
        return raw;
    }
}