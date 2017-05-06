
package Excecoes;

public class CompromissosException extends RuntimeException{
    public CompromissosException(){
        super("Você não tem compromissos");
    }
}
