/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.model.search;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import sistemasinteligentes.model.Agent;
import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.State;
import sistemasinteligentes.view.IPrintable;
import sistemasinteligentes.view.IRenderizable;
import sistemasinteligentes.view.graphics.RenderPanel;

/**
 *
 * @author Jacichen
 */
public abstract class HeuristicFunction implements IRenderizable{
    protected final Ambient ambient;
    protected final State objective;
    protected final int[] heuristicTable;
    
    protected final String name;
    
    public int get(State st){
        return heuristicTable[st.getID()];
    }
    
    private HeuristicFunction(){ambient = null; objective = null; heuristicTable = null; name = null;}
    
    public HeuristicFunction(Ambient ambient, State objective, String name){
        if(ambient == null)
            throw new NullPointerException();
        this.ambient = ambient;
        this.objective= objective;
        
        heuristicTable = new int[ambient.getStateSize()];
        
        for(int i = 0; i < heuristicTable.length; i++)
            heuristicTable[i] = -1;
        
        this.name = name;
    }
    
    @Override
    public void render(RenderPanel mp) {
        for(int i = 0; i < ambient.getStateSize(); i++)
            if(get(ambient.getState(i)) >= 0)
                mp.drawText(ambient.getState(i).getX() + State.STATE_RADIUS, ambient.getState(i).getY() - State.STATE_RADIUS, String.format("%02d", heuristicTable[i]), Agent.DESTINY_COLOR, mp.TEXT_FONT, mp.VALIGN_BOTTOM, mp.HALIGN_LEFT);
    
    }
}
