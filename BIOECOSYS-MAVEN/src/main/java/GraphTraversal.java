import org.chocosolver.solver.variables.IntVar;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class GraphTraversal {
    /*static Set<String> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }
    static Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }*/


    public int[] breadthFirstTraversalMod(Graph graph, NodeArray.Node root, IntVar[] ugs) {
        int[] t = new int[1374];
        int pointer = 1;
        t[pointer] = root.ug;
        pointer++;
        Set<NodeArray.Node> visited = new LinkedHashSet<NodeArray.Node>();
        Queue<NodeArray.Node> queue = new LinkedList<NodeArray.Node>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            NodeArray.Node vertex = queue.poll();
            for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                    t[pointer] = v.label.ug;
                    pointer++;
                }
            }
        }
        return t;
    }
}