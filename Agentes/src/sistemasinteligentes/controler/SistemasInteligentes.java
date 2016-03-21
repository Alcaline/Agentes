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

        State portalGraciosa = new State(30,80,"PG",0);
        State saoJoao = new State(130,80,"SJ",1);
        State bufara = new State(230,30,"B",2);
        State morretes = new State(230,120,"M",3);
        State antonina = new State(330,80,"A",4);
        
        Ambient amb = new Ambient();
        amb.addState(portalGraciosa);
        amb.addState(saoJoao);
        amb.addState(bufara);
        amb.addState(antonina);
        amb.addState(morretes);
       //Ida
        amb.addWeight(portalGraciosa, saoJoao, 18);
        amb.addWeight(saoJoao, morretes, 14);
        amb.addWeight(saoJoao, bufara, 18);
        amb.addWeight(bufara, antonina, 8);
        amb.addWeight(morretes, bufara, 8);
       //Volta
        amb.addWeight(saoJoao,portalGraciosa , 18);
        amb.addWeight(morretes, saoJoao, 14);
        amb.addWeight(bufara, saoJoao, 18);
        amb.addWeight(antonina,bufara , 8);
        amb.addWeight(bufara, morretes, 8);
        //cria o vetor solução
        List<AbstractAction> list = new ArrayList<>();
        list.add(new GoToAction(amb.getLink(0, 1)));
        list.add(new GoToAction(amb.getLink(1, 2)));
        list.add(new GoToAction(amb.getLink(2, 4)));
        Agent ag = new Agent(portalGraciosa,antonina,amb,list);
        gui.assignRenderizable(ag);
        gui.assignRenderizable(amb);
        gui.assignPrintable(ag);
        
        
     
    }    
}