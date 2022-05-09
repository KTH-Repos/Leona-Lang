import java.util.ArrayList;

public class ParseTree{

    private ArrayList<AbstractInstruction> instructions;

    public ParseTree() {
        instructions = new ArrayList<>();
    }

    public void add(AbstractInstruction ins) {
        instructions.add(ins);
    }
}