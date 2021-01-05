import org.chocosolver.graphsolver.variables.DirectedGraphVar;
import org.chocosolver.graphsolver.variables.GraphEventType;
import org.chocosolver.graphsolver.variables.delta.GraphDeltaMonitor;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.PropagatorPriority;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.util.ESat;
import org.chocosolver.util.procedure.PairProcedure;

public class PropAntiSymmetric extends Propagator<DirectedGraphVar> {

    //***********************************************************************
    // VARIABLES
    //***********************************************************************

    DirectedGraphVar g;
    int n;
    // object enabling to iterate over enforced/removed nodes/arcs
    GraphDeltaMonitor gdm;
    // procedure to apply to every enforced arc (a, b) in order to remove (b, a)
    PairProcedure enf = (from, to) -> {
        if (from != to) {
            g.removeArc(to, from, this);
        }
    };

    //***********************************************************************
    // CONSTRUCTORS
    //***********************************************************************

    public PropAntiSymmetric(DirectedGraphVar graph) {
        super(new DirectedGraphVar[]{graph}, PropagatorPriority.UNARY, true);
        g = graph;
        gdm = g.monitorDelta(this);
        n = g.getNbMaxNodes();
    }

    //***********************************************************************
    // METHODS
    //***********************************************************************

    @Override
    public void propagate(int evtmask) throws ContradictionException {
        // First propagation (not incremental)
        for (int i : g.getMandatoryNodes()) {
            for (int j :g.getMandSuccOf(i)) {
                g.removeArc(j, i, this);
            }
        }
        gdm.unfreeze(); // necessary call to setup incremental data-structures
    }

    @Override
    public void propagate(int idxVarInProp, int mask) throws ContradictionException {
        // incremental propagation over every enforced arc since the last call
        gdm.freeze();
        gdm.forEachArc(enf, GraphEventType.ADD_ARC);
        gdm.unfreeze();
    }

    @Override
    public int getPropagationConditions(int vIdx) {
        return GraphEventType.ADD_ARC.getMask();
    }

    @Override
    public ESat isEntailed() {
        for (int i : g.getMandatoryNodes()) {
            for (int j : g.getMandSuccOf(i)) {
                if (g.getMandSuccOf(j).contains(i)) {
                    return ESat.FALSE;
                }
            }
        }
        if (g.isInstantiated()) {
            return ESat.TRUE;
        }
        return ESat.UNDEFINED;
    }
}


