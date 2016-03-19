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
        State st2 = new State(300,120,"B",1);
        State st3 = new State(300,30,"C",2);
        Ambient amb = new Ambient();
        amb.addState(st1);
        amb.addState(st2);
        amb.addState(st3);
        amb.addWeight(st1, st2, 20);
        amb.addWeight(st2, st3, 15);
        amb.addWeight(st1, st3, 25);
        List<Action> list = new ArrayList<Action>();
        list.add(new GoToAction(amb.getLink(0, 1)));
        list.add(new GoToAction(amb.getLink(1, 2)));
        list.add(new GoToAction(amb.getLink(0, 2)));
        Agent ag = new Agent(st1,st3,amb,list);
        gui.assignRenderizable(ag);
        gui.assignRenderizable(amb);
        gui.assignPrintable(ag);
        
        gui.setAgent(ag);
    }    
}
