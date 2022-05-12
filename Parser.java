import java.util.ArrayList;

public class Parser {
    private Lexer lexer;
    //private ParseTree parseTree;
    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public ParseTree parse() throws Error, Exception{
        //start from leona expressions/instructions
        //ParseTree pt = leonaExpr();
        //parseTree = new ParseTree();

        ParseTree newParseTree = new ParseTree();
        while(true) {
            if(lexer.peekToken().getType() != TokenType.EOF) {
                newParseTree.add(leonaExpr());
                //System.err.println("new instruction added");
            }
            else {
                break;
            }
        }
        return newParseTree;
    }

    public AbstractInstruction leonaExpr() throws Error, Exception {
        Token t = lexer.nextToken();
        if(t.getType() == TokenType.FORW) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t2.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new MovementNode((Integer)t1.getData());
        }
        else if(t.getType() == TokenType.BACK) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t2.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new MovementNode(-1*(Integer)t1.getData());

        }
        else if(t.getType() == TokenType.LEFT) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t2.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new RotationNode((Integer)t1.getData());
        }
        else if(t.getType() == TokenType.RIGHT) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t2.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new RotationNode(-1*(Integer)t1.getData());
        }
        else if(t.getType() == TokenType.UP) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t1.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new PenNode(true);
        }
        else if(t.getType() == TokenType.DOWN) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t1.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner.
            return new PenNode(false); 
        }
        else if(t.getType() == TokenType.COLOR) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.HEX) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError(t2.getRaw());
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
            return new PenNode((String)t1.getData());
        }
        else if(t.getType() == TokenType.REP) {
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() == TokenType.QUOTE) {
                // We need an ArrayList<AbstractInstruction>
                ArrayList<AbstractInstruction> instructions = repExpr();
                return new RepeatNode((Integer)t1.getData(), instructions);
            }
            else {
                // TODO: In case of performance issues, change to some other data structure instead of arrayList
                ArrayList<AbstractInstruction> instructions = new ArrayList<>();
                instructions.add(leonaExpr());
                return new RepeatNode((Integer)t1.getData(), instructions);
            }
        } else {
            throw new SyntaxError(t.getRaw());
        }
    }

    public ArrayList<AbstractInstruction> repExpr() throws Error, Exception {
        ArrayList<AbstractInstruction> instructions = new ArrayList<>();
        Token t = lexer.peekToken();
        if(t.getType() != TokenType.QUOTE) {
            while(lexer.peekToken().getType() != TokenType.QUOTE) {
                instructions.add(leonaExpr());
            }
        }

        return instructions;
    }
}