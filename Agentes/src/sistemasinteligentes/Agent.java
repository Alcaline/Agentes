/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.awt.Color;
import java.util.List;

/**
 *
 * @author Jacichen
 */
public class Agent implements Renderizable{
    final public Color AGENT_COLOR     = new Color(0x6070f0);
    final public Color AGENT_BORDER    = new Color(0xa0c0e0);
    final public int AGENT_RADIUS      = 25;
    
    private State current;
    private State objective;
    private List<State> solution;
    
    public Agent(State current, State objective, List<State> solution){
        this.current = current;
        this.objective = objective;
        this.solution = solution;
    }
    
    public void advance(){
        int i = solution.indexOf(current);
        i++;
        if(i < solution.size())
            current = solution.get(i);
    }
            
    public void percept(){
    
    }
    
    public void choose(){
        
    }
    
    public void act(){
        
    }
    
    @Override
    public void render(RenderPanel mp) {
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_BORDER);
    }
    
}
