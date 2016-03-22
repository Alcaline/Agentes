package sistemasinteligentes.view;

//Interface para objetos imprimirem o que quiserem em uma caixa de texto
public interface IPrintable {
    //Esta função é chamada a cada update() quando o objeto esta registrado em um AbstractPrinter
    public String getMessage();
}
