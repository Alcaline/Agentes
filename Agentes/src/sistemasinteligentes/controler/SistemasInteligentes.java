/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.controler;

import sistemasinteligentes.model.GoToAction;
import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.State;
import sistemasinteligentes.model.Agent;
import sistemasinteligentes.view.graphics.GUI;
import java.util.ArrayList;
import java.util.List;
import sistemasinteligentes.model.AbstractAction;
import sistemasinteligentes.model.Link;

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

        State portalGraciosa = new State(50,150,"PG",0);
        State saoJoao = new State(200,150,"SJ",1);
        State bufara = new State(350,50,"B",2);
        State morretes = new State(350,250,"M",3);
        State antonina = new State(500,150,"A",4);
        
        Ambient amb = new Ambient();
        amb.addState(portalGraciosa);
        amb.addState(saoJoao);
        amb.addState(bufara);
        amb.addState(antonina);
        amb.addState(morretes);
        
        Link l0 = new Link(portalGraciosa, saoJoao, 18);
        Link l1 = new Link(saoJoao, morretes, 14);
        Link l2 = new Link(saoJoao, bufara, 18);
        Link l3 = new Link(bufara, antonina, 8);
        Link l4 = new Link(morretes, bufara, 8);
        
        amb.addBidirectionalConection(l0);
        amb.addBidirectionalConection(l1);
        amb.addBidirectionalConection(l2);
        amb.addBidirectionalConection(l3);
        amb.addBidirectionalConection(l4);
        //cria o vetor solução
        List<AbstractAction> solution = new ArrayList<>();
        solution.add(new GoToAction(l0));
        solution.add(new GoToAction(l2));
        solution.add(new GoToAction(l3));
        
        List<AbstractAction> actions = new ArrayList<>();
        actions.add(new GoToAction(l0));
        actions.add(new GoToAction(l1));
        actions.add(new GoToAction(l2));
        actions.add(new GoToAction(l3));
        actions.add(new GoToAction(l4));
        
        Agent ag = new Agent(portalGraciosa,antonina,amb,actions,solution);
        gui.setAgent(ag);
        gui.assignRenderizable(ag);
        gui.assignPrintable(ag);
        gui.assignRenderizable(amb);
    }    
}