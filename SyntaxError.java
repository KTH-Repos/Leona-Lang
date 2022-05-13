/* Skriven av Tomas & Melvin */

public class SyntaxError extends Exception{
    public SyntaxError(int row) {
        super("Syntaxfel på rad " + row);
    } 
}