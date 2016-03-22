package sistemasinteligentes.view.graphics;

import javax.swing.JTextArea;
import sistemasinteligentes.view.IPrintable;

//Esta classe associa um JTextArea com objetos que desejam enviar a ele mensagens
public class JTextAreaPrinter extends AbstractTextPrinter{
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
    public void update() {
        clear();
        for(IPrintable p: printList)
            printer.append(p.getMessage());
        printer.setCaretPosition(0);
    }

    public void clear() {
        printer.setText(null);
    }
    
}
