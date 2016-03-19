package sistemasinteligentes;

import java.util.ArrayList;
import java.util.List;

public abstract class TextPrinter {
    protected final List<Printable> printList;
    
    protected TextPrinter(){
        printList = new ArrayList<>();
    }
    
    void assignPrintable(Printable p){
        printList.add(p);
    }
    
    void unassignPrintable(Printable p){
        printList.remove(p);
    }
    
    abstract void update();
}
