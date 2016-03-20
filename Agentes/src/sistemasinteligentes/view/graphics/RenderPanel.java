package sistemasinteligentes.view.graphics;

import java.util.List;
import java.util.ArrayList;
import sistemasinteligentes.view.IRenderizable;

//Esta "desenha" os objetos "assinantes" (membros de renderObjects) 
//chamando o método render() de cada um para que possam se autodesenhar 
public class RenderPanel extends DrawPanel{
    final protected List<IRenderizable> renderObjects = new ArrayList<>();

    @Override
    protected void draw(){       
        for(IRenderizable rObj: renderObjects)
            rObj.render(this);
    }
    
    public void assignRenderizable(IRenderizable rend){
        renderObjects.add(rend);
    }
    
    public void unassignRenderizable(IRenderizable rend){
        renderObjects.remove(rend);
    }
}
