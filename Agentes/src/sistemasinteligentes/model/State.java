package sistemasinteligentes.model;

import sistemasinteligentes.view.IRenderizable;
import sistemasinteligentes.view.graphics.RenderPanel;
import java.awt.Color;

public class State implements IRenderizable, Comparable<State>{
    static final public Color STATE_COLOR     = new Color(0xf0f0f0);
    static final public Color STATE_BORDER    = new Color(0x606060);
    static final public int STATE_RADIUS      = 20;
    
    private int x;
    private int y;
    
    private String name;
    private int id;
    
    public State(int x, int y, String name, int id){
        this.x = x;
        this.y = y;
        this.name = name;
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
   
    public int getX(){
        return x;
    }
    
    public void setX(int x ){
        this.x=x;
    }
    
    public int getY(){
        return y;
    }
    public void setY(int y ){
        this.y=y;
    }
    
    
    
    public int getID(){
        return id;
    }    
      
    public void setID(int id ){
        this.id=id;
    }
    
    public int getRadius(){
        return STATE_RADIUS;
    }

    @Override
    public void render(RenderPanel mp) {
        mp.drawCircle(x, y, STATE_RADIUS, STATE_COLOR, STATE_BORDER);
        mp.drawText(x, y, name, mp.TEXT_COLOR, mp.TEXT_FONT, mp.VALIGN_MIDDLE, mp.HALIGN_CENTER);
    }

    @Override
    public int compareTo(State o) {
        return id - o.id; 
    }
    
    @Override
    public String toString(){
        return getName()+ "["+ getID() +"]";
    }
}
