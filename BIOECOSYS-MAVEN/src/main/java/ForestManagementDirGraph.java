import org.chocosolver.graphsolver.GraphModel;
import org.chocosolver.graphsolver.variables.DirectedGraphVar;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.objects.graphs.DirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;

public class ForestManagementDirGraph {
    public static void main(String[] args) {
        NodeArray obj = new NodeArray();
        NodeArray.Node[] nodeArray = obj.array;

        int n = 1374;

        GraphModel gmodel = new GraphModel();
        DirectedGraph GLB = new DirectedGraph(gmodel, n, SetType.BITSET, true);
        DirectedGraph GUB = new DirectedGraph(gmodel, n, SetType.BITSET, true);
        GLB.addArc(0,1); // some arbitrary mandatory arcs
        GLB.addArc(1,2);
        GLB.addArc(3,1);
        GUB.addArc(0,1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < nodeArray[i].adj.length; j++) {
                GUB.addArc(i, nodeArray[i].adj[j]);
            }
        }

        DirectedGraphVar dag = gmodel.digraphVar("dag", GLB, GUB);


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
