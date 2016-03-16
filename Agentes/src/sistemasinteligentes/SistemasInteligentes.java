/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacichen
 */
public class SistemasInteligentes {
    public static void main(String[] args) {
        
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        GUI gui = new GUI();
        gui.setVisible(true);
           
        State st1 = new State(30,30,"A",0);
        State st2 = new State(300,100,"B",1);
        List<State> list = new ArrayList<State>();
        list.add(st1);
        list.add(st2);
        Agent ag = new Agent(st1,st2,list);
        Link link = new Link(st1,st2,20);
        gui.assignRenderizable(ag);
        gui.assignRenderizable(link);
        gui.assignRenderizable(st1);
        gui.assignRenderizable(st2);
        
        gui.setAgent(ag);
    }    
}
