public class Main {
    public static void main(String[] args) throws Exception{
        //System.err.println("Starting Lexer");
        Lexer lexer = new Lexer(System.in);
        //System.err.println("Starting Parser");
        Parser parser = new Parser(lexer);
        //System.err.println("Parsing");
        ParseTree parseTree = parser.parse();
        //System.err.println("Evaluating");
        parseTree.evaluate();
        //System.err.println("Finished"); 
    }
}
