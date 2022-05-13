import java.util.ArrayList;

/* Skriven av Tomas och Melvin */

abstract class AbstractInstruction {

    abstract public void evaluate(Leona leona);
    
}

class MovementNode extends AbstractInstruction {
    // Forw eller Back-instruktioner får läggas i en movement node

    private int moveUnits; // Hur många steg framåt

    public MovementNode(int d) {
        moveUnits = d;
    }

    public void evaluate(Leona leona) {
        // Gå framåt
        leona.move(moveUnits);
    }
    
}

class RotationNode extends AbstractInstruction {
    // Left eller Right-instruktioner får läggas i en rotation node

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
    // Up, Down, Color

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
            // Color
            leona.changeColor(this.newColor);
        } else {
            // Up or Down
            if(this.newPenUp) {
                leona.penUp();
            } else {
                leona.penDown();
            }
        }
        
    }

}

class RepeatNode extends AbstractInstruction {
    // Rep-instruktioner
    private int numReps; // Hur många gånger det ska repeteras
    private ArrayList<AbstractInstruction> instructions; // Instruktioner som körs 1 gång per repetition

    public RepeatNode(int numReps, ArrayList<AbstractInstruction> instructions) {
        this.numReps = numReps;
        this.instructions = instructions;
    }

    public void evaluate(Leona leona) {

        // Kör 1 gång per rep
        for(int i = 0; i < numReps; i++) {
            // Kör alla instruktioner i ordning
            for(AbstractInstruction instruction : instructions) {
                instruction.evaluate(leona);
            }
        }
    }

}