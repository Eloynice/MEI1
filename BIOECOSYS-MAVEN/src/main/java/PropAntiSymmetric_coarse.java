import org.chocosolver.graphsolver.variables.DirectedGraphVar;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.util.ESat;
public class PropAntiSymmetric_coarse extends Propagator<DirectedGraphVar> {

            //*****************************************************************************
            // VARIABLES
            //*****************************************************************************

            DirectedGraphVar g;
            int n;

            //*****************************************************************************
            // CONSTRUCTORS
            //*****************************************************************************

            public PropAntiSymmetric_coarse(DirectedGraphVar graph) {
                super(graph);
                g = graph;
                n = g.getNbMaxNodes();
            }

            //*****************************************************************************
            // METHODS
            //*****************************************************************************

            @Override
            public void propagate(int evtmask) throws ContradictionException {
                // iterates over mandatory nodes
                for (int i : g.getMandatoryNodes()) {
                    // iterates over mandatory arcs
                    for (int j :g.getMandSuccOf(i)) {
                        g.removeArc(j, i, this); // removes symmetric arcs
                    }
                }
            }

            @Override // checker of partial instantiations, useful for reification
            public ESat isEntailed() {
                for (int i : g.getMandatoryNodes()) {
                    for (int j : g.getMandSuccOf(i)) {
                        if (g.getMandSuccOf(j).contains(i)) {
                            return ESat.FALSE; // the constraint is violated
                        }
                    }
                }
                if (g.isInstantiated()) {
                    return ESat.TRUE; // the constraint is satisfied for sure
                }
                return ESat.UNDEFINED; // satisfiability is undefined
            }
        }

