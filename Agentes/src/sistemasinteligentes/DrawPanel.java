/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import javax.swing.JPanel;

/**
 *
 * @author Jacichen
 */
public class DrawPanel extends JPanel {
    protected Graphics2D g2d;
    
    final public Color BG_COLOR         = new Color(0xefffff);
    final public Color BORDER_COLOR     = new Color(0x808080);
        
    final public Font TEXT_FONT         = Font.decode("Arial-BOLD-18");
    final public Color TEXT_COLOR       = new Color(0x404040);    
    
    final public float VALIGN_MIDDLE    = 0.5f;
    final public float VALIGN_BOTTON    = 1.0f;
    final public float VALIGN_TOP       = 0.0f;
    final public float HALIGN_CENTER    = 0.5f;
    final public float HALIGN_RIGHT     = 1.0f;
    final public float HALIGN_LEFT      = 0.0f;
    

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        
        preDraw();
        draw();
        drawBorder();
    }
    
    protected void preDraw(){
        g2d.setPaintMode();
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setBackground(BG_COLOR);
        clear();
    }
    
    protected void draw(){};
    
    public void clear() {
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    public void drawBorder() {
        Color prev = g2d.getColor();
        g2d.setColor(BORDER_COLOR);
        g2d.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        g2d.setColor(prev);
    }    
    
    public void drawCircle(int x, int y, int radius, Color fill, Color stroke) {
        Color prev = g2d.getColor();
        g2d.setColor(fill);
        g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
        g2d.setColor(stroke);
        g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);
        g2d.setColor(prev);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, Color stroke) {
        Color prev = g2d.getColor();
        g2d.setColor(stroke);
        g2d.drawLine(x1, y1, x2, y2);
        g2d.setColor(prev);
    }
    
    public void drawText(int x, int y, String text, Color fontColor, Font font, float vAlignment, float hAlignment) {
        Color prev = g2d.getColor();
        TextLayout tl = new TextLayout(text, font, g2d.getFontRenderContext());
        double h = tl.getBounds().getHeight();
        double w = tl.getBounds().getWidth();
        g2d.setColor(fontColor);
        g2d.setFont(font);
        tl.draw(g2d,(int) (x - w*hAlignment), (int) (y + h*vAlignment));
        g2d.setColor(prev);        
    }
    
    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color fill, Color stroke){
        Color prev = g2d.getColor();
        Polygon p = new Polygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
        g2d.setColor(fill);
        g2d.fillPolygon(p);
        g2d.setColor(stroke);
        g2d.drawPolygon(p);
        g2d.setColor(prev);
    }
    
    public void drawArrow(int x1, int y1, int x2, int y2, Color stroke, int arrowSize, int arrowWidth){
        Color prev = g2d.getColor();
        g2d.setColor(stroke);
        drawLine(x1, y1, x2, y2, stroke);
        double mod = Math.sqrt(Math.pow((double)x2-x1,2) + Math.pow((double)y2-y1,2));
        double normx = ((double)x2-x1)/mod;
        double normy = ((double)y2-y1)/mod;
        double xref = ((double)x2)-normx*arrowSize;
        double yref = ((double)y2)-normy*arrowSize;
        double sigx = Math.signum(normx)!=0?Math.signum(normx):1;
        double sigy = Math.signum(normy)!=0?Math.signum(normy):1;
        double perpx = sigx*Math.cos(Math.acos(normx)+Math.PI/2);
        double perpy = sigy*Math.sin(Math.asin(normy)+Math.PI/2);
        double xp1 = xref - perpx*arrowWidth/2;
        double yp1 = yref - perpy*arrowWidth/2;
        double xp2 = xref + perpx*arrowWidth/2;
        double yp2 = yref + perpy*arrowWidth/2;
        drawTriangle(x2, y2, (int) xp1, (int) yp1, (int) xp2, (int) yp2, stroke, stroke);
        g2d.setColor(prev);
    }
}
