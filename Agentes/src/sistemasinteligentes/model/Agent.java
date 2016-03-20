package sistemasinteligentes.model;

import sistemasinteligentes.view.IPrintable;
import sistemasinteligentes.view.graphics.RenderPanel;
import sistemasinteligentes.view.IRenderizable;
import java.awt.Color;
import java.util.List;

public class Agent implements IRenderizable, IPrintable{
    final public Color AGENT_COLOR     = new Color(0x6070f0);
    final public Color AGENT_BORDER    = new Color(0xa0c0e0);
    final public int AGENT_RADIUS      = 25;
    
    private final State current;
    private final State objective;
    private Ambient ambient;
    private List<AbstractAction> solution;
    
    //Variavel de teste
    boolean state = false;
    
    public Agent(State current, State objective, Ambient ambient, List<AbstractAction> solution){
        this.current = current;
        this.objective = objective;
        this.ambient = ambient;
        this.solution = solution;
    }

    
    public void percept(){
       
     }
       
    public void choose(){
        //current = solution;
            
        System.out.println(current);   
       
    }
    
    public void execute(){
        
    }
    
//Utilize este metodo para desenhar em no painel desejado
    @Override
    public void render(RenderPanel mp) {
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_BORDER);
    }

    //Utilize este método para exibir na caixa de texto uma mensagem. 
    //Aconselho utilizar variaveis de estados como "percebendo", "decidindo" 
    //e "agindo" para exibir a cada etapa uma mensagem diferente
   @Override
    public String printText() {
       
         for(int n=0;n<solution.size()-1;n++){
            if(ambient.getLink(current.getID(),ambient.getState(n).getID() )!=null)
            {  
              return ("States on the border of  "+ current.getName() +": "+ ambient.getState(n).getName());
            }   
         }
         return "oi";
    } 
    
}
