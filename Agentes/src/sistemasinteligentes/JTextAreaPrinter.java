/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import javax.swing.JTextArea;

/**
 *
 * @author Jacichen
 */
public class JTextAreaPrinter extends TextPrinter{
    final private JTextArea printer;
    
    private JTextAreaPrinter(){
        super();
        printer = null;
    }
    
    public JTextAreaPrinter(JTextArea printer) {
        super();
        this.printer = printer;
    }

    @Override
    void update() {
        clear();
        for(Printable p: printList)
            printer.append(p.printText());
    }

    void clear() {
        printer.setText(null);
    }
    
}
