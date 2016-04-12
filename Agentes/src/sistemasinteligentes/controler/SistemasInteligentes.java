/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.controler;

import static java.awt.Frame.MAXIMIZED_BOTH;
import sistemasinteligentes.model.GoToAction;
import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.State;
import sistemasinteligentes.model.Agent;
import sistemasinteligentes.view.graphics.GUI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemasinteligentes.model.AbstractAction;
import sistemasinteligentes.model.Link;
import sistemasinteligentes.model.search.AStarSolver;
import sistemasinteligentes.model.search.AbstractSolver;
import sistemasinteligentes.model.search.ReverseWeightHeuristic;
import sistemasinteligentes.model.search.TableHeuristic;

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
                if ("Windows".equals(info.getName())) {
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
                
        State a = new State(50,250,"A",0);
        State b = new State(200,150,"B",1);
        State c = new State(200,350,"C",2);
        State d = new State(350,50,"D",3);
        State e = new State(500,50,"E",4);
        State f = new State(350,150,"F",5);
        State g = new State(350,450,"G",6);
        State h = new State(650,250,"H",7);
        
        Ambient amb = new Ambient();
        amb.addState(a);
        amb.addState(b);
        amb.addState(c);
        amb.addState(d);
        amb.addState(e);
        amb.addState(f);
        amb.addState(g);
        amb.addState(h);

        List<State> choose = new ArrayList<>();
        choose.add(a);
        choose.add(b);
        choose.add(c);
        choose.add(d);
        choose.add(e);
        choose.add(f);
        choose.add(g);
        choose.add(h);
        
        Link lab = new Link(a, b, 20);
        Link lac = new Link(a, c, 20);
        Link lbd = new Link(b, d, 10);
        Link lbf = new Link(b, f, 19);
        Link lch = new Link(c, h, 25);
        Link lcg = new Link(c, g, 12);
        Link lgh = new Link(g, h, 12);
        Link lde = new Link(d, e, 5);
        Link ldf = new Link(d, f, 7);
        Link leh = new Link(e, h, 8);
        Link lfh = new Link(f, h, 6);
        
        amb.addBidirectionalConection(lab);
        amb.addBidirectionalConection(lac);
        amb.addBidirectionalConection(lbd);
        amb.addBidirectionalConection(lbf);
        amb.addBidirectionalConection(lch);
        amb.addBidirectionalConection(lcg);
        amb.addBidirectionalConection(lgh);
        amb.addBidirectionalConection(lde);
        amb.addBidirectionalConection(ldf);
        amb.addBidirectionalConection(leh);
        amb.addBidirectionalConection(lfh);

        List<AbstractAction> actions = new ArrayList<>();
        actions.add(new GoToAction(lab));
        actions.add(new GoToAction(lac));
        actions.add(new GoToAction(lbd));
        actions.add(new GoToAction(lbf));
        actions.add(new GoToAction(lch));
        actions.add(new GoToAction(lcg));
        actions.add(new GoToAction(lgh));
        actions.add(new GoToAction(lde));
        actions.add(new GoToAction(ldf));
        actions.add(new GoToAction(leh));
        actions.add(new GoToAction(lfh));
        
        List<AbstractSolver> solvers = new ArrayList<AbstractSolver>();
        
        TableHeuristic tableHeuristic = new TableHeuristic(amb,h,"h1");
        TableHeuristic tableHeuristic2 = new TableHeuristic(amb,h,"h2");
        //ReverseWeightHeuristic revWeightHeuristic = new ReverseWeightHeuristic(amb,h);
        
        tableHeuristic.set(new int[]{40,21,24,13,8,6,12,0});
        tableHeuristic2.set(new int[]{30,20,10,20,10,10,10,0});
        
        solvers.add(new AStarSolver(tableHeuristic));
        solvers.add(new AStarSolver(tableHeuristic2));
                
        GUI gui = new GUI(solvers);
                
        State init = (State) JOptionPane.showInputDialog(gui, "Escolha o estado inicial", "Estado Inicial", JOptionPane.QUESTION_MESSAGE, null, choose.toArray(), 0);

        gui.setExtendedState(MAXIMIZED_BOTH);
        gui.setVisible(true);
        
        if(init == null)
            init = a;
        
        Agent ag = new Agent(init,h,amb,actions,solvers.get(0));

        gui.setAgent(ag);
        gui.assignRenderizable(ag);
        gui.assignPrintable(ag);
        gui.assignRenderizable(amb);
    }    
}