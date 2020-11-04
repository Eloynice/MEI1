import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class ex3 {
    public static void main(String[] args) {
        int size = 3;
        Model model = new Model("Magic Squares");
        IntVar[][] vars = new IntVar[size][size];
        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                vars[i][j] = model.intVar("[" + i + ","+j +"]", 1, size);
            }
        }
    }
}