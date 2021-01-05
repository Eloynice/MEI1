/*
 * Copyright (C) 2017 COSLING S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.chocosolver.graphsolver.GraphModel;
import org.chocosolver.graphsolver.variables.UndirectedGraphVar;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;

/**
 * Simple example of connected dominating set problem
 * Shows how to use graph-set channeling and how to create a set constraint
 * @author Jean-Guillaume FAGES (cosling)
 * @version Choco Solver 4.0.4
 * @version Choco Graph 4.2.0
 */
public class DomSetProblem {

    public static void main(String[] args){
        // INPUT GRAPH
        int n = 6;
        UndirectedGraph inputGraph = new UndirectedGraph(n, SetType.BITSET, true);
        for(int[] edge:new int[][]{{0,1},{1,2},{0,3},{1,3},{2,4},{3,4},{4,5}})
            inputGraph.addEdge(edge[0],edge[1]);

        GraphModel model = new GraphModel();

        // GRAPH VARIABLE : initial domain (from https://en.wikipedia.org/wiki/Dominating_set)
        UndirectedGraph GUB = new UndirectedGraph(model, n, SetType.BITSET, false);
        UndirectedGraph GLB = new UndirectedGraph(model, n, SetType.BITSET, false);
        for(int i:inputGraph.getNodes()){
            GUB.addNode(i);
            for(int j:inputGraph.getNeighOf(i)){
                GUB.addEdge(i,j);
            }
        }
        UndirectedGraphVar subgraph = model.graphVar("g", GLB, GUB);
        model.connected(subgraph).post();

        // DOMINATING SET
        BoolVar[] domSet = model.nodeSetBool(subgraph);
        IntVar size = model.intVar(0,n);
        model.setObjective(Model.MINIMIZE,size);
        model.sum(domSet,"=",size).post();
        for(int i=0;i< n;i++){
            int idx = 0;
            BoolVar[] cluster = new BoolVar[1+inputGraph.getNeighOf(i).size()];
            cluster[idx++] = domSet[i];
            for(int j:inputGraph.getNeighOf(i))cluster[idx++] = domSet[j];
            model.or(cluster).post();
        }

        //SEARCH (domset only -> then all edges can be used)
        model.getSolver().setSearch(Search.intVarSearch(variables -> {
            int min = -1;
            int size1 = Integer.MAX_VALUE/10;
            for(int i=0;i< n;i++){
                if(!variables[i].isInstantiated()){
                    if(size1 > inputGraph.getNeighOf(i).size()){
                        size1 = inputGraph.getNeighOf(i).size();
                        min = i;
                    }
                }
            }
            return min==-1?null:variables[min];
        }, new IntDomainMin(),domSet));

        System.out.println("input graph : "+inputGraph);
        System.out.println("start solving...");
        while (model.getSolver().solve()){
            System.out.println("dominating set : ");
            for(int i=0;i< n;i++)if(domSet[i].isInstantiatedTo(1)) System.out.println("\t"+i);
        }
    }
}
            