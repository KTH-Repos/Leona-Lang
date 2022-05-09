public class Parser {
    private Lexer lexer;

    public ParseTree parse() throws SyntaxError{
        //start from leona expressions/instructions
        ParseTree pt = leonaExpr();
        Token t = lexer.nextToken();
    }

    public ParseTree leonaExpr() throws SyntaxError {
        Token t = lexer.nextToken();
        if(t.getType() == TokenType.FORW) {
            if(lexer.nextToken().getType() != TokenType.DECIMAL) {
                throw new SyntaxError();
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
        }
        else if(t.getType() == TokenType.BACK) {
            if(lexer.nextToken().getType() != TokenType.DECIMAL) {
                throw new SyntaxError();
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 

        }
        else if(t.getType() == TokenType.LEFT) {
            if(lexer.nextToken().getType() != TokenType.DECIMAL) {
                throw new SyntaxError();
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
        }
        else if(t.getType() == TokenType.RIGHT) {
            if(lexer.nextToken().getType() != TokenType.DECIMAL) {
                throw new SyntaxError();
            }
            Token t2 = lexer.nextToken();
            if(t2.getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
        }
        else if(t.getType() == TokenType.UP) {
            if(lexer.nextToken().getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
        }
        else if(t.getType() == TokenType.DOWN) {
            if(lexer.nextToken().getType() != TokenType.PERIOD) {
                throw new SyntaxError();
            }
            //lägg till instruktionen till nån datastruktur med andra giltiga instruktioner. 
        }
        else if(t.getType() == TokenType.COLOR) {
            if(lexer.nextToken().getType() != TokenType.HEX) {
                throw new SyntaxError();
            }
        }
    }
}