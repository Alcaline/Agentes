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
    
    private GUI g = new GUI();
    private State current;
    private State objective;
    private Ambient ambient;
    private List<Action> solution;
    
    public Agent(State current, State objective, Ambient ambient, List<Action> solution){
        this.current = current;
        this.objective = objective;
        this.ambient = ambient;
        this.solution = solution;
    }
    
     public void advance()
     {
         
     }
    public void percept(){
      
        g.printText("Environment's perceptions");
      //  for(int n=0;n<solution.size()-1;n++){
      //      if(ambient.getLink(current,ambient.getState(n) )!=null)
      //          g.printText("State:"+ n + ambient.getState(n));
                
      //  }
        
    }
    
    public void choose(){
        
        
       
    }
    
    public void execute(){
        
    }
    
    @Override
    public void render(RenderPanel mp) {
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_BORDER);
    }
    
}
