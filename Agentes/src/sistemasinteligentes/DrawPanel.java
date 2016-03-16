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
import javax.swing.JPanel;

/**
 *
 * @author Jacichen
 */
public class DrawPanel extends JPanel {
    protected Graphics2D g2d;
    
    final public Color BG_COLOR        = new Color(0xefffff);
    final public Color BORDER_COLOR    = new Color(0x808080);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        
        preDraw();
        draw();
        drawBorder();
    }
    
    private void preDraw(){
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
    
    public void drawText(int x, int y, String text, Color fontColor, Font font) {
        g2d.setColor(fontColor);
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }
}
