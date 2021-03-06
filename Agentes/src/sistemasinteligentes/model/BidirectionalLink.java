
package sistemasinteligentes.model;

import sistemasinteligentes.view.graphics.RenderPanel;

public class BidirectionalLink extends Link{

    public BidirectionalLink(State first, State second, int weight) {
        super(first, second, weight);
    }
    
    public BidirectionalLink(Link link) {
        super(link.getFirst(),link.getSecond(),link.getWeight());
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
        mp.drawLine((int) xref1, (int) yref1, (int) xref2, (int) yref2, LINK_COLOR);
        mp.drawText((first.getX()+second.getX())/2, (first.getY()+second.getY())/2, 
                String.valueOf(weight), mp.TEXT_COLOR, mp.TEXT_FONT, mp.VALIGN_MIDDLE, mp.HALIGN_CENTER);
    }

}
