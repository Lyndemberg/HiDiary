
package Excecoes;

public class PreencheCamposException extends RuntimeException{
    public PreencheCamposException(){
        super("Preencha todos os campos");
    }
}
