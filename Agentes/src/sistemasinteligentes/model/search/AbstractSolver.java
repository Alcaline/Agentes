/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.model.search;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemasinteligentes.model.AbstractAction;
import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.State;
import sistemasinteligentes.view.IPrintable;
import sistemasinteligentes.view.IRenderizable;

/**
 *
 * @author Jacichen
 */
public abstract class AbstractSolver implements IPrintable, IRenderizable{
    protected Ambient representationOriginal;
    protected List<AbstractAction> actionsOriginal;
    protected State initialStateOriginal;
    protected State objectiveOriginal;
    
    protected Ambient representation;
    protected List<AbstractAction> actions;
    protected State initialState;
    protected State objective;
    
    protected final List<AbstractAction> solution = new ArrayList<AbstractAction>();

    public List<AbstractAction> solve(Ambient representation, List<AbstractAction> actions, State initialState, State objective){
        this.representationOriginal = representation;
        this.actionsOriginal = actions;
        this.initialStateOriginal = initialState;
        this.objectiveOriginal = objective;
        
        this.representation = representation;
        this.actions = actions;
        this.initialState = initialState;
        this.objective = objective;
        
        solve(showSimulationAsking());
        resetParameters();
        return solution;
    }

    protected boolean showSimulationAsking() {
        return JOptionPane.showOptionDialog(null, "Deseja visualizar a resolução da busca?", "Simulação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0;
    }
    
    public void resetParameters(){
        representation = representationOriginal;
        actions = actionsOriginal;
        initialState = initialStateOriginal;
        objective = objectiveOriginal;
    }
    
    protected abstract void solve(boolean showSolving);
}
