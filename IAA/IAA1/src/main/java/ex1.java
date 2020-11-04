import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMiddle;
import org.chocosolver.solver.search.strategy.selectors.variables.FirstFail;
import org.chocosolver.solver.search.strategy.selectors.variables.Smallest;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelectorWithTies;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;
import org.chocosolver.util.tools.ArrayUtils;
public class ex1 {
    public static void main(String[] args) {
        //Verbal Arithmetic
        Model model = new Model("TWO+TWO=FOUR");
        IntVar T = model.intVar("T", 1, 9, false);
        IntVar W = model.intVar("W", 0, 9, false);
        IntVar O = model.intVar("O", 0, 9, false);
        IntVar F = model.intVar("F", 1, 9, false);
        IntVar U = model.intVar("U", 0, 9, false);
        IntVar R = model.intVar("R", 0, 9, false);

        model.allDifferent(new IntVar[]{T, W, O, F, U, R}).post();
        IntVar[] ALL = new IntVar[]{
                T, W, O,
                T, W, O,
                F, O, U, R
        };
        int[] COEFFS = new int[]{
                100, 10, 1,
                100, 10, 1,
                -1000, -100, -10, -1
        };
        model.scalar(ALL, COEFFS, "=", 0).post();

        Solver solver = model.getSolver();
        solver.showShortStatistics();
        solver.showSolutions();
        solver.findSolution();
    }

}