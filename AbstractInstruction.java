abstract class AbstractInstruction {

    abstract public void evaluate(Leona leona);
    
}

class MovementNode extends AbstractInstruction {

    private int moveUnits;

    public MovementNode(int d) {
        moveUnits = d;
    }

    public void evaluate(Leona leona) {
        if(moveUnits < 0) {
            leona.moveBack(-moveUnits);
        } else {
            leona.moveForw(moveUnits);
        }
    }
    
}

class RotationNode extends AbstractInstruction {

    private int rotationUnits;

    public RotationNode(int angle) {
        rotationUnits = angle;
    }

    public void evaluate(Leona leona) {
        if(rotationUnits < 0) {
            leona.turnRight(-rotationUnits);
        } else {
            leona.turnLeft(rotationUnits);
        }
    }

}

class PenNode extends AbstractInstruction {

    private boolean changeColor; // True ==> COLOR, False ==> UP or DOWN
    private String newColor;

    private boolean newPenUp;

    public PenNode(String newColor) {
        this.newColor = newColor;
        this.changeColor = true;
    }

    public PenNode(boolean newPenUp) {
        this.newPenUp = newPenUp;
        this.changeColor = false;
    }

    public void evaluate(Leona leona) {
        if(this.changeColor) {
            leona.changeColor(this.newColor);
        } else {
            if(this.newPenUp) {
                leona.penUp();
            } else {
                leona.penDown();
            }
        }
        
    }

}

class RepeatNode extends AbstractInstruction {
    private int numReps;
    private ArrayList<AbstractInstruction> instructions;

    public RepeatNode(int numReps, ArrayList<AbstractInstruction> instructions) {
        this.numReps = numReps;
        this.instructions = instructions;
    }

    public void evaluate(Leona leona) {
        for(int i = 0; i < numReps; i++) {
            for(AbstractInstruction instruction : instructions) {
                instruction.evaluate(leona);
            }
        }
    }

}