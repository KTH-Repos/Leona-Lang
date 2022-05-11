import java.util.ArrayList;

public class ParseTree{

    private ArrayList<AbstractInstruction> instructions;

    public ParseTree() {
        instructions = new ArrayList<>();
    }

    public void add(AbstractInstruction ins) {
        instructions.add(ins);
    }

    public void evaluate() {
        Leona leona = new Leona();
        /* for(int i = 0; i < instructions.size(); i++){
            System.out.println(i);
        }  */
        for(AbstractInstruction instruction : instructions) {
            instruction.evaluate(leona);
        }
    }
}