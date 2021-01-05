import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class ForestManagementOg {
    public static void main(String[] args) {
        Model model = new Model("Forest Management");
        UgsInitializer init = new UgsInitializer(model);

        IntVar[] ugs = init.giveDomains();

        NodeArray obj = new NodeArray();
        NodeArray.Node[] nodeArray = obj.array;

        Graph g = new Graph();
        for (int i = 1; i < 1374; i++) {
            g.addVertex((nodeArray[i]));
        }
        for (int i = 1; i < 1374; i++) {
            for (int j = 0; j < nodeArray[i].adj.length; j++) {
                g.addEdge(nodeArray[i], (nodeArray[nodeArray[i].adj[j]]));
            }
        }

        GraphTraversal gt = new GraphTraversal();
        int[] t = gt.breadthFirstTraversalMod(g, nodeArray[1], ugs);

        for(int i = 1; i < 1374; i++){
            for(int j = 0; j < nodeArray[i].presc.length; j++){
                for(int k = 0; k < nodeArray[i].periods[j].length; k++){
                    if(nodeArray[i].periods[j][k] > 0){
                        //Constraint is cut legal?

                    }
                }
            }
            System.out.println(t[i]);
        }


        Solution solution = model.getSolver().findSolution();

        if (solution != null) {
            System.out.println(solution.toString());
        }

    }
}
