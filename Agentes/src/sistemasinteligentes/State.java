/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.awt.Color;

public class State implements Renderizable, Comparable<State>{
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
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
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
