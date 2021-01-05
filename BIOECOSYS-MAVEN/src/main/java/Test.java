import org.chocosolver.graphsolver.GraphModel;
import org.chocosolver.graphsolver.variables.DirectedGraphVar;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.util.objects.graphs.DirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;

public class Test {
    public static void main(String[] args) {
        NodeArray obj = new NodeArray();
        NodeArray.Node[] nodeArray = obj.array;
        int n = 1374;

        GraphModel gmodel = new GraphModel();

        DirectedGraph GLB = new DirectedGraph(gmodel, 5, SetType.BITSET, false);
        DirectedGraph GUB = new DirectedGraph(gmodel, 5, SetType.BITSET, false);

        GUB.addNode(0);
        for (int i = 1; i < 4; i++) {
            GUB.addNode(i);
        }
        GUB.addArc(1,2);
        GUB.addArc(2,3);
        GUB.addArc(0,1);
        GUB.addArc(0,4);



        DirectedGraphVar dag = gmodel.digraphVar("dag", GLB, GUB);
        Constraint c = new Constraint("Test", new PropAntiSymmetric_coarse(dag));
        gmodel.post(c);

        Constraint c2 = new Constraint("Test2", new PropAntiSymmetric(dag));
        gmodel.post(c2);

        System.out.println(dag.graphVizExport()); // displays initial graph domain
        try {
            gmodel.getSolver().propagate(); // propagates constraints (without branching)
        } catch (ContradictionException e) {
            e.printStackTrace();
        }
        System.out.println(dag.graphVizExport()); // displays graph domain after propagation
        if (gmodel.getSolver().solve()){
            System.out.println("solution found : "+ 5598*2);
            System.out.println(dag.graphVizExport()); // displays solution graph
        }
        gmodel.getSolver().printStatistics();

    }
}
