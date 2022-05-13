import java.util.ArrayList;

/*
    Skriven av Melvin och Tomas
    Baserad på föreläsningsexempel
*/

public class Parser {
    // En klass som omvandlar en lista av tokens till exekverbara instruktioner

    private Lexer lexer; // Har lista av tokens
    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public ParseTree parse() throws Error, Exception{
        ParseTree newParseTree = new ParseTree();
        while(true) {
            if(lexer.peekToken().getType() != TokenType.EOF) {
                // Tills vi har EOF ska vi försöka lägga till instruktioner
                newParseTree.add(leonaExpr());
            }
            else {
                break;
            }
        }
        return newParseTree;
    }

    public AbstractInstruction leonaExpr() throws Error, Exception {
        /* 
            Tolkar listan av tokens från lexer till en instruktion
            Om något är inkorrekt throwas ett SyntaxError
        */

        Token t = lexer.nextToken();
        //System.err.println("Tokentype " + t.getType());
        if(t.getType() == TokenType.FORW) {
            // FORW måste vara följt av Decimal respektive Period
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
            // BACK måste vara följt av Decimal respektive Period
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
            // LEFT måste vara följt av Decimal respektive Period
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
            // RIGHT måste vara följt av Decimal respektive Period
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
            // FORW måste vara följt av Hex respektive Period
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
            /*  Rep kan antingen vara med eller utan citattecken
                Alltid följt av Decimal först
            */
            Token t1 = lexer.nextToken();
            if(t1.getType() != TokenType.DECIMAL) {
                throw new SyntaxError(t1.getRaw());
            }

                /*  Om nästa är quote, parsa efterföljande tokens som instruktioner inom repExpr 
                    Annars, läs in en instruktion och spara i Rep-instruktionen
                */

            Token t2 = lexer.peekToken();
            if(t2.getType() == TokenType.QUOTE) {
                lexer.nextToken(); // Nästa token ska vara den första på nästa instruktion
                ArrayList<AbstractInstruction> instructions = repExpr(); // Lista av instruktioner tills det kommer en avslutande quote
                return new RepeatNode((Integer)t1.getData(), instructions);
            }
            else {
                // TODO: In case of performance issues, change to some other data structure instead of arrayList
                ArrayList<AbstractInstruction> instructions = new ArrayList<>();
                // Ingen quote, bara en instruktion som ska hämtas
                instructions.add(leonaExpr());
                return new RepeatNode((Integer)t1.getData(), instructions);
            }
        } else {
            // Fel token, t.ex. INVALID, Decimal / Hex i början av kommando etc...
            //System.err.println("Unknown token:" + t.getType());
            throw new SyntaxError(t.getRaw());
        }
    }

    public ArrayList<AbstractInstruction> repExpr() throws Error, Exception {
        /* Läs in instruktioner tills någonting börjar på quote */

        ArrayList<AbstractInstruction> instructions = new ArrayList<>();
        Token t = lexer.peekToken();
        if(t.getType() != TokenType.QUOTE) {
            while(lexer.peekToken().getType() != TokenType.QUOTE) {
                instructions.add(leonaExpr());
            }
        } else {
            throw new SyntaxError(t.getRaw());
        }
        // Vi har nått den avslutande quoten
        lexer.nextToken();
        // Hoppa över den

        
        return instructions;
    }
}