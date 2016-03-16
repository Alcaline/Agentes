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
        g2d.setColor(BORDER_COLOR);
        g2d.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
    }    
    
    public void drawCircle(int x, int y, int radius, Color fill, Color stroke) {
        g2d.setColor(fill);
        g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
        g2d.setColor(stroke);
        g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, Color stroke) {
        g2d.setColor(stroke);
        g2d.drawLine(x1, y1, x2, y2);
    }
    
    public void drawText(int x, int y, String text, Color fontColor, Font font, float vAlignment, float hAlignment) {
        TextLayout tl = new TextLayout(text, font, g2d.getFontRenderContext());
        double h = tl.getBounds().getHeight();
        double w = tl.getBounds().getWidth();
        g2d.setColor(fontColor);
        g2d.setFont(font);
        tl.draw(g2d,(int) (x - w*hAlignment), (int) (y + h*vAlignment));
    }

}
