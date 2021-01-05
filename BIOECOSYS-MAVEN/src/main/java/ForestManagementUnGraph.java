import org.chocosolver.graphsolver.GraphModel;
import org.chocosolver.graphsolver.variables.DirectedGraphVar;
import org.chocosolver.graphsolver.variables.GraphVar;
import org.chocosolver.graphsolver.variables.UndirectedGraphVar;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;

public class ForestManagementUnGraph {
    public static void main(String[] args) {
        NodeArray obj = new NodeArray();
        NodeArray.Node[] nodeArray = obj.array;

        int n = 1374;

        GraphModel model = new GraphModel();
        UndirectedGraph GLB = new UndirectedGraph(model, n, SetType.BITSET, false);
        UndirectedGraph GUB = new UndirectedGraph(model, n, SetType.BITSET, false);
        GUB.addNode(0);
        for (int i = 1; i < 100; i++) {
            GUB.addNode(i);
            for (int j = 0; j < nodeArray[i].adj.length; j++) {
                GUB.addEdge(i, nodeArray[i].adj[j]);
            }
        }

        GLB.addNode(1);
        GLB.addNode(2);
        GLB.addEdge(1, 2);

       UndirectedGraphVar graphVar = model.graphVar("G", GLB, GUB);




        System.out.println(graphVar.graphVizExport()); // displays initial graph domain
        try {
            model.getSolver().propagate(); // propagates constraints (without branching)
        } catch (ContradictionException e) {
            e.printStackTrace();
        }
        System.out.println(graphVar.graphVizExport()); // displays graph domain after propagation
        if (model.getSolver().solve()){
            System.out.println("solution found : "+ 5598*2);
            System.out.println(graphVar.graphVizExport()); // displays solution graph
        }
        model.getSolver().printStatistics();


    }
}

