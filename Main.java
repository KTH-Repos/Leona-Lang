public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("Starting Lexer");
        Lexer lexer = new Lexer(System.in);
        System.out.println("Starting Parser");
        Parser parser = new Parser(lexer);
        System.out.println("Parsing");
        ParseTree parseTree = parser.parse();
        System.out.println("Evaluating");
        parseTree.evaluate();
        System.out.println("Finished");
    }
}
