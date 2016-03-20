package sistemasinteligentes.view;

import sistemasinteligentes.view.graphics.RenderPanel;
import java.awt.Graphics2D;

//Interface para objetos se desenharem em um RenderPanel
public interface IRenderizable {
    //Esta função é chamada a cada repaint() quando o objeto esta registrado em um RenderPane
    public void render(RenderPanel mp);
}
