/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.model.search;

import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.State;
import sistemasinteligentes.view.graphics.RenderPanel;

/**
 *
 * @author Jacichen
 */
public class TableHeuristic extends HeuristicFunction {

    public TableHeuristic(Ambient ambient, State objective) {
        super(ambient, objective);
    }
    
    public void set(State st, int weight){
        if(st.getID() >= heuristicTable.length)
            return;
        heuristicTable[st.getID()] = weight;
    }

    public void set(int[] weights){
        if(weights.length != heuristicTable.length)
            return;
        for(int i = 0; i < weights.length; i++)
            heuristicTable[i] = weights[i];
    }
    
    @Override
    public String toString(){
        return "Heurística Tabelada";
    }
}
