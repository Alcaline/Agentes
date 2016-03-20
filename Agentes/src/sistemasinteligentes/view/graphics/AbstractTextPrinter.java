package sistemasinteligentes.view.graphics;

import java.util.ArrayList;
import java.util.List;
import sistemasinteligentes.view.IPrintable;

//Esta classe é um Wrapper- ela cria uma interface entre alguem que pode 
//imprimir textos e alguem que quer imprimir texto, e imprime o texto de todos 
//os "assinantes" da classe (menbros de printList) quando update() é chamado
public abstract class AbstractTextPrinter {
    protected final List<IPrintable> printList;
    
    protected AbstractTextPrinter(){
        printList = new ArrayList<>();
    }
    
    public void assignPrintable(IPrintable p){
        printList.add(p);
    }
    
    public void unassignPrintable(IPrintable p){
        printList.remove(p);
    }
    
    //Imprime mensagens dos integrantes de printList
    public abstract void update();
}
