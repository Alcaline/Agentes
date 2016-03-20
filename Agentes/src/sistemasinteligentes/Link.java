/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.awt.Color;

/**
 *
 * @author Jacichen
 */
public class Link implements Renderizable{
    final public Color LINK_COLOR      = new Color(0xf08040);

    private State first;
    private State second;
    private int weight;
    
    public Link(State first, State second, int weight){
        this.first = first;
        this.second = second;
        this.weight = weight;
    }
    
    public State getFirst(){
        return first;
    }
    
    public State getSecond(){
        return second;
    }
    
    public int getWeight(){
        return weight;
    }

    @Override
    public void render(RenderPanel mp) {
        double mod = Math.sqrt(Math.pow((double)second.getX()-first.getX(),2) + 
                Math.pow((double)second.getY()-first.getY(),2));
        double normx = ((double)second.getX()-first.getX())/mod;
        double normy = ((double)second.getY()-first.getY())/mod;
        double xref1 = ((double)first.getX())+normx*first.getRadius();
        double yref1 = ((double)first.getY())+normy*first.getRadius();
        double xref2 = ((double)second.getX())-normx*second.getRadius();
        double yref2 = ((double)second.getY())-normy*second.getRadius();
        mp.drawArrow((int) xref1, (int) yref1, (int) xref2, (int) yref2, LINK_COLOR, 10, 8);
        mp.drawText((first.getX()+second.getX())/2, (first.getY()+second.getY())/2, 
                String.valueOf(weight), mp.TEXT_COLOR, mp.TEXT_FONT, mp.VALIGN_MIDDLE, mp.HALIGN_CENTER);
    }
}
