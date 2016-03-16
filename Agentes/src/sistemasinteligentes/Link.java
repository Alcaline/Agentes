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

    @Override
    public void render(RenderPanel mp) {
        mp.drawLine(first.getX(), first.getY(), second.getX(), second.getY(), LINK_COLOR);
        mp.drawText((first.getX()+second.getX())/2, (first.getY()+second.getY())/2, 
                String.valueOf(weight), mp.TEXT_COLOR, mp.TEXT_FONT, mp.VALIGN_MIDDLE, mp.HALIGN_CENTER);
    }
}
