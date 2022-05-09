abstract class AbstractInstruction {

    abstract public void evaluate(Leona leona);
    
}

class MovementNode extends AbstractInstruction {

    private int moveUnits;

    public MovementNode(int d) {
        moveUnits = d;
    }

    public void evaluate(Leona leona) {
        leona.move(moveUnits);
    }
    
}

class RotationNode extends AbstractInstruction {

    
    public void evaluate(Leona leona) {
        // TODO Auto-generated method stub
        
    }

}

class PenNode extends AbstractInstruction {

    public void evaluate() {
        // TODO Auto-generated method stub
        
    }

}

class RepeatNode extends AbstractInstruction {

    public void evaluate() {
        // TODO Auto-generated method stub
        
    }

}