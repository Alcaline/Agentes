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
public class Agent implements Renderizable, Printable{
    final public Color AGENT_COLOR     = new Color(0x6070f0);
    final public Color AGENT_BORDER    = new Color(0xa0c0e0);
    final public int AGENT_RADIUS      = 25;
    
    
    private State current;
    private State objective;
    private Ambient ambient;
    private List<GoToAction> solution;
    
    //Variavel de teste
    boolean state = false;
    
    public Agent(State current, State objective, Ambient ambient, List<GoToAction> solution){
        this.current = current;
        this.objective = objective;
        this.ambient = ambient;
        this.solution = solution;
    }
    
    public void percept(){
       
     }
       
    public void choose(){
        current = solution;
            
        System.out.println(current);   
       
    }
    
    public void execute(){
        
    }
    
    @Override
    public void render(RenderPanel mp) {
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_BORDER);
    }

    @Override
    public String printText() {
       
         for(int n=0;n<solution.size()-1;n++){
            if(ambient.getLink(current,ambient.getState(n) )!=null)
            {  
              return ("States on the border of  "+ current.getName() +": "+ ambient.getState(n).getName());
            }   
         }
         return "oi";
    } 
    
}
