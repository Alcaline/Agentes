/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jacichen
 */
public class RenderPanel extends DrawPanel{
    final protected List<Renderizable> renderObjects = new ArrayList<>();
        
    final public Font TEXT_FONT        = Font.decode("Arial-BOLD-18");
    final public Color TEXT_COLOR      = new Color(0x404040);    

    @Override
    public void draw(){       
        for(Renderizable rObj: renderObjects)
            rObj.render(this);
    }
    
    public void assignRenderizable(Renderizable rend){
        renderObjects.add(rend);
    }
    
    public void unassignRenderizable(Renderizable rend){
        renderObjects.remove(rend);
    }
}
