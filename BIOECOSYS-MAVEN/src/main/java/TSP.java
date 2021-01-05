import org.chocosolver.graphsolver.GraphModel;
import org.chocosolver.graphsolver.search.strategy.ArcStrategy;
import org.chocosolver.graphsolver.search.strategy.GraphStrategy;
import org.chocosolver.graphsolver.variables.UndirectedGraphVar;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.chocosolver.util.objects.setDataStructures.ISet;
import org.chocosolver.util.objects.setDataStructures.SetType;
//import org.chocosolver.solver.variables.VariableFactory;

public class TSP {
    public static void main(String[] args) {
        /*
        GraphModel model = new GraphModel("TSP");
        // variables
        IntVar totalCost = VariableFactory.bounded("obj", 0, 99999999, solver);
        // creates a graph containing n nodes
        UndirectedGraph GLB =new UndirectedGraph(solver, n, SetType.LINKED_LIST,true);
        UndirectedGraph GUB =new UndirectedGraph(solver, n, SetType.SWAP_ARRAY,true);
        // adds potential edges
        for(int i = 0; i < n; i++) {
            for(intj = i + 1; j < n; j++) {
                GUB.addEdge(i, j);
            }
        }
        graph = model.undirected_graph_var("G", GLB, GUB, solver);
        // constraints : TSP basic model + lagrangian relaxation (after a first solution has been found)
        model.tsp(graph, totalCost, costMatrix, 2))*/
    }

}
