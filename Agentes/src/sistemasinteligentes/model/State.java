package sistemasinteligentes.model;

import sistemasinteligentes.view.IRenderizable;
import sistemasinteligentes.view.graphics.RenderPanel;
import java.awt.Color;

public class State implements IRenderizable, Comparable<State>{
    final public Color STATE_COLOR     = new Color(0xb0f010);
    final public Color STATE_BORDER    = new Color(0x606060);
    final public int STATE_RADIUS      = 20;
    
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
    
    public String getName(){
        return name;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getID(){
        return id;
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
        return id > o.id? 1 : (id < o.id? -1 : 0); 
    }
}
